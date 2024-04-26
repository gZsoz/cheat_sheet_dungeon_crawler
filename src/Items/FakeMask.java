package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy hamis maszkot reprezentál, amely a Maskból származik.
 */
public class FakeMask extends Mask{
	/*
	 * Nem csinál semmit
	 */

	/**
     * A FakeMask osztály használatának metódusa.
     */
	@Override
	public void use() 
	{
		ProtoUtil.printLog("use"); // Logolás
	}
	
	@Override
	public void update() {}
}