package EnvironmentalFactor;

import Items.Item;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Class representing gas environmental factor in the game.
 */
public class Sticky extends EnvironmentalFactors {
	public static int defaultRemainingEntries=2;
	/**
	 * Kontruktor létrehozza, a sticky objektumot
	 * @param r Adott szoba
	 */
	public Sticky(Room r){
		location = r;
	}
	/**
	 * A hátralévő emberek száma, amelyek után ragacsossá teszi a szobát
	 */
	private int remainingEntries=defaultRemainingEntries;

	/**
	 * Lekérdezi a hátralévő emberek számát, amelyek után ragacsossá teszi a szobát
	 * @return Hátralévő emberek száma
	 */
	public int getRemainingEntries(){
		ProtoUtil.printLog("getRemainingEntries");
		return remainingEntries;
	}

	/**
	 * Beállítja a hátralévő emberek számát, amelyek után ragacsossá teszi a szobát
	 * @param re A beállítandó emberek száma
	 */
	public void reduceRemainingEntries(){
		ProtoUtil.printLog("setRemainingEntries");
		remainingEntries--;
	}

	/**
	 * Ragacsossá teszi a paraméterként kapott tárgyat
	 * @param i Ragacsossá teendő tárgy
	 */
	private void makeSticky(Item i){
		ProtoUtil.printLog("makeSticky");
		i.setSticky(true);
	}

	/**
     * Minden adott időben, ha elértük, hogy elég ember megforduljon a szobában,
	 * akkor ragacsossá teszi az összes itemet a szobában
	 */
	@Override
	public void update() {
		for(Item item: getLocation().getItems()){
			if(remainingEntries <= 0){
				makeSticky(item);
			}
		}
	}
}