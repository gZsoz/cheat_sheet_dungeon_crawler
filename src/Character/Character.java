package Character;

import java.util.ArrayList;
import java.util.List;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Sticky;
import Items.Beer;
import Items.Item;
import Items.Transistor;
import Map.Room;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import View.Utils.Subscriber;

/**
 * A Character egy absztrakt osztály, amely a hallgatók és az oktatók közös tulajdonságait
 * foglalja magába a kód duplikációjának elkerülése végett. Ez az osztály felelős a karakterek
 * inventory-jának (eszköztárának) nyilvántartásáért, életállapotuk figyeléséért (aktív vagy
 * elájult), a pálya keretein belüli szabad mozgásukért, valamint tárgyak felvételéért és letételéért
 * a játék környezetében. A Character leszármazottai a Teacher és a Student.
 */
public abstract class Character implements iTask {
	public static int stunTime=4;
	public static int restTime=10;
	
	/** A karakter változásaira feliratkozott osztályok*/
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * A karakter birtokában található tárgyak
	 * listája. Maximum 5 Item lehet benne. Nem lehet benne logarléc.
	 */
	protected ArrayList<Item> inventory;
	
	/**
	 * Melyik szobában van éppen a karakter.
	 */
	protected Room currentRoom;

	/**
	 * El van-e kábítva a karakter.
	 */
	protected int stunned;
	
	/**
	 * Van-e védelme a karakternek kábítás ellen.
	 */
	protected boolean hasDefense;
	
    /**
     * meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
     *  függvényüket a paraméterként kapott Stringgel
     * @param str
     */
    public void notifySubsribers(String str) {
    	for(Subscriber sub : new ArrayList<>(subscribers))
    		sub.propertyChanged(str); // lehetséges értékek: "stun", "inventory", "invincible", "kicked"
    }
	
    /**
     * hozzáadja a paraméterként kapott Subscriber objektumot a feliratkózók listájához
     * ezzentúl a propertyChanged függvénye meghívásával jelzi, ha belső állapota megváltozik
     * @param sub
     */
    public void subscribe(Subscriber sub) {
    	subscribers.add(sub);
    }
    
    /**
     * eltávolítja a paraméterként kapott Subscriber objektumot a feliratkózók listájából
     * ezzentúl nem kap értesítést, ha az osztály belső állapota megváltozik
     * @param sub
     */
    public void unsubscribe(Subscriber sub) {
    	subscribers.remove(sub);
    }
    
	/**
	 * Inventory lekérdezése.
	 * @return A karakter birtokában lévő tárgyak listája.
	 */
	public ArrayList<Item> getInventory(){
		ProtoUtil.printLog("getInventory");
		return inventory;
	}
	
	/**
	 * Szoba lekérdezése.
	 * @return A karakter tartózkodási helye.
	 */
	public Room getRoom() {
		ProtoUtil.printLog("getRoom");
		return currentRoom;
	}
	
	public void setRoom(Room r) {
		currentRoom=r;
	}
	
	/**
	 * Kábítás állapot lekérdezése.
	 * @return A karakter kábultsági állapota.
	 */
	public int getStunned() {
		ProtoUtil.printLog("getStunned");
		return stunned;
	}
	
	
	/**
	 * Kábultsági állapotának beállítása.
	 * @param s Ha kábult a karakter, akkor igaz, egyébként hamis.
	 */
	public void setStunned(int s) {
		ProtoUtil.printLog("setStunned");
		notifySubsribers("stun");	// jelzi, hogy a stunolás állapota megváltozhat
		stunned = s;
	}
	
	public void reduceStunned() {
		ProtoUtil.printLog("reduceStunned");
		notifySubsribers("stun");	// jelzi, hogy a stunolás állapota megváltozhat
		stunned--;
	}
	
	/**
	 * Kábítás elleni védelem lekérdezése.
	 * @return Ha van védelme a karakternek kábítás ellen, akkor igaz, egyébként hamis.
	 */
	public boolean getHasDefense() {
		return hasDefense;
	}
	
	/**
	 * Kábítás elleni védelem beállítása.
	 * @param b Ha van védelme a karakternek kábítás ellen, akkor igaz, egyébként hamis.
	 */
	public void setHasDefense(boolean b) {
		hasDefense = b;
	}
	
    /**
     * Akkor hívódik meg, amikor egy karakter át akar menni egyik szobából a másikba.
     * @param r A szoba, amelybe be akar menni.
     * @return Ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal.
     */
    public boolean enterRoom(Room r) {
    	ProtoUtil.printLog("enterRoom");
    	if(r.getCharacters().size() < r.getCapacity() && !(stunned > 0 && stunned <= stunTime)) {
    		Room temp=currentRoom;
    		currentRoom = r;
    		temp.removeCharacter(this);
    		r.addCharacter(this);
    		setStunned(0);
    		for(EnvironmentalFactors ef: currentRoom.getEnvironmentalFactors()) {
    			if(ef instanceof Sticky) {
    				((Sticky) ef).reduceRemainingEntries();
    				break;
    			}
    		}
    		
    		boolean hasBeer=false;
    		for(Item i : inventory) {
    			if(i instanceof Beer) {
    				hasBeer=true;
    				break;
    			}
    		}
    		if((!hasBeer) && this instanceof Student) ((Student)this).setInvincible(false); // a denevérbőr ne védjen tovább
    		return true;
    	}
    	return false;
    }

    /**
     * A paraméterül kapott tárgy felvételét valósítja meg.
     * @param i A felvevendő tárgy.
     * @return Ha az inventory-ban van hely, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, ha egy párral rendelkező tranzisztort venne fel, illetve egyéb esetben hamissal.
     */
    public boolean pickupItem(Item i) {
    	ProtoUtil.printLog("pickupItem");
    	if(inventory.size() < 5 && !(stunned > 0 && stunned <= stunTime) && !i.getSticky()) {
    		if(i instanceof Transistor) {
    			Transistor transistorInRoom = (Transistor) i;
    			if(transistorInRoom.getPair() != null) {
    				ProtoUtil.printLog("Could not pick up item");
    				return false; // párosított tranzisztort nem vehetünk fel
    			}
    		}
			currentRoom.removeItem(i);
			inventory.add(i);
			notifySubsribers("inventory");
			i.setOwner(this);
			ProtoUtil.printLog("Successfully picked up item");
    		i.onPickUp();
    		return true;
    	}
    	ProtoUtil.printLog("Could not pick up item");
    	return false;
    }

    /**
     * A paraméterül kapott tárgy letételét valósítja meg.
     * @param i A tárgy, amit le akar tenni.
     */
    public void putdownItem(Item i) {
    	ProtoUtil.printLog("putdownItem");
    	inventory.remove(i);
    	notifySubsribers("inventory");
    	currentRoom.addItem(i);
    	i.onDrop();
    }

    /**
     * A karakter időérzékeny műveleteit végzi.
     */
    public void update() {
    	if(0 < stunned && stunned <= stunTime) {
    		reduceStunned();
    		if(stunned==0) setStunned(restTime);
    	}
    	if(stunTime<stunned) {
    		reduceStunned();
    		if(stunned==stunTime) setStunned(0);
    	}
    	for(Item temp : new ArrayList<Item>(inventory)) {
			if(temp instanceof iTask) {
				((iTask) temp).update();
			}
		}
    }

	

}
