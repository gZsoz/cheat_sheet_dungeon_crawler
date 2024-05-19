package Items;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Az AirFreshener osztály egy használati tárgyat reprezentál, amely levegőillatosítót jelképez.
 */
public class AirFreshener extends NumberOfUsesItem {

    public static int defaultRemainingUses = 1; // Az alapértelmezett maradék használatok száma

    /**
     * Az AirFreshener osztály konstruktora.
     */
    public AirFreshener() {
        owner=null;
        sticky=false;
        RemainingUses=defaultRemainingUses; // Az alapértelmezett maradék használatok beállítása
    }

    /**
     * A tárgy használatának metódusa.
     */
    @Override
    public void use() {
        ProtoUtil.printLog("use");
        if(RemainingUses>0) setRemainingUses(RemainingUses-1); // Csökkenti a maradék használatok számát
        Room r = owner.getRoom();
        
        boolean roomHasGas = r.getEnvironmentalFactors().stream().anyMatch(n -> n.getClass().equals(Gas.class));
		if(roomHasGas){
			EnvironmentalFactors expired=null;
			for(EnvironmentalFactors env: r.getEnvironmentalFactors())
				if(env instanceof Gas)
					expired=env;
			r.removeEnvironmentalFactor(expired); // gáz megszűntetése
			r.notifySubsribers("factors");
			ProtoUtil.printLog("airfreshener removed gas");
		}
    }

    /**
     * A tárgy felvételekor lefutó metódus.
     */
    @Override
    public void onPickUp() {
        ProtoUtil.printLog("onPickUp");
    }
    
    /**
     * A tárgy használatakor lefutó metódus.
     */
    @Override
    public void onActivate() {
    	use();
    }
}