package Model.Items.SpecialItems;

import java.util.ArrayList;

import Main.Main;
import Model.Items.Item;
import Model.Map.Room;

/**
 * Felelős egy másik ugyanilyen tárggyal való összekapcsolódásért, amennyiben a két tárgy ugyanannak a hallgatónak az inventory-jába kerül. 
 * Ha össze van már kapcsolva, felelős a párjának nyilvántartásáért és a 
 * két tranzisztor lehelyezése esetén az adott karakter megfelelő helyre történő teleportálásáért.
 */
public class Transistor extends Item {
	
	/**
	 * A transistor párja.
	 */
	private Transistor pair;
	
	/**
	 * Melyik szobában van a tranzisztor.
	 */
	private Room location;
	
	/**
	 * Be van-e aktiválva a tranzisztor.
	 */
	private boolean active;
	
	/**
	 * A transistor párjának lekérdezése.
	 * @return a tranzisztor párja
	 */
	public Transistor getPair(){
		Main.printLog("getPair");
		return pair;
	}
	
	/**
	 * Beállítja ennek a tranzisztornak a párját.
	 * @param t a tranzisztor amellyel párosul
	 */
	public void setPair(Transistor t){
		Main.printLog("setPair");
		pair=t;
		notifySubsribers("pair");
	}
	
	/**
	 * Visszaadja ennek a tranzisztornak a helyét, ahol letették.
	 * @return a szoba ahova letették
	 */
	public Room getLocation() {
		Main.printLog("getLocation");
		return location;
	}
	
	/**
	 * Beállítja ennek a tranzisztornak a helyét, ahol letették.
	 * @param r a szoba ahova letették
	 */
	public void setLocation(Room r) {
		Main.printLog("setLocation");
		location = r;
	}
	
	/**
	 * Lekérdezi, hogy be van-e aktiválva a tranzisztor.
	 * @return ha aktív a tranzisztor, akkor igaz, egyébként hamis
	 */
	public boolean getActive(){
		Main.printLog("getActive");
		return active;
	}
	
	/**
	 * Beállítja a tranzisztor állapotát.
	 * @param state ha aktív, akkor igaz, egyébként hamis
	 */
	public void setActive(boolean state){
		Main.printLog("setActive");
		active = state;
		notifySubsribers("isactive");
	}
	
	/**
	 * A hallgató használja a tranzisztor pár második elemét,
	 * így ha ezután lerakja azt, akkor átteleportál a pár első eleméhez.
	 * Ez a tranzisztor aktiválódik, vagy már aktiválva deaktiválódik.
	 */
	@Override
	public void use() {
		Main.printLog("use");
		setActive(!active);
		if(getPair() != null) {
			getPair().setActive(active);
		}
	}
	
	/**
	 * Elteleportálja a hallgatót a párjához, ha már párosítva van és a párja le van már rakva.
	 * @param c a karakter akit teleportál
	 */
	@Override
	public void onDrop() {
		Main.printLog("onDrop");
		if (pair != null) { // van párja
			if(pair.location == null) { // nincs letéve a párja, ez az első közülük, amit letesz
				setLocation(owner.getRoom());
			} else { // le van téve a párja, ez a második közülük, amit letesz
				if (active) {
					owner.enterRoom(pair.location);
				}
				
				// a tranzisztorok defaultra állítása és szétválasztása
				pair.location = null;
				pair.setActive(false);
				pair.setPair(null);
				setPair(null);
				setActive(false);
			}
		} else { // nincs párja
			setActive(false);
		}
	}
	
	/**
	 * Beállítja ennek a tranzisztornak a párját és a párjának is beállítja önmagát.
	 * @param t a tranzisztor akivel párosul
	 */
	public void connect(Transistor t) {
		Main.printLog("connect");
		setPair(t);
		t.setPair(this);
	}
	
	/**
	 * Az inventory-ban található tranzisztorokat kettesével összepárosítja.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp");
		ArrayList<Transistor> transistorsInInventory = new ArrayList<>();
		for(Item i: owner.getInventory()) {
			if(i instanceof Transistor) {
				Transistor transistorAtDisposal = (Transistor) i;
				if(transistorAtDisposal.getPair() == null)
					transistorsInInventory.add(transistorAtDisposal);
			}
		}
		for(int j = 0; j + 1 < transistorsInInventory.size(); j += 2) {
			transistorsInInventory.get(j).connect(transistorsInInventory.get(j + 1));
		}
	}
	
	/**
	 * A tranzisztor használatakor lefutó metódus.
	 */
	@Override
	public void onActivate() {
		use();
	}
}