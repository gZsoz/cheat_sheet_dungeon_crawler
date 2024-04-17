package Items;

import Character.Teacher;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Class representing WetCLoth item.
 */
public class WetCloth extends DecayingItems{
	
	/**
     * Konstruktor egy tárgy létrehozásához. Wetcloth-ra állítja a nevet.
     */
	public WetCloth() {
		name="Wetcloth";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public WetCloth(String n) {
		name=n;
	}
	
	/**
     * A WetCLoth tárgy használatakor elvégezendő műveleteket végzi
     * Elkábítja abban a szobában tartozkodó tanárokat, ahol a tárgy használva volt
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	
	@Override
	public void use() {
    	ProtoUtil.printLog(name+".WetCloth.use()");
		ProtoUtil.increaseIndent();
    	new Room().getCharacters();
    	if(ProtoUtil.binaryQuestion("Van a szobában tanár?"))
    		new Teacher().setStunned(true);
    	reduceDuration();
		ProtoUtil.decreaseIndent();
    }
	/**
	 * A trágy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog(name+".onPickUp()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
	}

}