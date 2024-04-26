package Items;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;
import ProtoUtil.ProtoUtil;

public class AirFreshener extends NumberOfUsesItem {

    public AirFreshener() {
		owner=null;
		sticky=false;
		RemainingUses=1;
	}

    @Override
    public void use() {
        ProtoUtil.printLog("use");
        if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
		for(EnvironmentalFactors a : owner.getRoom().getEnvironmentalFactors()){
            if(a instanceof Gas){
                owner.getRoom().getEnvironmentalFactors().remove(a);
                return;
            }
        }
    }

    @Override
    public void onPickUp() {
        ProtoUtil.printLog("onPickUp");
    }

}
