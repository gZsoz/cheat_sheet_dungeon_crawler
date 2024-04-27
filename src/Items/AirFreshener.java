package Items;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;
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
        if(RemainingUses>0) this.setRemainingUses(RemainingUses-1); // Csökkenti a maradék használatok számát
        for(EnvironmentalFactors a : owner.getRoom().getEnvironmentalFactors()){
            if(a instanceof Gas){
                owner.getRoom().getEnvironmentalFactors().remove(a); // Távolítja el a gázt a szobából
                ProtoUtil.printLog("airfreshener removed gas");
                return;
            }
        }
    }

    /**
     * A tárgy felvételekor lefutó metódus.
     */
    @Override
    public void onPickUp() {
        ProtoUtil.printLog("onPickUp");
    }
}