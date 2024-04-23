package Items;

import Character.Teacher;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Class representing WetCLoth item.
 */
public class WetCloth extends DecayingItems{
	
	
	
	
	/**
     * A WetCLoth tárgy használatakor elvégezendő műveleteket végzi
     * Elkábítja abban a szobában tartozkodó tanárokat, ahol a tárgy használva volt
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	
	@Override
	public void use() {
    	ProtoUtil.printLog("use()");
		for(Character character: location.getCharacters) {
			if(character instanceof Teacher) {
				((Teacher) character).setStunned(true);
			}
		}
    	setisactive(true);
		
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

	@Override
	public void update() {
		
		
	}

}