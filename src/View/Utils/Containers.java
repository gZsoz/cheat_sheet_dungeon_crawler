package View.Utils;

import Model.Map.Room;
import View.ViewItem.ViewItem;
import View.ViewMap.ViewRoom;

import java.util.HashMap;

import Model.Items.Item;
import View.ViewCharacter.ViewCharacter;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import Model.Characters.Character;
import Model.EnvironmentalFactors.EnvironmentalFactors;

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
	public static HashMap<EnvironmentalFactors, ViewEnvironmentalFactors> envs= new HashMap<>();

}