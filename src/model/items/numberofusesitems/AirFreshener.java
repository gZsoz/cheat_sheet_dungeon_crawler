package model.items.numberofusesitems;

import main.Main;
import model.environmentalfactors.EnvironmentalFactor;
import model.environmentalfactors.Gas;
import model.map.Room;

/**
 * Az AirFreshener osztály egy használati tárgyat reprezentál, amely egy légfrissítőt jelképez.
 */
public class AirFreshener extends NumberOfUsesItem {
	
	/**
	 * Az alapértelmezett maradék használatok száma.
	 */
	public static int defaultRemainingUses = 1;
	
	/**
	 * Konstruktor egy légfrissítő létrehozásához.
	 */
	public AirFreshener() {
	    owner=null;
	    sticky=false;
	    RemainingUses=defaultRemainingUses; // Az alapértelmezett maradék használatok beállítása
	}
	
	/**
	 * A tárgy használatának metódusa. Ha tulajdonosának szobája gázzal teli, megszünteti benne a gázt.
	 */
	@Override
	public void use() {
	    Main.printLog("use");
	    if(RemainingUses>0) setRemainingUses(RemainingUses-1); // Csökkenti a maradék használatok számát
	    Room r = owner.getRoom();
	    
	    boolean roomHasGas = r.getEnvironmentalFactors().stream().anyMatch(n -> n.getClass().equals(Gas.class));
		if(roomHasGas){
			EnvironmentalFactor expired=null;
			for(EnvironmentalFactor env: r.getEnvironmentalFactors())
				if(env instanceof Gas)
					expired=env;
			r.removeEnvironmentalFactor(expired); // gáz megszűntetése
			r.notifySubscribers("factors");
			Main.printLog("airfreshener removed gas");
		}
	}
	
	/**
	 * A tárgy felvételekor lefutó metódus.
	 */
	@Override
	public void onPickUp() {
	    Main.printLog("onPickUp");
	}
	
	/**
	 * A tárgy aktiválásakor lefutó metódus.
	 */
	@Override
	public void onActivate() {
		use();
	}
}