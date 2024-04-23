package Items;

import ProtoUtil.ProtoUtil;

public class FakeSlideRule extends SlideRule{

    public FakeSlideRule() {
		sticky=false;
		RemainingUses=0;
	}

    @Override
    public void use() {
		ProtoUtil.printLog("use");
		if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
    }

}
