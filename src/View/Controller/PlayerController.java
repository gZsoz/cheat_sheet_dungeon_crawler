package View.Controller;

import javax.swing.*;

import Character.Student;
import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import ProtoUtil.ProtoUtil;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Subscriber;
import View.ViewCharacter.ViewStudent;
import View.ViewMap.ViewCursedRoom;
import View.ViewMap.ViewLabyrinth;
import View.ViewMap.ViewRoom;
import View.Utils.ActionState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Felelősség: A felhasználók bemeneteinek értelmezése és a model ezek szerinti formázása.
 */
public class PlayerController extends JComponent implements KeyListener, Subscriber {
	
    /**
     * Az egyik játékos által irányított hallgató.
     */
    private Student player;
    
    /**
     * A szoba melyben a hallgató van.
     */
    private Room room;
    
    /**
     * A labirintus melyben a hallgató van.
     */
    private Labyrinth labyrinth;
    
    /**
     * Az egyik játékos által irányított hallgató megjelenítéséért felelő része.
     */
    private ViewStudent playerView;

    /**
     * Az adott karakter arcképe.
     */
    private Image characterImage;

    /**
     * Az adott karkter inventory-jának háttere
     */
    private Image inventoryBackgroundImage;

    private SelectionColor color;

    public final static Coordinates RED_FACE_POSITION = new Coordinates(50,575);
    public static Coordinates[] leftInventoryPositions = {new Coordinates(150,575),
                                                    new Coordinates(50,670),new Coordinates(150,670),
                                                    new Coordinates(50,765),new Coordinates(150,765)
    };
    public final static Coordinates BLUE_FACE_POSITION = new Coordinates(1678,575);
    public static Coordinates[] rightInventoryPositions = {new Coordinates(1578,575),
                                                     new Coordinates(1578,670),new Coordinates(1678,670),
                                                     new Coordinates(1578,765),new Coordinates(1678,765)
    };

    /**
     * Az irányító játékos éppen melyik akció melyik részén áll.
     */
    private ActionState state=ActionState.RoomPicker;
    
    private boolean isStudentAlive=true;
    
    /**
     * Az éppen kiválasztott elem amivel valamilyen műveletet szeretne végezni a játékos.
     */
    private int selectedSlot;

    public PlayerController(SelectionColor color, Student stud){
        inventoryBackgroundImage = ImageReader.loadImage(ImageReader.path+"test/testroom.png");
        this.color = color;
        player=stud;
        player.subscribe(this);
        selectedSlot = 0;
        playerView=new ViewStudent(player, new Coordinates(0,0));
        playerView.setImage(color);
        setCharacterImage();
    }
    
    public void setLabyrinth(Labyrinth l) {
    	labyrinth=l;
    	labyrinth.subscribe(this);
    }

    private void setCharacterImage() {
    	if(isStudentAlive) {
	    	if(color==SelectionColor.Red)
	    		characterImage = ImageReader.loadImage(ImageReader.path+"characters/student_red_face.png");
	    	else
	    		characterImage = ImageReader.loadImage(ImageReader.path+"characters/student_blue_face.png");
    	}else {
    		if(color==SelectionColor.Red)
	    		characterImage = ImageReader.loadImage(ImageReader.path+"characters/student_red_face_kicked.png");
	    	else
	    		characterImage = ImageReader.loadImage(ImageReader.path+"characters/student_blue_face_kicked.png");
    	}
    } 
    
    public void setPlayerView(ViewStudent pv){
        playerView = pv;
        playerView.setImage(color);
    }
    
    public void setRoomSubscribed() {
    	room=player.getRoom();
    	room.subscribe(this);
    }

    /**
     * A konstruktorban megadott gomboknak megfelelően értelmezi a játékos által végzett műveleteket,
     * és ennek megfelelően módosítja állapotát, vagy hív függvényeket a player attribútumán.
     */
    public void action() {}



