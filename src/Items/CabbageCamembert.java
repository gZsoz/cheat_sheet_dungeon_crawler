package Items;

import EnvironmentalFactor.Gas;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * A Káposztás Camembert osztály felelős mérges gáz kibocsátására egy adott szobában egyszeri alkalommal. 
 * Amikor a hallgató aktiválja ezt a tárgyat akkor elhasználódik és többet már nem lehet használni.
 */
public class CabbageCamembert extends NumberOfUsesItem {

	private Room location;
	/**
     * Konstruktor egy tárgy létrehozásához. CabbageCamembert-ra állítja a nevet.
     */
	public CabbageCamembert() {
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

	/**
	 * A camambert kinyitása. Amikor a hallgató használja, 
	 * akkor gázt bocsát abba a szobába, melyben a hallgató van.
	 */
    @Override
    public void use() {
    	ProtoUtil.printLog("use");
		Gas g=new Gas();
		location.addEnvironmentalFactor(g);
		this.setRemainingUses(getRemainingUses()-1);
    }

    /**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
	}
}