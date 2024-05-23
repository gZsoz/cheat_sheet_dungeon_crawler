package Model.Items.DecayingItems;

import main.Main;

/**
 * Az osztály egy hamis maszkot reprezentál, amely a Maskból származik.
 */
public class FakeMask extends Mask {
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amelyeknek a tárgy felvételénél kell megtörténniük.
	 */
	@Override
	public void onPickUp() 
	{
		Main.printLog("onPickUp"); // Logolás
	}
	
	/**
	 * A hamis maszk letételekor hívódik meg, ekkor a tárgynak nem lesz tulajdonosa.
	 */
	@Override
	public void onDrop() 
	{
		Main.printLog("onDrop"); // Logolás
		owner=null;
	}
	
	/**
	 * A hamis maszk használatának metódusa. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void use() 
	{
		Main.printLog("use"); // Logolás
	}
	
	/**
	 * A hamis maszk frissítése. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void update() {}
}