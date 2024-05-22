package Model.Items.NumberOfUsesItems;

import Main.Main;
import Model.Items.NumberOfUsesItems.NumberOfUsesItem;

/**
 * A logarléc osztályból az összes többi tárggyal ellentétben csupán egy lehet a pályán, 
 * ez felel a játék egyik lehetséges végkimeneteléért, a hallgatók győzelméért, 
 * amely akkor következik be, ha ezt a tárgyat valamelyik hallgató felveszi.
 */
public class SlideRule extends NumberOfUsesItem {
	
	/**
	 * Konstruktor egy logarléc létrehozásához.
	 */
	public SlideRule() {
		sticky=false;
		RemainingUses=1;
	}
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amiknek a logarléc felvevésénél kell megtörténnie.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp");
		use();
	}
	
	/**
	 * A logarléc használata. Ezzel a játékot megnyerik a hallgatók.
	 */
	@Override
	public void use() {
		Main.printLog("use");
		if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
		Main.printLog("Game over");
		owner.notifySubsribers("studentwon");
	}
}