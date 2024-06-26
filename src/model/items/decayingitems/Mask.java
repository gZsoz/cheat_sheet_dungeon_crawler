package model.items.decayingitems;

import main.Main;
import model.items.Item;

/**
 * Az osztály egy maszk tárgyat reprezentál a játékban.
 */
public class Mask extends DecayingItem {
	
	/**
	 * A tárgy felvételekor végrehajtott műveletek.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp"); // Logolás
		owner.setHasDefense(true); // Védelem beállítása
	}
	
	/**
	 * A tárgy eldobásakor végrehajtott műveletek.
	 */
	@Override
	public void onDrop() {
		Main.printLog("onDrop"); // Logolás
		isActive=false; // Aktiválás kikapcsolása
		boolean mask=false;
		for(Item i : owner.getInventory()) {
			if(i instanceof Mask && !(i instanceof FakeMask)) {
				mask=true;
				break;
			}
		}
		if(!mask) owner.setHasDefense(false); // Védelem kikapcsolása
		
		super.onDrop();
	}
	
	/**
	 * A maszk használatának metódusa.
	 * Aktiválja a tárgyat, lehetővé téve a védelmet és az időtartam csökkenését.
	 */
	@Override
	public void use() {
		Main.printLog("use"); // Logolás
		isActive=true; // Aktiválás jelzése
		reduceDuration(); // Időtartam csökkentése
	}
	
	/**
	 * A maszk frissítése.
	 */
	@Override
	public void update(){}

}
