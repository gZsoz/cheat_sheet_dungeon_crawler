package Items;

import EnvironmentalFactor.Gas;
import ProtoUtil.ProtoUtil;

/**
 * A Káposztás Camembert osztály felelős mérges gáz kibocsátására egy adott szobában egyszeri alkalommal. 
 * Amikor a hallgató aktiválja ezt a tárgyat akkor elhasználódik és többet már nem lehet használni.
 */
public class CabbageCamembert extends NumberOfUsesItem {
	
	/**
	 * Az alapértelmezett maradék használatok száma.
	 */
	public static int defaultRemainingUses = 1;
	
	/**
	 * Konstruktor egy tárgy létrehozásához. CabbageCamembert-ra állítja a nevet.
	 */
	public CabbageCamembert() {
		owner=null;
		sticky=false;
		RemainingUses=defaultRemainingUses;
	}
		
	/**
	 * A camambert kinyitása. Amikor a hallgató használja, akkor gázt bocsát abba a szobába, amelyben a hallgató van.
	 */
	@Override
	public void use() {
		ProtoUtil.printLog("use");
		Gas g=new Gas(owner.getRoom());
		owner.getRoom().spawnEnvironmentalFactor(g);
		if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
	}
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amelyeknek a tárgy felvételénél kell megtörténniük.
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