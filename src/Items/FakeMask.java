package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy hamis maszkot reprezentál, amely a Maskból származik.
 */
public class FakeMask extends Mask{

	/**
     * A FakeMask osztály használatának metódusa.
     */
	@Override
	public void use() 
	{
		ProtoUtil.printLog("use"); // Logolás
	}
	
	@Override
	public void onPickUp() 
	{
		ProtoUtil.printLog("onPickUp"); // Logolás
	}
	
	@Override
	public void onDrop() 
	{
		ProtoUtil.printLog("onDrop"); // Logolás
		owner=null;
	}
	
	@Override
	public void update() {}
}