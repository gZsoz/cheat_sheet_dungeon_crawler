package Items;

import ProtoUtil.ProtoUtil;

/**
 * A logarléc osztályból az összes többi tárggyal ellentétben csupán egy lehet a pályán, 
 * ez felel a játék egyik lehetséges végkimeneteléért, a hallgatók győzelméért, 
 * amely akkor következik be, ha az objektum valamelyik hallgatónak az inventory-jába kerül.
 */
public class SlideRule extends NumberOfUsesItem {
    
	/**
     * Konstruktor egy tárgy létrehozásához. SlideRule-ra állítja a nevet.
     */
	public SlideRule() {
		name="SlideRule";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public SlideRule(String n) {
		name=n;
	}
	
	/**
	 * A logarléc használata. Ezzel a játékot megnyerik a hallgatók.
	 */
	@Override
    public void use() {
		ProtoUtil.printLog(name+".use()");
		ProtoUtil.increaseIndent();
		ProtoUtil.printLog("Vége a játéknak!!!");
    	ProtoUtil.decreaseIndent();
    }
	
	/**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog(name+".onPickUp()");
		ProtoUtil.increaseIndent();
		use();
    	ProtoUtil.decreaseIndent();
	}
}