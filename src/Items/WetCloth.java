package Items;

import SkeletonUtil.SkeletonUtil;
import Character.Teacher;

public class WetCloth extends DecayingItems{
	/**
     * Actions to perform when the WetCLoth is used by a character.
     * Stuns teachers in the room that the item is used
     * Lowers the duration of the effect each second
     */
	
	@Override
	public void use() {
        // Implementation
    	SkeletonUtil.printLog("use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    	SkeletonUtil.printLog("Room.getCharacters()");
    	SkeletonUtil.increaseIndent();
		Teacher teacher= new Teacher();
		teacher.setStunned(true);
		SkeletonUtil.decreaseIndent();
    }
	
	/**
	 * Keeps data up to date
	 */
	@Override
public void update() {
        
    	SkeletonUtil.printLog("update()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();// Implementation
    }

}
