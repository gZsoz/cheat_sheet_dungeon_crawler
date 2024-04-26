package Character;

import java.util.ArrayList;

import Items.BatSkin;
import Items.Beer;
import Items.Item;
import Items.Transistor;
import Map.Room;
import ProtoUtil.ProtoUtil;
import Time.iTask;

/**
 * A Character egy absztrakt osztály, amely a hallgatók és az oktatók közös tulajdonságait
 * foglalja magába a kód duplikációjának elkerülése végett. Ez az osztály felelős a karakterek
 * inventory-jának (eszköztárának) nyilvántartásáért, életállapotuk figyeléséért (aktív vagy
 * elájult), a pálya keretein belüli szabad mozgásukért, valamint tárgyak felvételéért és letételéért
 * a játék környezetében. A Character leszármazottai a Teacher és a Student.
 */
public abstract class Character implements iTask {
	
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
		stunned = s;
	}
	
	public void reduceStunned() {
		ProtoUtil.printLog("reduceStunned");
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
    	if(r.getCharacters().size() < r.getCapacity()) {
    		currentRoom.removeCharacter(this);
    		r.addCharacter(this);
    		currentRoom = r;
    		stunned = 0;
    		boolean b=false;
    		for(Item i : inventory) {
    			if(i instanceof Beer) b=true;
    		}
    		if((!b) && this instanceof Student) ((Student)this).setInvincible(false);
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
    	if(inventory.size() < 5 ) {
    		if(i instanceof Transistor) {
    			Transistor transistorInRoom = (Transistor) i;
    			if(transistorInRoom.getPair() != null) {
    				ProtoUtil.printLog("Could not pick up item");
    				return false; // párosított tranzisztort nem vehetünk fel
    			}
    		}
			currentRoom.removeItem(i);
			inventory.add(i);
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
    	currentRoom.addItem(i);
    	i.onDrop();
    }

    /**
     * A karakter időérzékeny műveleteit végzi (mozgás).
     */
    public void update() {
    	if(0 < stunned && stunned <= 4) {
    		reduceStunned();
    		if(stunned==0) stunned=10;
    	}
    	if(4<stunned) {
    		reduceStunned();
    		if(stunned==4) stunned=0;
    	}
    	for(Item temp : new ArrayList<Item>(inventory)) {
			if(temp instanceof iTask) {
				((iTask) temp).update();
			}
		}
    }

	

}
