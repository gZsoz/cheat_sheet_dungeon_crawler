package model.items.numberofusesitems;

import main.Main;

/**
 * Az osztály egy hamis csúszkálószabályzót reprezentál, amely a SlideRuleból származik.
 */
public class FakeSlideRule extends SlideRule {
	
	/**
	 * Konstruktor egy hamis logarléc létrehozásához.
	 */
	public FakeSlideRule() {
	    sticky=false;
	    RemainingUses=0; // Nincs maradék használat
	}
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amelyeknek a tárgy felvételénél kell megtörténniük.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp");
	}
	
	/**
	 * A hamis logarléc használatának metódusa. Üres, mivel a hamis tárgyak nem csinálnak semmit.
	 */
	@Override
	public void use() {
	    Main.printLog("use"); // Logolás
	}

}