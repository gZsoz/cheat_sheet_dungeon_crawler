package Items;

import Character.Teacher;
import Map.Room;
import SkeletonUtil.SkeletonUtil;

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
     * Actions to perform when the WetCLoth is used by a character.
     * Stuns teachers in the room that the item is used
     * Lowers the duration of the effect each second
     */
	
	@Override
	public void use() {
    	SkeletonUtil.printLog(name+".WetCloth.use()");
		SkeletonUtil.increaseIndent();
    	new Room().getCharacters();
    	if(SkeletonUtil.binaryQuestion("Van a szobában tanár?"))
    		new Teacher().setStunned(true);
    	reduceDuration();
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