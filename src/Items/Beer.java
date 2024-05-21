package Items;

import Character.Student;
import Main.Main;

/**
 * Az osztály a Beer tárgy reprezentálására szolgál.
 */
public class Beer extends DecayingItem {
	
	/**
	 * Lerakat egy tárgyat a karakterrel, amely felveszi a sört.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp");
		use(); // Használat elindítása
		if(!owner.getInventory().isEmpty()) {
			int rand=Main.random.nextInt(owner.getInventory().size(),0);
			owner.putdownItem(owner.getInventory().get(rand)); // Véletlenszerű tárgy eldobása
		}
	}
	
	/**
	 * A tárgy aktivitásának és a tárgyat letevő karakter immunitásának beállítása.
	 */
	@Override
	public void onDrop() {
		Main.printLog("onDrop");
		isActive=false; // Aktiválás kikapcsolása
		boolean beer=false;
		for(Item i : owner.getInventory()) {
			if(i instanceof Beer) {
				beer=true;
				break;
			}
		}
		if((!beer) && owner instanceof Student) ((Student)owner).setInvincible(false); // Immunitás kikapcsolása
		
		super.onDrop();
	}
	
	/**
	 * Sör használatakor (felvételekor) végrehajtott műveletek, amelyek immunitást adnak a használónak
	 * és minden másodpercben csökkentik a hatás időtartamát.
	 */
	@Override
	public void use() {
		Main.printLog("use");
		if(owner instanceof Student) ((Student)owner).setInvincible(true); // Immunitás adása
		setIsActive(true); // Aktiválás jelzése
	}
}