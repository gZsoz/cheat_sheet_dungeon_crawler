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
    	ProtoUtil.printLog("use");
    	setIsActive(true);
		for(Character character: owner.getRoom().getCharacters() ) {
			if(character instanceof Teacher) {
				if(character.getStunned()==0) character.setStunned(4);
			}
		}
    }
	
	/**
	 * A tárgy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		use();
	}
	
	@Override
	public void onDrop() {
		ProtoUtil.printLog("onDrop");
		isActive=false;
		super.onDrop();
	}

	@Override
	public void update() {
		use();
		super.update();
	}	
}
