package View.Controller;

import Map.Labyrinth;

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

    /**
     * Meghívja a vezérelt játékosok action függvényét és a labyrinth update() függvényét.
     */
    public void action() {}
}