package Character;

import Items.Item;
import Items.Transistor;
import Map.*;
import ProtoUtil.*;

/**
 * A Character egy absztrakt osztály, amely a hallgatók és az oktatók közös tulajdonságait
 * foglalja magába a kód duplikációjának elkerülése végett. Ez az osztály felelős a karakterek
 * inventory-jának (eszköztárának) nyilvántartásáért, életállapotuk figyeléséért (aktív vagy
 * elájult), a pálya keretein belüli szabad mozgásukért, valamint tárgyak felvételéért és letételéért
 * a játék környezetében. A Character leszármazottai a Teacher és a Student.
 */
public abstract class Character {
	
	/**
	 * Az inicializálás tesztjéhez két azonos típusú változó megkülönböztetésére.
	 */
	public String name;
	
	/**
	 * Az inicializálás tesztjéhez a konstruktor szimulálására.
	 */
	public void create() {
		ProtoUtil.printLog(name + ".create()");
	}

    /**
     * Akkor hívódik meg, amikor egy karakter át akar menni egyik szobából a másikba.
     * @param r A szoba, amelybe be akar menni.
     * @return Ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal.
     */
    public boolean enterRoom(Room r) {
    	ProtoUtil.printLog(name + ".enterRoom(" + r.name + ")");
    	ProtoUtil.increaseIndent();
    	if(ProtoUtil.binaryQuestion("Befér-e a szobába?")) {
    		new Room().getCapacity();
    		new Room().getCharacters();
    		if(ProtoUtil.binaryQuestion("Elátkozott-e a szoba?")) {
    			new CursedRoom().addCharacter(this);
				new Room().removeCharacter(this);
    		} else {
    			new Room().addCharacter(this);
				new Room().removeCharacter(this);
    		}
    	}
    	ProtoUtil.decreaseIndent();
		return false;
    }

    /**
     * A paraméterül kapott tárgy felvételét valósítja meg.
     * @param i A felvevendő tárgy.
     * @return Ha az inventory-ban van hely, akkor a művelet sikeres és igaz értékkel tér vissza afüggvény, egyébként hamissal.
     */
    public boolean pickupItem(Item i) {
    	ProtoUtil.printLog(name + ".pickupItem(" + i.name + ")");
		ProtoUtil.increaseIndent();
		this.getInventory();
		if(ProtoUtil.binaryQuestion("Van hely a karakternél a tárgy számára?")) {
			new Room().removeItem(i);
			i.onPickUp();
		} else {
		}
		ProtoUtil.decreaseIndent();
		return false;
    }

    /**
     * A paraméterül kapott tárgy letételét valósítja meg.
     * @param i A tárgy, amit le akar tenni.
     */
    public void putdownItem(Item i) {
    	ProtoUtil.printLog(name + "putdownItem(" + i.name + ")");
		ProtoUtil.increaseIndent();
		
		if(ProtoUtil.binaryQuestion("Ez egy tranzisztor?")) {
			if(ProtoUtil.binaryQuestion("Ez a tranzisztor össze van kötve egy másikkal?")) {
				if(ProtoUtil.binaryQuestion("Ez lesz közülük a második, amit letesz?")) {
					if(ProtoUtil.binaryQuestion("Aktiválva van?")) {
						new Transistor("tranzisztor_második").onDrop(this);
					}
				} else {
					new Transistor("tranzisztor_első").setLocation(new Room());
				}
			}
		}
		new Room().addItem(i);
		ProtoUtil.decreaseIndent();
    }

    /**
     * A karakter időérzékeny műveleteit végzi (mozgás).
     */
    public void update() {
    }
    
	/**
	 * Visszaadja a karakter inventory-ját.
	 */
	public void getInventory() {
		ProtoUtil.printLog(name + ".getInventory()");
		ProtoUtil.increaseIndent();
		ProtoUtil.decreaseIndent();
	}

	/**
	 * Beállítja a karakter stunned állapotát.
	 * @param b Ha elkábult a karakter, akkor igaz, egyébként hamis.
	 */
	public void setStunned(boolean b) {
		ProtoUtil.printLog(name + ".setStunned(" + b + ")");
		ProtoUtil.increaseIndent();
		ProtoUtil.decreaseIndent();
	}
}