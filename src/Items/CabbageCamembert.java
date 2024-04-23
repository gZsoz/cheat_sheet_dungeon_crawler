package Items;

import EnvironmentalFactor.Gas;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * A Káposztás Camembert osztály felelős mérges gáz kibocsátására egy adott szobában egyszeri alkalommal. 
 * Amikor a hallgató aktiválja ezt a tárgyat akkor elhasználódik és többet már nem lehet használni.
 */
public class CabbageCamembert extends NumberOfUsesItem {

	/**
     * Konstruktor egy tárgy létrehozásához. CabbageCamembert-ra állítja a nevet.
     */
	public CabbageCamembert() {
		owner=null;
		sticky=false;
		RemainingUses=1;
	}
		
	/**
	 * A camambert kinyitása. Amikor a hallgató használja, 
	 * akkor gázt bocsát abba a szobába, melyben a hallgató van.
	 */
    @Override
    public void use() {
    	ProtoUtil.printLog("use");
		Gas g=new Gas(owner.getRoom());
		owner.getRoom().addEnvironmentalFactor(g);
		if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
    }

    /**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
	}
}