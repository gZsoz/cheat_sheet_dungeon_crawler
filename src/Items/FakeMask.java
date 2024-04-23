package Items;

import ProtoUtil.ProtoUtil;

public class FakeMask extends Mask{
	/*
	 * Nem csinál semmit
	 */
	@Override
	public void use() 
	{
		ProtoUtil.printLog("use");
	}
}
