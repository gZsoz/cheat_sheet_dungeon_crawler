package View.Controller;

import Map.Labyrinth;
import View.ViewItem.ViewItem;

import java.util.HashMap;

import Items.Item;
import View.ViewCharacter.*;
import Character.Character;

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

    public static HashMap<Item, ViewItem> items= new HashMap<Item, ViewItem>();
    public static HashMap<Character, ViewCharacter> characters = new HashMap<Character, ViewCharacter>();
    
    /**
     * Meghívja a vezérelt játékosok action függvényét és a labyrinth update() függvényét.
     */
    public void action() {}
}