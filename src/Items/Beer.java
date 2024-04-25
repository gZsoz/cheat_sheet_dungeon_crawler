package Items;

import java.util.Random;

import Character.Student;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Osztály a Beer tárgy reprezentálására
 */
public class Beer extends DecayingItems {
	
	/**
     * Sör használatakor (felvételekor) végrehajtandó műveleteket végzi, 
     * immunitást ad a használónak, minden másodpercben csökkenti a hatás időtartamát.
     */
	@Override
    public void use() {
		ProtoUtil.printLog("use");
		if(owner instanceof Student) ((Student)owner).setInvincible(true);
		setIsActive(true);
    }
	
	/**
	 * A tárgy felvételekor elindítja a használatot.
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		use();
		if(!owner.getInventory().isEmpty()) {
			int rand=new Random().nextInt(owner.getInventory().size());
			owner.putdownItem(owner.getInventory().get(rand));
		}
	}
	
	@Override
	public void onDrop() {
		ProtoUtil.printLog("onDrop");
		isActive=false;
		boolean beer=false;
		for(Item i : owner.getInventory()) {
			if(i instanceof Beer) {
				beer=true;
				break;
			}
		}
		if((!beer) && owner instanceof Student) ((Student)owner).setInvincible(false);
		
		super.onDrop();
	}
	
}