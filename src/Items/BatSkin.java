package Items;

import Character.Student;
import ProtoUtil.ProtoUtil;

/**
 * A tárgyhoz tartozó objektum feladata, hogy veszély esetén megvédje a karaktereket 
 * az oktatók szipolyozásától és hogy nyilvántartsa a védelemnyújtások számát. 
 * Abban az esetben ha elfogytak a használási lehetőségek, a karakterek szempontjából 
 * használhatatlanná kell tegye magát.
 */
public class BatSkin extends NumberOfUsesItem {
	
	public static int defaultRemainingUses = 3;
	
	/**
     * Konstruktor egy tárgy létrehozásához. BatSkin-ra állítja a nevet.
     */
	public BatSkin() {
		sticky=false;
		RemainingUses=defaultRemainingUses;
	}
		
	/**
	 * Megvédi a hallgatót az oktatókkal szemben. Automatikusan használódik, 
	 * amikor a hallgatót kirúgnák az egyetemről és védelmet nyújt nekik 
	 * amíg az adott szobából ki nem mennek, hogy legyen esélyük elmenekülni az oktató elől.
	 */
	@Override
	public void use() {
        ProtoUtil.printLog("use");
        if(owner instanceof Student) ((Student)owner).setInvincible(true);
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