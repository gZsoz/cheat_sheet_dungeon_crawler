package Map;
import Items.Item;
import SkeletonUtil.SkeletonUtil;
import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;

import java.util.List;

import Character.*;
import Character.Character;
/**
 * Class representing a room in the game.
 */
public class Room {

    /**
     * Spawns an item in the room.
     * @param item The item to be spawned.
     */
    public void spawnItem(Item item) {
        // Method signature
    }

    /**
     * Adds a character to the room.
     * @param character The character to be added.
     * @throws IllegalArgumentException if adding the character exceeds the room's capacity.
     */
    public void addCharacter(Character character) {
    	SkeletonUtil.printLog("addCharacter()");
		SkeletonUtil.increaseIndent();
		if(SkeletonUtil.binaryQuestion("Gázos-e a szoba?")) {
			new Gas().stun(character);
		}else {
		}
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Adds an item to the room's inventory.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        // Method signature
    }

    /**
     * Removes a character from the room.
     * @param character The character to be removed.
     */
    public void removeCharacter(Character character) {
		SkeletonUtil.printLog("removeCharacter()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Removes an item from the room's inventory.
     * @param item The item to be removed.
     */
    public void removeItem(Item item) {
        // Method signature
    }

    /**
     * Adds a neighbouring room.
     * @param room The neighbouring room to be added.
     */
    public void addNeighbour(Room room) {
        // Method signature
    }

    /**
     * Removes a neighbouring room.
     * @param room The neighbouring room to be removed.
     */
    public void removeNeighbour(Room room) {
        // Method signature
    }

    /**
     * Adds an environmental factor to the room.
     * @param environmentalFactor The environmental factor to be added.
     */
    public void addEnvironmentalFactor(EnvironmentalFactors environmentalFactor) {
        // Method signature
    }

    /**
     * Retrieves the current number of players in the room.
     * @return The number of characters currently in the room.
     */
    public int currentNumOfPlayers() {
		return 0;
        // Method signature
    }

    /**
     * Merges this room with another room.
     * @param room The room to be merged with.
     */
    public void merge(Room room) {
        // Method signature
    }

    /**
     * Updates the room, triggering actions for characters and environmental factors.
     */
    public void update() {
        // Method signature
    }

	public int getCapacity() {
		SkeletonUtil.printLog("getCapacity()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return 0;
	}

	public List<Character> getCharacters() {
		SkeletonUtil.printLog("getCarachters()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return null;
	}
}