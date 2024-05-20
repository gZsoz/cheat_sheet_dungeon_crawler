package Items;

import Character.Character;
import Character.Teacher;
import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy nedves törlőrongyot reprezentál a játékban.
 */
public class WetCloth extends DecayingItems {
	
	/**
	 * A tárgy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		use();
	}
	/**
	 * A tárgy eldobásakor végrehajtott műveletek.
	 */
	@Override
	public void onDrop() {
		ProtoUtil.printLog("onDrop");
		setIsActive(false);
		super.onDrop();
	}
	
	/**
	 * A WetCLoth tárgy használatakor elvégezendő műveleteket végzi.
	 * Elkábítja abban a szobában tartozkodó tanárokat, ahol a tárgy használva volt.
	 */
	@Override
	public void use() {
		ProtoUtil.printLog("use");
		setIsActive(true);
		for(Character character: owner.getRoom().getCharacters() ) {
			if(character instanceof Teacher) {
				character.setStunned(Character.stunTime);
			}
		}
	}
	
	/**
	 * A nedves törlőrongy frissítése.
	 */
	@Override
	public void update() {
		use();
		super.update();
	}	
}
