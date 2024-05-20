package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy hamis denevér bőrt reprezentál, amely a BatSkinből származik.
 */
public class FakeBatSkin extends BatSkin {
	
	/**
	 * Konstruktor egy hamis denevérbőr létrehozásához.
	 */
	public FakeBatSkin() {
	    sticky=false;
	    RemainingUses=0; // Nincs maradék használat
	}
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amelyeknek a tárgy felvételénél kell megtörténniük.
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
	}
	
	/**
	 * A tárgy használatának metódusa. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void use() {
	    ProtoUtil.printLog("use"); // Logolás
	}

}