package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy hamis denevér bőrt reprezentál, amely a BatSkinből származik.
 */
public class FakeBatSkin extends BatSkin{
    
    /**
     * A FakeBatSkin osztály konstruktora.
     */
    public FakeBatSkin() {
        sticky=false;
        RemainingUses=0; // Nincs maradék használat
    }

    /**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
	}
    
    /**
     * A tárgy használatának metódusa.
     */
    @Override
    public void use() {
        ProtoUtil.printLog("use"); // Logolás
    }

}