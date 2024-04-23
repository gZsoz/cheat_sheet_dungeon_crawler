package Items;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;
import Map.Room;
import ProtoUtil.ProtoUtil;

public class AirFreshener extends NumberOfUsesItem {

    private Room location;

    public AirFreshener() {
		location=null;
		sticky=false;
		RemainingUses=1;
	}

    public Room getLocation(){
		return location;
	}

	public void setLocation(Room l){
		location=l;
	}

    @Override
    public void use() {
        ProtoUtil.printLog("use");
        if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
		for(var a : location.getEnvironmentalFactors()){
            if(a instanceof Gas){
                location.getEnvironmentalFactors().remove(a);
                return;
            }
        }
    }

    @Override
    public void onPickUp() {
        ProtoUtil.printLog("onPickUp");
    }

}
