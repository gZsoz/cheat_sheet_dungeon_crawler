package Items;

import ProtoUtil.ProtoUtil;

/**
 * A tárgyhoz tartozó objektum feladata, hogy veszély esetén megvédje a karaktereket 
 * az oktatók szipolyozásától és hogy nyilvántartsa a védelemnyújtások számát. 
 * Abban az esetben ha elfogytak a használási lehetőségek, a karakterek szempontjából 
 * használhatatlanná kell tegye magát.
 */
public class BatSkin extends NumberOfUsesItem {
    
	/**
     * Konstruktor egy tárgy létrehozásához. BatSkin-ra állítja a nevet.
     */
	public BatSkin() {
		name="BatSkin";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public BatSkin(String n) {
		name=n;
	}
	
	/**
	 * Megvédi a hallgatót az oktatókkal szemben. Automatikusan használódik, 
	 * amikor a hallgatót kirúgnák az egyetemről és védelmet nyújt nekik 
	 * amíg az adott szobából ki nem mennek, hogy legyen esélyük elmenekülni az oktató elől.
	 */
	@Override
    public void use() {
    	ProtoUtil.printLog(name+".use()");
		ProtoUtil.increaseIndent();
		this.setRemainingUses();
    	ProtoUtil.decreaseIndent();
    }
	/**
     * Levon a fennmaradó használatok számából egyet.
     */
	public void setRemainingUses() {
		ProtoUtil.printLog(name+".setRemainingUses()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
	}

	/**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog(name+".onPickUp()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
	}
}