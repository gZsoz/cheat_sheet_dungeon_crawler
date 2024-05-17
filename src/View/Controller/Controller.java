package View.Controller;

import Map.Labyrinth;
import Map.Room;
import View.ViewItem.ViewItem;
import View.ViewMap.ViewRoom;

import java.util.HashMap;

import Items.Item;
import View.ViewCharacter.*;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import Character.Character;
import EnvironmentalFactor.EnvironmentalFactors;

/**
 * Felelősség: A felhasználók bemeneteinek értelmezése és a model ezek szerinti formázása.
 */
public class Controller {
	
    /**
     * Az első játékos vezérlője.
     */
    private PlayerController player1;
    
    /**
     * A második játékos vezérlője.
     */
    private PlayerController player2;
    
    /**
     * A modellben értelmezett labirintus.
     */
    private Labyrinth labyrinth;

    public static HashMap<Item, ViewItem> items= new HashMap<>();
    public static HashMap<Character, ViewCharacter> characters = new HashMap<>();
    public static HashMap<Room, ViewRoom> rooms= new HashMap<>();
    public static HashMap<EnvironmentalFactors, ViewEnvironmentalFactors> envs= new HashMap<>();
    
    /**
     * Meghívja a vezérelt játékosok action függvényét és a labyrinth update() függvényét.
     */
    public void action() {}
}