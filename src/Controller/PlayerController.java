package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import Model.Characters.Student;
import Model.Map.Labyrinth;
import Model.Map.Room;
import View.Utils.ActionState;
import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Subscriber;
import View.ViewCharacter.ViewStudent;
import View.ViewMap.ViewRoom;

/**
 * A felhasználók bemeneteinek értelmezésére és a model ezek szerinti formázására használt osztály.
 */
@SuppressWarnings("serial")
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
	 * Az adott karakter normál arcképe.
	 */
	private Image normalCharacterImage;
	
	/**
	 * Az adott stunnolt karakter arcképe.
	 */
	private Image stunnedCharacterImage;
	
	/**
	 * Az adott kirúgott karakter arcképe.
	 */
	private Image kickedCharacterImage;
	
	/**
	 * Az adott karkter inventory-jának háttere
	 */
	private Image inventoryBackgroundImage;
	
	/**
	 * A játékoshoz tartozó kijelölőszín.
	 */
	private SelectionColor color;
	
	/**
	 * A piros diák arcképének pozíciója.
	 */
	public final static Coordinates RED_FACE_POSITION = new Coordinates(50,575);
	
	/**
	 * A piros diák inventory-jában a tárgyak pozíciói.
	 */
	public static Coordinates[] leftInventoryPositions = { new Coordinates(150,575),
	                                                new Coordinates(50,670),new Coordinates(150,670),
	                                                new Coordinates(50,765),new Coordinates(150,765)
	};
	/**
	 * A piros diák arcképének pozíciója.
	 */
	public final static Coordinates BLUE_FACE_POSITION = new Coordinates(1678,575);
	
	/**
	 * A kék diák inventory-jában a tárgyak pozíciói.
	 */
	public static Coordinates[] rightInventoryPositions = {new Coordinates(1578,575),
	                                                 new Coordinates(1578,670),new Coordinates(1678,670),
	                                                 new Coordinates(1578,765),new Coordinates(1678,765)
	};
	
	/**
	 * Az irányító játékos éppen melyik akció melyik részén áll.
	 */
	private ActionState state = ActionState.RoomPicker;
	
	/**
	 * Játékban van-e még a diák, azaz nem rúgták ki.
	 */
	private boolean isStudentAlive = true;
	
	/**
	 * Az éppen kiválasztott elem amivel valamilyen műveletet szeretne végezni a játékos.
	 */
	private int selectedSlot;
	
	/**
	 * Konstruktor egy játékosvezérlő létrehozásához.
	 * @param color a játékoshoz tartozó kijelölőszín
	 * @param stud a játékoshoz tartozó modellbeli diák
	 */
	public PlayerController(SelectionColor color, Student stud){
	    inventoryBackgroundImage = ImageReader.loadImage(ImageReader.path+"Backgrounds/inventory_background.png");
	    this.color = color;
	    player=stud;
	    player.subscribe(this);
	    selectedSlot = 0;
	    playerView=new ViewStudent(player, new Coordinates(0,0));
	    playerView.setImage(color);
	    initCharacterImage();
	    setCharacterImage();
	}
	
	/**
	 * A játékosok karakterei képének beolvasása.
	 */
	private void initCharacterImage() {
		if(color==SelectionColor.Red) {
			normalCharacterImage = ImageReader.loadImage(ImageReader.path+"Characters/student_red_face.png");
			stunnedCharacterImage = ImageReader.loadImage(ImageReader.path+"Characters/student_red_face_stunned.png");
		}else {
			normalCharacterImage = ImageReader.loadImage(ImageReader.path+"Characters/student_blue_face.png");
			stunnedCharacterImage = ImageReader.loadImage(ImageReader.path+"Characters/student_blue_face_stunned.png");	
		}
		kickedCharacterImage = ImageReader.loadImage(ImageReader.path+"Characters/student_face_kicked.png");
	}
	
	/**
	 * A játékosok karakterei képének beállítása.
	 */
	private void setCharacterImage() {
		if(!isStudentAlive)
			characterImage=kickedCharacterImage;
		else if(player.isStunned())
			characterImage=stunnedCharacterImage;
		else
			characterImage=normalCharacterImage;
	} 
	
	/**
	 * A modellbeli labirintus beállítása és az arra történő feliratkozás.
	 * @param l a labirintus
	 */
	public void setLabyrinth(Labyrinth l) {
		labyrinth=l;
		labyrinth.subscribe(this);
	}
	
	/**
	 * Beállítja a játékoshoz tartozó ViewStudent objektumot.
	 * @param pv a ViewStudent objektum
	 */
	public void setPlayerView(ViewStudent pv){
	    playerView = pv;
	    playerView.setImage(color);
	}
	
	/**
	 * Visszaadja a játékoshoz tartozó ViewStudent objektumot.
	 * @return a ViewStudent objektum
	 */
	public ViewStudent getViewStudent() {
		return playerView;
	}
	
	/**
	 * Feliratkozás a játékos karakterének szobájára.
	 */
	public void setRoomSubscribed() {
		room=player.getRoom();
		room.subscribe(this);
	}
	
	/**
	 * A játékos színének megfelelő kijelölést rak a játékos által kijelölt szobára, szobabeli tárgyra vagy inventory-beli tárgyra.
	 */
	private void setNewColor() {
	    if(state==ActionState.RoomPicker && !player.getRoom().getNeighbours().isEmpty()){
	        // set color
	        Containers.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).setColor(color);
	    }
	    else if(state==ActionState.ItemPicker && !player.getRoom().getItems().isEmpty()){
	        //set color
	        Containers.items.get(player.getRoom().getItems().get(selectedSlot)).setColor(color);
	    }
	    else if(state == ActionState.InInventory && !player.getInventory().isEmpty()){
	        //set color
	        Containers.items.get(player.getInventory().get(selectedSlot)).setColor(color);
	    }
	}
	
	/**
	 * Eltávolítja a kijelölést a játékos által kijelölt szobáról, szobabeli tárgyról vagy inventory-beli tárgyról.
	 */
	private void clearColor() {
	    if(state == ActionState.RoomPicker){
	        //set prev empty color
	        if(player.getRoom().getNeighbours().isEmpty()){
	            return;
	        }
	        Containers.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).removeColor(color);
	    }
	    else if(state == ActionState.ItemPicker){
	        //set prev empty color
	        if(player.getRoom().getItems().isEmpty()){
	            return;
	        }
	        Containers.items.get(player.getRoom().getItems().get(selectedSlot)).removeColor(color);
	    }
	    else if(state == ActionState.InInventory){
	        //set prev empty color
	        if(player.getInventory().isEmpty()){
	            return;
	        }
	        Containers.items.get(player.getInventory().get(selectedSlot)).removeColor(color);
	    }
	}
	
	/**
	 * Akkor fut le, amikor a játékos megnyomja a "Kiválasztás" gombját.
	 */
	private void chooseSelected() {
	    if(state == ActionState.RoomPicker && !player.getRoom().getNeighbours().isEmpty()){
	        Containers.rooms.get(player.getRoom().getNeighbours().get(selectedSlot)).removeColor(color);
	        player.enterRoom(player.getRoom().getNeighbours().get(selectedSlot));
	    }
	    else if(state == ActionState.ItemPicker && !player.getRoom().getItems().isEmpty()){
	        Containers.items.get(player.getRoom().getItems().get(selectedSlot)).removeColor(color);
	        player.pickupItem(player.getRoom().getItems().get(selectedSlot));
	    }
	    else if(state == ActionState.InInventory && !player.getInventory().isEmpty()){
	        Containers.items.get(player.getInventory().get(selectedSlot)).removeColor(color);
	        player.activate(player.getInventory().get(selectedSlot));
	    }
	}
	
	/**
	 * A kijelölő jobbra mozgatásakor fut le. Lépteti a selectedSlot-ot a következő szobára, szobabeli tárgyra vagy inventory-beli tárgyra.
	 */
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
	
	/**
	 * A kijelölő balra mozgatásakor fut le. Lépteti a selectedSlot-ot a következő szobára, szobabeli tárgyra vagy inventory-beli tárgyra.
	 */
	private void decreaseSelectedSlot() {
	    if(selectedSlot>0){
	        selectedSlot--;
	    }
	    else if(state == ActionState.RoomPicker){
	        if(player.getRoom().getNeighbours().isEmpty()){
	            selectedSlot = 0;
	        }
	        else {
	            selectedSlot=player.getRoom().getNeighbours().size()-1;
	        }
	    }
	    else if(state == ActionState.ItemPicker){
	        selectedSlot=player.getRoom().getItems().size()-1;
	    }
	    else if(state == ActionState.InInventory){
	        selectedSlot=player.getInventory().size()-1;
	    }
	}
	
	/**
	 * A billentyűk lenyomására végbemenő események.
	 */
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
	                chooseSelected();
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
	                chooseSelected();
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
	
	/**
	 * Kötelezően megvalósítandó függvénye a KeyListener-nek, mi erre az eseményre nem csniálunk semmit.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Kötelezően megvalósítandó függvénye a KeyListener-nek, mi erre az eseményre nem csniálunk semmit.
	 */
	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * A következőkről kap értesítést:
	 * a játékosának karaktere megnyerte a játékot,
	 * a játékosának karaktere elkábult,
	 * a játékosának karakterét kirúgták,
	 * a játékosának karaktere átment egy másik szobába,
	 * a játékosának karakterének lejár egy időérzékeny tárgya,
	 * a játékosának karakterének szobájából eltűnik egy tárgy,
	 * a játékosának karakterének szobájának megváltoznak a szomszédai,
	 * a játékosának karaktere belépett egy elátkozott szobába,
	 * a játékosának karakterének szobájának szomszédai közül eltűnt egy elátkozott szoba.
	 */
	@Override
	public void propertyChanged(String property) {
	    if(property.equals("studentwon")){ // küldő: SlideRule
	        labyrinth.notifySubsribers("gamewon");
	    } else if(property.equals("stun")) { // küldő: Model.Character
	    	setCharacterImage();
	    } else if(property.equals("kicked")) { // küldő: Teacher
			isStudentAlive=false;
			clearColor();
			GameFrame.viewCharacters.remove(Containers.characters.get(player));
			GameFrame.mainPanel.remove(Containers.characters.get(player));
			Containers.characters.remove(player);
			room.unsubscribe(this);
			player.unsubscribe(this);
			labyrinth.unsubscribe(this);
			setCharacterImage();
			for(ViewRoom vr : GameFrame.viewRooms) {
				vr.removeColor(color);
			}
			labyrinth.notifySubsribers("gamelost");
		} else if(property.equals("characters")) { // küldő: Room
			if(!room.getCharacters().contains(player)) {
				if(state==ActionState.ItemPicker && !room.getItems().isEmpty()) {
					Containers.items.get(room.getItems().get(selectedSlot)).removeColor(color);
				} else if(state==ActionState.RoomPicker && !room.getNeighbours().isEmpty()) {
					Containers.rooms.get(room.getNeighbours().get(selectedSlot)).removeColor(color);
				}
				selectedSlot=0;
				room.unsubscribe(this);
				setRoomSubscribed();
				setNewColor();
			}
		} else if(property.contains("inventory removed")) { // küldő: Model.Character, DecayingItem, NumberOfUsesItem
			// ha elhasználódik egy decaying akkor a selectedSlot reagáljon rá
			if(state==ActionState.InInventory) {
				int idx= Integer.parseInt(property.split(" ")[2]);
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
		} else if(property.contains("items removed")) { // küldő: Room
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
		} else if(property.equals("modifyneighbours")) { // küldő: Labyrinth
			if(state==ActionState.RoomPicker) {
				clearColor();
			}
		} else if(property.equals("neighboursmodified")) { // küldő: Labyrinth
			if(state==ActionState.RoomPicker) {
				selectedSlot=0;
				setNewColor();
			}
		} else if(property.contains("enteredcursedroom")) { // küldő: CursedRoom
			for(ViewRoom vr : GameFrame.viewRooms) {
				vr.removeColor(color);
			}
		} else if(property.contains("cursedremovedfromneighbours")) { // küldő: ViewCursedRoom
			if(state==ActionState.RoomPicker) {
				int idx=Integer.parseInt(property.split(" ")[1]);	// labyrinthon belüli index
				Room justremoved=labyrinth.getRooms().get(idx);
				ArrayList<Room> neighboursbeforecursedremoved=new ArrayList<Room>(room.neighbours);
				int justremovedselectionpos=room.neighbours.indexOf(justremoved);
				neighboursbeforecursedremoved.removeIf(szoba -> ((room.neighbours.contains(szoba) && !room.getNeighbours().contains(szoba)) && szoba != justremoved));
				justremovedselectionpos=neighboursbeforecursedremoved.indexOf(justremoved);
				if(selectedSlot==justremovedselectionpos) {
					Containers.rooms.get(justremoved).removeColor(color);
					selectedSlot=0;
					setNewColor();
				}else if(selectedSlot>justremovedselectionpos) {
					selectedSlot--;
					setNewColor();
				}else if(selectedSlot<justremovedselectionpos){
					// a selectedSlot után tűnik el a szoba, szóval nem változik semmi
					return;
				}
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
			case Empty:
				break;
			default:
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
			case Empty:
				break;
			default:
				break;
	        }
	    }
	}
}