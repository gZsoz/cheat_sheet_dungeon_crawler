package Character;

import EnvironmentalFactor.Gas;
import Items.Item;
import Map.*;
import SkeletonUtil.*;

/**
 * Abstract class representing a character in the game.
 */
public abstract class Character {

    /**
     * Enters the specified room.
     * @param r The room to enter.
     * @return True if successful, false otherwise.
     */
    public boolean enterRoom(Room r) {
    	SkeletonUtil.printLog("enterRoom(Room)");
    	SkeletonUtil.increaseIndent();
    	if(SkeletonUtil.binaryQuestion("Befér-e a szobába?")) {
    		new Room().getCapacity();
    		new Room().getCharacters();
    		if(SkeletonUtil.binaryQuestion("Elátkozott-e a szoba?")) {
    			new CursedRoom().addCharacter(this);
				new Room().removeCharacter(this);
    		}else {
    			new Room().addCharacter(this);
				new Room().removeCharacter(this);
    		}
    	}
    	SkeletonUtil.decreaseIndent();
		return false;
    }

    /**
     * Picks up the specified item.
     * @param i The item to pick up.
     * @return True if successful, false otherwise.
     */
    public boolean pickupItem(Item i) {
		return false;
        // Implementation
    }

    /**
     * Puts down the specified item.
     * @param i The item to put down.
     */
    public void putdownItem(Item i) {
        // Implementation
    }

    public void update() {
        // Implementation
    }

	public void getInventory() {
		SkeletonUtil.printLog("getInventory()");
		SkeletonUtil.increaseIndent();
		SkeletonUtil.decreaseIndent();
	}

	public void setStunned(boolean b) {
		SkeletonUtil.printLog("setStunned()");
		SkeletonUtil.increaseIndent();
		SkeletonUtil.decreaseIndent();
		
	}
}