package Items;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;
import Map.Room;
import ProtoUtil.ProtoUtil;

public class AirFreshener extends NumberOfUsesItem {

    public AirFreshener() {
		Owner=null;
		sticky=false;
		RemainingUses=1;
	}

    @Override
    public void use() {
        ProtoUtil.printLog("use");
        if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
		for(EnvironmentalFactors a : Owner.getRoom().getEnvironmentalFactors()){
            if(a instanceof Gas){
                Owner.getRoom().getEnvironmentalFactors().remove(a);
                return;
            }
        }
    }

    @Override
    public void onPickUp() {
        ProtoUtil.printLog("onPickUp");
    }

}
