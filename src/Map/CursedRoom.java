package Map;

import Character.Character;
import EnvironmentalFactor.Gas;
import SkeletonUtil.SkeletonUtil;

/**
 * Class representing a cursed room in the game.
 */
public class CursedRoom extends Room{
	public void hideDoors() {
		SkeletonUtil.printLog("hideDoors()");
		SkeletonUtil.increaseIndent();
    	removeAllDoors();
    	SkeletonUtil.decreaseIndent();
	}
	public void removeAllDoors() {
		SkeletonUtil.printLog("removeAllDoors()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	@Override
	public void addCharacter(Character character) {
		SkeletonUtil.printLog("addCharacter()");
		SkeletonUtil.increaseIndent();
    	hideDoors();
		if(SkeletonUtil.binaryQuestion("Gázos-e a szoba?")) {
			new Gas().stun(character);
		}else {
		}
    	SkeletonUtil.decreaseIndent();
	}
	@Override
    public void update() {
        // Implementation
    }
}