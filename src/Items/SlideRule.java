package Items;

import SkeletonUtil.SkeletonUtil;

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
		SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
		SkeletonUtil.printLog("Vége a játéknak!!!");
    	SkeletonUtil.decreaseIndent();
    }
	
	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
		use();
    	SkeletonUtil.decreaseIndent();
	}
}