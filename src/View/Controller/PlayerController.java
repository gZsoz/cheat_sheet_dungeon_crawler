package View.Controller;

import javax.swing.*;

import Character.Student;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.ViewCharacter.ViewStudent;
import View.ViewMap.ViewLabyrinth;
import View.Utils.ActionState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Felelősség: A felhasználók bemeneteinek értelmezése és a model ezek szerinti formázása.
 */
public class PlayerController extends JComponent implements KeyListener {
	
    /**
     * Az egyik játékos által irányított hallgató.
     */
    private Student player;
    
    /**
     * Az egyik játékos által irányított hallgató megjelenítéséért felelő része.
     */
    private ViewStudent playerView;

    public void setLabyrinthView(ViewLabyrinth labyrinthView) {
        this.labyrinthView = labyrinthView;
    }

    /**
     * A megjelenített labirintus osztály csak a kiválasztott szobák körvonalának beállítására kell.
     */
    private ViewLabyrinth labyrinthView;
    
    /**
     * Az adott karakter arcképe.
     */
    private Image characterImage;

    /**
     * Az adott karkter inventory-jának háttere
     */
    private Image inventoryBackgroundImage;

    private SelectionColor color;

    private Coordinates[] leftInventoryPositions = {new Coordinates(50,575),new Coordinates(150,575),
                                                    new Coordinates(50,670),new Coordinates(150,670),
                                                    new Coordinates(50,765),new Coordinates(150,765)
    };

    private Coordinates[] rightInventoryPositions = {new Coordinates(1578,575),new Coordinates(1678,575),
                                                     new Coordinates(1578,670),new Coordinates(1678,670),
                                                     new Coordinates(1578,765),new Coordinates(1678,765)
    };

    /**
     * Az irányító játékos éppen melyik akció melyik részén áll.
     */
    private ActionState state;
    
    /**
     * Az éppen kiválasztott elem amivel valamilyen műveletet szeretne végezni a játékos.
     */
    private int selectedSlot;

    public PlayerController(SelectionColor color, Student stud){
        inventoryBackgroundImage = ImageReader.loadImage("res/images/test/testroom.png");
        this.color = color;
        player=stud;
        selectedSlot = 0;
        playerView=new ViewStudent(player, new Coordinates(0,0));
        playerView.setImage(color);
    }

    public void setPlayerView(ViewStudent pv){
        playerView = pv;
        playerView.setImage(color);
    }

    /**
     * A konstruktorban megadott gomboknak megfelelően értelmezi a játékos által végzett műveleteket,
     * és ennek megfelelően módosítja állapotát, vagy hív függvényeket a player attribútumán.
     */
    public void action() {}