	public ViewStudent getViewStudent() {
		// TODO Auto-generated method stub
		return playerView;
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!isStudentAlive)
            return;
        if(color == SelectionColor.Red){
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
                    System.out.println("A - Balra vált kijelölést");
                    clearColor();
                    decreaseSelectedSlot();
                    setNewColor();
                    break;
                case KeyEvent.VK_D:
                    System.out.println("D - Jobbra vált kijelölést");
                    clearColor();
                    increaseSelectedSlot();
                    setNewColor();
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("Space - Kiválaszt");
                    clearColor();
                    useSelected();
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_S:
                    System.out.println("S - Inventory kijelölő mód");
                    clearColor();
                    state = ActionState.InInventory;
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_W:
                    System.out.println("W - Szoba kijelölő mód");
                    clearColor();
                    state = ActionState.RoomPicker;
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_E:
                    System.out.println("E - Szobatárgy kijelölő mód");
                    clearColor();
                    state = ActionState.ItemPicker;
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_Q:
                    System.out.println("Q - Tárgy eldobása");
                    if (state == ActionState.InInventory && !player.getInventory().isEmpty()) {
                        clearColor();
                        player.putdownItem(player.getInventory().get(selectedSlot));
                        setNewColor();
                    }
                    break;
            }
        }
        if(color == SelectionColor.Blue) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    System.out.println("⬅ - Balra vált kijelölést");
                    clearColor();
                    decreaseSelectedSlot();
                    setNewColor();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("➡ - Jobbra vált kijelölést");
                    clearColor();
                    increaseSelectedSlot();
                    setNewColor();
                    break;
                case KeyEvent.VK_ENTER:
                    System.out.println("Enter - Kiválaszt");
                    clearColor();
                    useSelected();
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("⬆ - Szoba kijelölő mód");
                    clearColor();
                    state = ActionState.RoomPicker;
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("⬇ - Inventory kijelölő mód");
                    clearColor();
                    state = ActionState.InInventory;
                    selectedSlot = 0;
                    setNewColor();
                    break;
                case KeyEvent.VK_CONTROL:
                    System.out.println("Ctrl - Szobatárgy kijelölő mód");
                    clearColor();
                    state = ActionState.ItemPicker;
                    selectedSlot = 0;
                    setNewColor();
                    break;

                case KeyEvent.VK_SHIFT:
                    System.out.println("Shift - Tárgy eldobása");
                    if (state == ActionState.InInventory && !player.getInventory().isEmpty()) {
                        clearColor();
                        player.putdownItem(player.getInventory().get(selectedSlot));
                        setNewColor();
                    }
                    break;
            }
        }
    }

    private void setNewColor() {
        if(state==ActionState.RoomPicker && !player.getRoom().getNeighbours().isEmpty()){
            // set color
            Controller.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).setColor(color);
        }
        else if(state==ActionState.ItemPicker && !player.getRoom().getItems().isEmpty()){
            //set color
            Controller.items.get(player.getRoom().getItems().get(selectedSlot)).setColor(color);
        }
        else if(state == ActionState.InInventory && !player.getInventory().isEmpty()){
            //set color
            Controller.items.get(player.getInventory().get(selectedSlot)).setColor(color);
        }
    }

    private void clearColor() {
        if(state == ActionState.RoomPicker){
            //set prev empty color
            if(player.getRoom().getNeighbours().isEmpty()){
                return;
            }
            Controller.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).removeColor(color);
        }
        else if(state == ActionState.ItemPicker){
            //set prev empty color
            if(player.getRoom().getItems().isEmpty()){
                return;
            }
            Controller.items.get(player.getRoom().getItems().get(selectedSlot)).removeColor(color);
        }
        else if(state == ActionState.InInventory){
            //set prev empty color
            if(player.getInventory().isEmpty()){
                return;
            }
            Controller.items.get(player.getInventory().get(selectedSlot)).removeColor(color);
        }
    }

    private void useSelected() {
        if(state == ActionState.RoomPicker && !player.getRoom().getNeighbours().isEmpty()){
            Controller.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).removeColor(color);
            player.enterRoom(player.getRoom().getNeighbours().get(selectedSlot));
        }
        else if(state == ActionState.ItemPicker && !player.getRoom().getItems().isEmpty()){
            Controller.items.get(player.getRoom().getItems().get(selectedSlot)).removeColor(color);
            player.pickupItem(player.getRoom().getItems().get(selectedSlot));
        }
        else if(state == ActionState.InInventory && !player.getInventory().isEmpty()){
            Controller.items.get(player.getInventory().get(selectedSlot)).removeColor(color);
            player.activate(player.getInventory().get(selectedSlot));
        }
    }

    private void increaseSelectedSlot() {
        if(state == ActionState.RoomPicker){
            if(selectedSlot<player.getRoom().getNeighbours().size()-1){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
        else if(state == ActionState.ItemPicker){
            if(selectedSlot<player.getRoom().getItems().size()-1){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
        else if(state == ActionState.InInventory){
            if(selectedSlot<player.getInventory().size()-1){
                selectedSlot++;
            }
            else{
                selectedSlot=0;
            }
        }
    }

    private void decreaseSelectedSlot() {
        if(selectedSlot>0){
            selectedSlot--;
        }
        else if(state == ActionState.RoomPicker){
            selectedSlot=player.getRoom().getNeighbours().size()-1;
        }
        else if(state == ActionState.ItemPicker){
            selectedSlot=player.getRoom().getItems().size()-1;
        }
        else if(state == ActionState.InInventory){
            selectedSlot=player.getInventory().size()-1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

	@Override
	public void propertyChanged(String property) {
		if(property.equals("kicked")) {
			isStudentAlive=false;
			clearColor();
			GameFrame.viewCharacters.remove(Controller.characters.get(player));
			GameFrame.container.remove(Controller.characters.get(player));
			Controller.characters.remove(player);
			room.unsubscribe(this);
			player.unsubscribe(this);
			setCharacterImage();
		}else if(property.equals("characters")) {
			if(!room.getCharacters().contains(player)) {
				if(state==ActionState.ItemPicker) {
					Controller.items.get(room.getItems().get(selectedSlot)).removeColor(color);
					selectedSlot=0;
				}else if(state==ActionState.RoomPicker) {
					Controller.rooms.get(room.getNeighbours().get(selectedSlot)).removeColor(color);
					selectedSlot=0;
				}
				room.unsubscribe(this);
				setRoomSubscribed();
				setNewColor();
			}
		}else if(property.equals("inventory")) { // ha elhasználódik egy decaying akkor a selectedSlot reagáljon rá
			if(state==ActionState.InInventory) {
				selectedSlot=0;
				setNewColor();
			}	
		}else if(property.contains("items removed")) {
			if(state==ActionState.ItemPicker) {
				int idx=Integer.parseInt(property.split(" ")[2]);
				if(idx>selectedSlot)
					return;
				else if(idx==selectedSlot){
					selectedSlot=0;
					setNewColor();
				}else {
					selectedSlot--;
					setNewColor();
				}
			}
		}else if(property.equals("modifyneighbours")) {
			if(state==ActionState.RoomPicker) {
				clearColor();
			}
		}else if(property.equals("neighboursmodified")) {
			if(state==ActionState.RoomPicker) {
					selectedSlot=0;
					setNewColor();
			}
		} else if(property.equals("enteredcursedroom")) {
			for(ViewRoom vr : GameFrame.viewRooms) {
				if(vr.getSelected() == color || vr.getSelected() == SelectionColor.Both)
					vr.removeColor(color);
			}
		}
	}
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
            g2D.fillRect(RED_FACE_POSITION.getX(),RED_FACE_POSITION.getY(),78,78);
            g2D.drawImage(characterImage,RED_FACE_POSITION.getX(),RED_FACE_POSITION.getY(),78,78,null);
            g2D.setColor(Color.GRAY.darker());
            for(int i = 0; i<leftInventoryPositions.length; i++){
                g2D.fillRect(leftInventoryPositions[i].getX(),leftInventoryPositions[i].getY(),78,78);
            }
            // Mode textbox
            g2D.setColor(new Color(221,221,221));
            g2D.fillRect(leftInventoryPositions[leftInventoryPositions.length-2].getX(),
                    leftInventoryPositions[leftInventoryPositions.length-2].getY() + 94,
                    78+100,50);

            g2D.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2D.setColor(Color.RED);
            switch (state){
                case InInventory:
                    if(color == SelectionColor.Red){
                        g2D.drawString("Inventory mód", leftInventoryPositions[3].getX() + 16, leftInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
                case RoomPicker:
                    if(color == SelectionColor.Red){
                        g2D.drawString("Szobaváltó mód", leftInventoryPositions[3].getX() + 12, leftInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
                case ItemPicker:
                    if(color == SelectionColor.Red){
                        g2D.drawString("Szobatárgy mód", leftInventoryPositions[3].getX() + 12, leftInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
            }

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
                    g2D.fillRect(rightInventoryPositions[i].getX(),rightInventoryPositions[i].getY(),78,78);

            }
            g2D.setColor(Color.DARK_GRAY.darker());
            g2D.fillRect(BLUE_FACE_POSITION.getX(),BLUE_FACE_POSITION.getY(),78,78);
            g2D.drawImage(characterImage,BLUE_FACE_POSITION.getX(),BLUE_FACE_POSITION.getY(),78,78,null);

            // Mode textbox
            g2D.setColor(new Color(221,221,221));
            g2D.fillRect(rightInventoryPositions[rightInventoryPositions.length-2].getX(),
                    rightInventoryPositions[rightInventoryPositions.length-2].getY() + 94,
                    78+100,50);
            g2D.setFont(new Font("Monospaced", Font.BOLD, 18));
            g2D.setColor(Color.BLUE);
            switch (state){
                case InInventory:
                    if(color == SelectionColor.Blue){
                        g2D.drawString("Inventory mód",rightInventoryPositions[3].getX() + 16, rightInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
                case RoomPicker:
                    if(color == SelectionColor.Blue){
                        g2D.drawString("Szobaváltó mód",rightInventoryPositions[3].getX() + 12, rightInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
                case ItemPicker:
                    if(color == SelectionColor.Blue){
                        g2D.drawString("Szobatárgy mód",rightInventoryPositions[3].getX() + 12, rightInventoryPositions[4].getY() + 78 + 44);
                    }
                    break;
            }

        }

    }
}