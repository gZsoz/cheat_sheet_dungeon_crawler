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
		sticky=false;
		RemainingUses=1;
	}
		
	/**
	 * A logarléc használata. Ezzel a játékot megnyerik a hallgatók.
	 */
	@Override
    public void use() {
		ProtoUtil.printLog("use");
		if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
		ProtoUtil.printLog("Game over");
		owner.notifySubsribers("studentwon");
    }
	
	/**
     * Végrehajtja azokat a tevékenységeket, amiknek a tárgy felvevésénél kell megtörténnie.
     */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		use();
	}
}