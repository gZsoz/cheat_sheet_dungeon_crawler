package Items;

import Character.Character;
import Character.Student;
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
    	
		for(Character character: owner.getRoom().getCharacters() ) {
			if(character instanceof Teacher) {
				((Teacher) character).setStunned(true);
			}
		}
    	isActive=true;
		
    }
	/**
	 * A trágy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp)");
		
	}

	@Override
	public void update() {
		ProtoUtil.printLog("update");
		if(isActive) {
			reduceDuration();
		}
	}	

}