package Items;

import EnvironmentalFactor.Gas;
import Map.Room;
import SkeletonUtil.SkeletonUtil;

/**
 * A Káposztás Camembert osztály felelős mérges gáz kibocsátására egy adott szobában egyszeri alkalommal. 
 * Amikor a hallgató aktiválja ezt a tárgyat akkor elhasználódik és többet már nem lehet használni.
 */
public class CabbageCamembert extends NumberOfUsesItem {

	/**
     * Konstruktor egy tárgy létrehozásához. CabbageCamembert-ra állítja a nevet.
     */
	public CabbageCamembert() {
		name="CabbageCamembert";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public CabbageCamembert(String n) {
		name=n;
	}
	
	/**
	 * A camambert kinyitása. Amikor a hallgató használja, 
	 * akkor gázt bocsát abba a szobába, melyben a hallgató van.
	 */
    @Override
    public void use() {
    	SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
		new Gas().create();
		new Room().addEnvironmentalFactor(new Gas());
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
}