    /**
     * Kirajzolja a játékos által irányított karakter információs ablakát.
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        // RED Inventory
        if(color == SelectionColor.Red){
            // Box
            g2D.setColor(Color.RED.brighter());
            g2D.fillRect(0,540,280,400);
            g2D.drawImage(inventoryBackgroundImage,20,560,238,360,null);

            // Slots
            g2D.setColor(Color.DARK_GRAY.darker());
            g2D.fillRect(leftInventoryPositions[0].getX(),leftInventoryPositions[0].getY(),78,78);
            g2D.setColor(Color.GRAY.darker());
            for(int i = 1; i<leftInventoryPositions.length; i++){
                g2D.fillRect(leftInventoryPositions[i].getX(),leftInventoryPositions[i].getY(),78,78);
            }
            // Mode textbox
            g2D.setColor(Color.white.darker());
            g2D.fillRect(leftInventoryPositions[leftInventoryPositions.length-2].getX(),
                    leftInventoryPositions[leftInventoryPositions.length-2].getY() + 94,
                    78+100,50);

        }
        // BLUE Inventory
        else if(color == SelectionColor.Blue){
            // Box
            g2D.setColor(Color.BLUE.brighter());
            g2D.fillRect(1526,540,280,400);
            g2D.drawImage(inventoryBackgroundImage,1548,560,238,360,null);

            // Slots
            g2D.setColor(Color.GRAY.darker());
            for(int i = 0; i<rightInventoryPositions.length; i++){
                if(i!=1){
                    g2D.fillRect(rightInventoryPositions[i].getX(),rightInventoryPositions[i].getY(),78,78);
                }

            }
            g2D.setColor(Color.DARK_GRAY.darker());
            g2D.fillRect(rightInventoryPositions[1].getX(),rightInventoryPositions[1].getY(),78,78);

            // Mode textbox
            g2D.setColor(Color.white.darker());
            g2D.fillRect(rightInventoryPositions[rightInventoryPositions.length-2].getX(),
                    rightInventoryPositions[rightInventoryPositions.length-2].getY() + 94,
                    78+100,50);
        }

    }

	public ViewStudent getViewStudent() {
		// TODO Auto-generated method stub
		return playerView;
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(color == SelectionColor.Red){
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
                    System.out.println("A - Balra vált kijelölést");
                    decreaseSelectedSlot();
                    break;
                case KeyEvent.VK_D:
                    System.out.println("D - Jobbra vált kijelölést");
                    increaseSelectedSlot();
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("Space - Kiválaszt");
                    useSelected();
                    selectedSlot = 0;
                    break;
                case KeyEvent.VK_S:
                    System.out.println("S - Inventory kijelölő mód");
                    state = ActionState.InInventory;
                    selectedSlot = 0;
                case KeyEvent.VK_W:
                    System.out.println("W - Szoba kijelölő mód");
                    state = ActionState.RoomPicker;
                    selectedSlot = 0;
                    break;
                case KeyEvent.VK_E:
                    System.out.println("E - Szobatárgy kijelölő mód");
                    state = ActionState.ItemPicker;
                    selectedSlot = 0;
                    break;
            }
        }
        if(color == SelectionColor.Blue){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    System.out.println("⬅ - Balra vált kijelölést");
                    decreaseSelectedSlot();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("➡ - Jobbra vált kijelölést");
                    increaseSelectedSlot();
                    break;
                case KeyEvent.VK_ENTER:
                    System.out.println("Enter - Kiválaszt");
                    useSelected();
                    selectedSlot = 0;
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("⬆ - Szoba kijelölő mód");
                    state = ActionState.RoomPicker;
                    selectedSlot = 0;
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("⬇ - Inventory kijelölő mód");
                    state = ActionState.InInventory;
                    selectedSlot = 0;
                    break;
                case KeyEvent.VK_CONTROL:
                    System.out.println("Ctrl - Szobatárgy kijelölő mód");
                    state = ActionState.InInventory;
                    selectedSlot = 0;
                    break;
            }
        }
    }

    private void useSelected() {
        System.out.println("\tuseSelected()");
        if(state == ActionState.RoomPicker && !labyrinthView.getRoomsInLabyrinth().isEmpty()){
            player.enterRoom(labyrinthView.getRoomsInLabyrinth().get(selectedSlot).getRoom());
        }
        else if(state == ActionState.ItemPicker && !player.getRoom().getItems().isEmpty()){
            player.pickupItem(player.getRoom().getItems().get(selectedSlot));
        }
        else if(state == ActionState.InInventory && !player.getInventory().isEmpty()){
            player.activate(player.getInventory().get(selectedSlot));
        }
    }

    private void increaseSelectedSlot() {
        System.out.println("\tincreaseSelectedSlot()");
        if(state == ActionState.RoomPicker){
            if(selectedSlot<labyrinthView.getRoomsInLabyrinth().size()){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
        else if(state == ActionState.ItemPicker){
            if(selectedSlot<player.getRoom().getItems().size()){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
        else if(state == ActionState.InInventory){
            if(selectedSlot<player.getInventory().size()){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
    }

    private void decreaseSelectedSlot() {
        System.out.println("\tdecreaseSelectedSlot()");
        if(selectedSlot>0){
            selectedSlot--;
        }
        else{
            if(state == ActionState.RoomPicker){
                selectedSlot=labyrinthView.getRoomsInLabyrinth().size();
            }
            else if(state == ActionState.ItemPicker){
                selectedSlot=player.getRoom().getItems().size();
            }
            else if(state == ActionState.InInventory){
                selectedSlot=player.getInventory().size();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}