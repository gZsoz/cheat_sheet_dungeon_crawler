package Items;

import java.util.ArrayList;

import Map.Room;
import ProtoUtil.ProtoUtil;

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
		notifySubsribers("pair");
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
		notifySubsribers("isactive");
	}

	/**
	 * A hallgató használja a tranzisztor pár második elemét,
	 * így ha ezután kidobja azt, akkor át teleportál a pár első eleméhez.
	 * Ez a Transistort aktiválódik, vagy már aktiválva deaktiválódik
	 */
	@Override
	public void use() {
		ProtoUtil.printLog("use");
		setActive(!active);
		if(getPair() != null) {
			getPair().setActive(active);
		}
	}

    /**
     * Elteleportálja a hallgatót a párjához, ha már párosítva van és a párja le van már rakva,
     * egyébként nem csinál semmit.
     * @param c A Karakter akit teleportál
     */
	@Override
    public void onDrop() {
    	ProtoUtil.printLog("onDrop");
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
     * Beállítja ennek a Transistornak a párját és a párjának is beállítja önmagát.
     * @param t A Tranzisztor akivel párosul
     */
    public void connect(Transistor t) {
    	ProtoUtil.printLog("connect");
		setPair(t);
		t.setPair(this);
    }

    /**
     * Az inventory-ban található tranzisztorokat kettesével összepárosítja
     */
    @Override
    public void onPickUp() {
    	ProtoUtil.printLog("onPickUp");
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
     * A tárgy használatakor lefutó metódus.
     */
    @Override
    public void onActivate() {
    	use();
    }
}