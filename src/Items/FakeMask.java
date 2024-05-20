package Items;

import ProtoUtil.ProtoUtil;

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
		ProtoUtil.printLog("onPickUp"); // Logolás
	}
	
	/**
	 * A hamis maszk letételekor hívódik meg, ekkor a tárgynak nem lesz tulajdonosa.
	 */
	@Override
	public void onDrop() 
	{
		ProtoUtil.printLog("onDrop"); // Logolás
		owner=null;
	}
	
	/**
	 * A hamis maszk használatának metódusa. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void use() 
	{
		ProtoUtil.printLog("use"); // Logolás
	}
	
	/**
	 * A hamis maszk frissítése. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void update() {}
}