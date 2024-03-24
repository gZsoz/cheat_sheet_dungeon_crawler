package Items;

import Map.Room;
import SkeletonUtil.SkeletonUtil;
import Character.*;
import Character.Character;

/**
 * Felelős egy másik ugyanilyen tárggyal való összekapcsolódásért, 
 * amennyiben a két tárgy ugyanannak a hallgatónak az inventory-jába kerül. 
 * Ha össze van már kapcsolva, felelős a párjának nyilvántartásáért és a 
 * két tranzisztor lehelyezése esetén az adott karakter megfelelő helyre történő teleportálásáért.
 */
public class Transistor extends Item {

	/**
     * Konstruktor egy tranzisztor létrehozásához. Tranzisztor-ra állítja a nevet.
     */
	public Transistor() {
		name="Transistor";
	}
	
	/**
     * Konstruktor egy Tranzisztor létrehozásához.
     * @param n A Tranzisztor neve
     */
	public Transistor(String n) {
		name=n;
	}
	
	/**
     * A hallgató használja a tranzisztor pár második elemét, 
     * így ha ezután kidobja azt, akkor át teleportál a pár első eleméhez.
     */
    @Override
    public void use() {
    	SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * elteleportálja a hallgatót a párjához, ha már párosítva van és a párja le van már rakva, 
     * egyébként nem csinál semmit.
     * @param c A Karakter akit teleportál
     */
    public void onDrop(Character c) {
    	SkeletonUtil.printLog(name+".onDrop("+c.name+")");
		SkeletonUtil.increaseIndent();
		c.enterRoom(new Transistor().getLocation());
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Beállítja ennek a Transistornak a párját és a párjának is beállítja önmagát.
     * @param t A Tranzisztor akivel párosul
     */
    public void connect(Transistor t) {
    	SkeletonUtil.printLog(name+".connect("+t.name+")");
		SkeletonUtil.increaseIndent();
		t.setPair(this);
    	SkeletonUtil.decreaseIndent();
    }
    /**
     * Beállítja ennek a Transistornak a párját.
     * @param t A Tranzisztor akivel párosul
     */
	public void setPair(Transistor t) {
		SkeletonUtil.printLog(name+".setPair("+t.name+")");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	/**
     * Beállítja ennek a Transistornak a helyét, ahol letették.
     * @param r A szoba ahova letették
     */
	public void setLocation(Room r) {
		SkeletonUtil.printLog(name+".setLocation("+r.name+")");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	/**
     * visszaadja ennek a Transistornak a helyét, ahol letették.
     * @return A szoba ahova letették
     */
	public Room getLocation() {
		SkeletonUtil.printLog(name+".getLocation()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    	return new Room();
	}
	
	/**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
		if(SkeletonUtil.binaryQuestion("Ez lesz a második tranzisztor nála?")) {
			Transistor t1 = new Transistor("tranzisztor_első");
			Transistor t2 = new Transistor("tranzisztor_második");
			t2.connect(t1);
		}
    	SkeletonUtil.decreaseIndent();
	}
}