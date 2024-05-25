package view.utils;

import model.characters.Character;
import model.environmentalfactors.EnvironmentalFactor;
import model.items.Item;
import model.map.Room;
import view.viewcharacters.ViewCharacter;
import view.viewenvironmentalfactors.ViewEnvironmentalFactor;
import view.viewitems.ViewItem;
import view.viewmap.ViewRoom;

import java.util.HashMap;

/**
 * A modellbeli objektumokat a View-beli objektumokkal összekötő osztály.
 */
public class Containers {
	
	/**
	 * A modellbeli tárgyakhoz a View-beli tárgyakat tárolja el.
	 */
	public static HashMap<Item, ViewItem> items= new HashMap<>();
	
	/**
	 * A modellbeli karakterekhez a View-beli karaktereket tárolja el.
	 */
	public static HashMap<Character, ViewCharacter> characters = new HashMap<>();
	
	/**
	 * A modellbeli szobákhoz a View-beli szobákat tárolja el.
	 */
	public static HashMap<Room, ViewRoom> rooms= new HashMap<>();
	
	/**
	 * A modellbeli környezeti változókhoz a View-beli környezeti változókat tárolja el.
	 */
	public static HashMap<EnvironmentalFactor, ViewEnvironmentalFactor> envs= new HashMap<>();

}