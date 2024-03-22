package EnvironmentalFactor;

import Character.Character;
import SkeletonUtil.SkeletonUtil;
import Items.*;

/**
 * Class representing gas environmental factor in the game.
 */
public class Gas extends EnvironmentalFactors {
	public void stun(Character character) {
		SkeletonUtil.printLog("stun()");
		SkeletonUtil.increaseIndent();
		character.getInventory();
		if(SkeletonUtil.binaryQuestion("Van-e maszkja?")) {
			new Mask().use();
		}else {
			character.setStunned(true);
		}
		SkeletonUtil.decreaseIndent();
	}
    public void update() {
        // Implementation
    }
}