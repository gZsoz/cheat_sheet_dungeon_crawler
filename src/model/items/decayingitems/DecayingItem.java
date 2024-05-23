package model.items.decayingitems;

import main.Main;
import model.items.Item;
import model.modelupdate.iTask;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItem extends Item implements iTask {
	
	/**
	 * Ezzel a változóval állítható be a main-ban az alapértelmezett lejárati idő.
	 */
	public static int defaultDuration = 14 * Main.fps;
	
	/**
	 * Lejárati idő.
	 */
	protected int duration = defaultDuration;
	
	/**
	 * Aktív-e éppen a tárgy.
	 */
	protected boolean isActive = false;
	
	/**
	 * Lejárati idő lekérdezése.
	 * @return a lejárati idő
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * A duration attribútum értékét csökkenti a Model.Time osztály időmérése alapján,
	 * amennyiben a usable értéke true, illetve átbillenti azt hamis értékre, 
	 * ha a duration eléri a 0-t.
	 */
	public void reduceDuration() {
		Main.printLog("reduceDuration");
		duration--;
		if(duration==0) {
			notifySubscribers("itemexpired");
			int idx = owner.getInventory().indexOf(this);
			if(!owner.getInventory().remove(this)) {
				System.out.println("Olyan DecayingItem lett eltávolítva az inventoryból, ami nincs benne az inventoryban!");
			}
			owner.notifySubscribers("inventory removed "+idx);
			onDrop();
			Main.printLog("Decaying item expired and removed.");
		}
	}
	
	/**
	 * A tárgy aktív állapotának lekérdezése.
	 * @return a tárgy aktív állapota
	 */
	public boolean getIsActive() {
		Main.printLog("getIsActive");
		return isActive;
	}
	
	/**
	 * A tárgy aktív állapotának beállítása.
	 * @param isactive a tárgy aktív állapota
	 */
	public void setIsActive(boolean isactive) {
		Main.printLog("setIsActive");
		notifySubscribers("isactive");
			this.isActive = isactive;
		}
	   
	    /**
	 * A gyermekosztályok által leírt tárgyak használatát és hatását elindító függvény, 
	 * a gyermekosztályok implementálják. Item ősosztályból örökölt metódus.
	 */
	public abstract void use();
	
	/**
	 * Az időérzékeny tárgyak frissítése.
	 */
	@Override
	public void update() {
		if(duration>0 && isActive) reduceDuration();
	}
}