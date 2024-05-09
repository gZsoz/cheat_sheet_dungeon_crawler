package View.Controller;

import javax.imageio.ImageIO;

import Character.Student;
import View.ViewStudent;
import View.Utils.ActionState;
import View.Utils.View;

/**
 * Felelősség: A felhasználók bemeneteinek értelmezése és a model ezek szerinti formázása.
 */
public class PlayerController implements View {
    /**
     * Az egyik játékos által irányított hallgató.
     */
    private Student player;
    /**
     * Az egyik játékos által irányított hallgató megjelenítéséért felelő része.
     */
    private ViewStudent playerView;
    /**
     * A megjelenített labirintus osztály csak a kiválasztott szobák körvonalának beállítására kell.
     */
    private ViewLabyrinth labyrinthView;
    /**
     * Az adott karakter arcképe.
     */
    private ImageIO characterImage;
    /**
     * Az irányító játékos éppen melyik akció melyik részén áll.
     */
    private ActionState state;
    /**
     * Az éppen kiválasztott elem amivel valamilyen műveletet szeretne végezni a játékos.
     */
    private int selectedSlot;

    /**
     * A konstruktorban megadott gomboknak megfelelően értelmezi a játékos által végzett műveleteket,
     * és ennek megfelelően módosítja állapotát, vagy hív függvényeket a player attribútumán.
     */
    public void action() {}

    /**
     * Kirajzolja a játékos által irányított karakter információs ablakát.
     */
    public void paint() {}
}