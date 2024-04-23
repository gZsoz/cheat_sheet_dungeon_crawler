package Items;

import Map.Room;
import ProtoUtil.ProtoUtil;
import Character.Character;

/**
 * Felelős egy másik ugyanilyen tárggyal való összekapcsolódásért, 
 * amennyiben a két tárgy ugyanannak a hallgatónak az inventory-jába kerül. 
 * Ha össze van már kapcsolva, felelős a párjának nyilvántartásáért és a 
 * két tranzisztor lehelyezése esetén az adott karakter megfelelő helyre történő teleportálásáért.
 */
public class Transistor extends Item {

	/**
	 * A transistor párja
	 */
	private Transistor pair;

	/**
	 * Melyik szobában van a tranzisztor
	 */
	private Room location;

	/**
	 * A tranzisztor be lett-e kapcsolva/aktiválva
	 */
	private boolean active;

	/**
	 * A transistor párjának lekérdezése
	 * @return A transistor párja
	 */
	public Transistor getPair(){
		ProtoUtil.printLog("getPair");
		return pair;
	}

	/**
	 * Beállítja ennek a Transistornak a párját.
	 * @param t A Tranzisztor akivel párosul
	 */
	public void setPair(Transistor t){
		ProtoUtil.printLog("setPair");
		pair=t;
	}

	/**
	 * Visszaadja ennek a Transistornak a helyét, ahol letették.
	 * @return A szoba ahova letették
	 */
	public Room getLocation() {
		ProtoUtil.printLog("getLocation");
		return location;
	}

	/**
	 * Beállítja ennek a Transistornak a helyét, ahol letették.
	 * @param r A szoba ahova letették
	 */
	public void setLocation(Room r) {
		ProtoUtil.printLog("setLocation");
		location = r;
	}

	//● private void setActive(boolean state): aktiválás=true, inaktiválás=false

	/**
	 * Lekérdezi, hogy be lett e aktiválva a Transistor
	 * @return Transistor aktív-e
	 */
	public boolean getActive(){
		ProtoUtil.printLog("getActive");
		return active;
	}

	/**
	 * Beállítja a Transistor állapotát, aktív=true, inaktív=false
	 */
	public void setActive(boolean state){
		ProtoUtil.printLog("setActive");
		active = state;
	}

	/**
	 * A hallgató használja a tranzisztor pár második elemét,
	 * így ha ezután kidobja azt, akkor át teleportál a pár első eleméhez.
	 * Ez a Transistort aktiválódik, vagy már aktiválva deaktiválódik
	 */
	@Override
	public void use() {
		ProtoUtil.printLog("use");
		active = !active;
	}

    /**
     * Elteleportálja a hallgatót a párjához, ha már párosítva van és a párja le van már rakva,
     * egyébként nem csinál semmit.
     * @param c A Karakter akit teleportál
     */
    public void onDrop(Character c) {
    	ProtoUtil.printLog("onDrop");
		if (active) {
			if (pair != null) { // van párja
				if(pair.location == null) { // nincs letéve a párja, ez az első közülük, amit letesz
					location = c.getRoom();
				} else { // le van téve a párja, ez a második közülük, amit letesz
					c.enterRoom(pair.location);
					
					// a felhasznált tranzisztorok defaultra állítása és szétválasztása
					pair.location = null;
					pair.active = false;
					pair.pair = null;
					pair = null;
					active = false;
				}
			} else { // nincs párja
				active = false;
			}
		}
    }

    /**
     * Beállítja ennek a Transistornak a párját és a párjának is beállítja önmagát.
     * @param t A Tranzisztor akivel párosul
     */
    public void connect(Transistor t) {
    	ProtoUtil.printLog("connect");
		setPair(t);
		t.setPair(this);
    }

	@Override
	public void onPickUp() {
	}
}