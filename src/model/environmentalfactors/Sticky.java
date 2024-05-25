package model.environmentalfactors;

import main.Main;
import model.items.Item;
import model.map.Room;

/**
 * Osztály a ragacs környezeti változó reprezentálására.
 */
public class Sticky extends EnvironmentalFactor {
	
	/**
	 * Ezzel a változóval állítható be a main-ban, hogy a takarító takarítása után mennyi karakter belépése után válik ragacsossá egy szoba.
	 */
	public static int defaultRemainingEntries=2;
	
	/**
	 * A takarító takarítása után ennyi karakter belépése után válik ragacsossá egy szoba.
	 */
	private int remainingEntries=defaultRemainingEntries;
	
	/**
	 * Kontruktor ragacs létrehozásához.
	 * @param r a szoba, ahol a környezeti változó van
	 */
	public Sticky(Room r){
		location = r;
	}
	
	/**
	 * Lekérdezi a hátralévő karakterek számát, amely után ragacsossá teszi a szobát.
	 * @return a hátralévő emberek száma
	 */
	public int getRemainingEntries(){
		Main.printLog("getRemainingEntries");
		return remainingEntries;
	}
	
	/**
	 * Beállítja a hátralévő karakterek számát, amely után ragacsossá teszi a szobát.
	 * @param re a beállítandó emberek száma.
	 */
	public void reduceRemainingEntries(){
		Main.printLog("setRemainingEntries");
		location.notifySubscribers("factors");
		remainingEntries--;
	}
	
	/**
	 * Ragacsossá teszi a paraméterként kapott tárgyat.
	 * @param i ragacsossá teendő tárgy.
	 */
	private void makeSticky(Item i){
		Main.printLog("makeSticky");
		i.setSticky(true);
	}
	
	/**
	 * Minden adott időben, ha elértük, hogy elég karakter megforduljon a szobában,
	 * akkor ragacsossá teszi az összes itemet a szobában.
	 */
	@Override
	public void update() {
		for(Item item: getLocation().getItems()){
			if(remainingEntries <= 0){
				makeSticky(item);
			}else {
				item.setSticky(false);
			}
		}
	}
}