package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy maszk tárgyat reprezentál a játékban.
 */
public class Mask extends DecayingItems {
	
	/**
     * A maszk használatának metódusa.
     * Aktiválja a tárgyat, lehetővé téve a védelmet és az időtartam csökkenését.
     */
	@Override
    public void use() {
    	ProtoUtil.printLog("use"); // Logolás
    	isActive=true; // Aktiválás jelzése
    	reduceDuration(); // Időtartam csökkentése
    }
	
	/**
	 * A tárgy felvételekor végrehajtott műveletek.
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp"); // Logolás
		owner.setHasDefense(true); // Védelem beállítása
	}
	
	/**
	 * A tárgy eldobásakor végrehajtott műveletek.
	 */
	@Override
	public void onDrop() {
		ProtoUtil.printLog("onDrop"); // Logolás
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
	
	@Override
	public void update(){}
	
}
