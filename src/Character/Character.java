package Character;

import Items.Item;
import Items.Mask;
import Items.Transistor;
import Map.*;
import SkeletonUtil.*;

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
		SkeletonUtil.printLog(name + ".create()");
	}

    /**
     * Akkor hívódik meg, amikor egy karakter át akar menni egyik szobából a másikba.
     * @param r A szoba, amelybe be akar menni.
     * @return Ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal.
     */
    public boolean enterRoom(Room r) {
    	SkeletonUtil.printLog(name + ".enterRoom(Room)");
    	SkeletonUtil.increaseIndent();
    	if(SkeletonUtil.binaryQuestion("Befér-e a szobába?")) {
    		new Room().getCapacity();
    		new Room().getCharacters();
    		if(SkeletonUtil.binaryQuestion("Elátkozott-e a szoba?")) {
    			new CursedRoom().addCharacter(this);
				new Room().removeCharacter(this);
    		} else {
    			new Room().addCharacter(this);
				new Room().removeCharacter(this);
    		}
    	}
    	SkeletonUtil.decreaseIndent();
		return false;
    }

    /**
     * A paraméterül kapott tárgy felvételét valósítja meg.
     * @param i A felvevendő tárgy.
     * @return Ha az inventory-ban van hely, akkor a művelet sikeres és igaz értékkel tér vissza afüggvény, egyébként hamissal.
     */
    public boolean pickupItem(Item i) {
    	SkeletonUtil.printLog(name + ".pickupItem(Item)");
		SkeletonUtil.increaseIndent();
		this.getInventory();
		if(SkeletonUtil.binaryQuestion("Van hely a karakternél a tárgy számára?")) {
			new Room().removeItem(i);
			String[] opt = {
					"Söröspohár",		// 1
					"Maszk",			// 2
					"Táblatörlő rongy", // 3
					"Camembert",		// 4
					"Denevérbőr",		// 5
					"Logarléc",			// 6
					"Tranzisztor", 		// 7
			};
			switch(SkeletonUtil.question("Milyen tárgyat vesz fel a karakter?", opt)) {
			case 1, 3, 6:
				i.use();
			case 7:
				if(SkeletonUtil.binaryQuestion("Ez lesz a második tranzisztor nála?")) {
					Transistor t1 = new Transistor("tranzisztor_első");
					Transistor t2 = new Transistor("tranzisztor_második");
					t2.connect(t1);
				}
				break;
			default:
			}
			
		} else {
		}
		SkeletonUtil.decreaseIndent();
		return false;
    }

    /**
     * A paraméterül kapott tárgy letételét valósítja meg.
     * @param i A tárgy, amit le akar tenni.
     */
    public void putdownItem(Item i) {
    	SkeletonUtil.printLog(name + "putdownItem(Item)");
		SkeletonUtil.increaseIndent();
		String[] opt = {
				"Söröspohár",		// 1
				"Maszk",			// 2
				"Táblatörlő rongy", // 3
				"Camembert",		// 4
				"Denevérbőr",		// 5
				"Logarléc",			// 6
				"Tranzisztor", 		// 7
		};
		switch(SkeletonUtil.question("Milyen tárgyat tesz le a karakter?", opt)) {
		case 7:
			if(SkeletonUtil.binaryQuestion("Ez a tranzisztor össze van kötve egy másikkal?")) {
				if(SkeletonUtil.binaryQuestion("Ez lesz közülük a második, amit letesz?")) {
					if(SkeletonUtil.binaryQuestion("Aktiválva van?")) {
						new Transistor("tranzisztor_második").onDrop(this);
					}
				} else {
					new Transistor("tranzisztor_első").setLocation(new Room());
				}
			}
			break;
		default:
		}
		new Room().addItem(i);
		SkeletonUtil.decreaseIndent();
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
		SkeletonUtil.printLog(name + ".getInventory()");
		SkeletonUtil.increaseIndent();
		SkeletonUtil.decreaseIndent();
	}

	/**
	 * Beállítja a karakter stunned állapotát.
	 * @param b Ha elkábult a karakter, akkor igaz, egyébként hamis.
	 */
	public void setStunned(boolean b) {
		SkeletonUtil.printLog(name + ".setStunned()");
		SkeletonUtil.increaseIndent();
		SkeletonUtil.decreaseIndent();
	}
}