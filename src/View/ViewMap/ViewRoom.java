package View.ViewMap;

import Items.*;
import Map.Room;
import View.Utils.*;
import View.ViewCharacter.ViewCharacter;
import View.ViewCharacter.ViewCleaner;
import View.ViewCharacter.ViewStudent;
import View.ViewCharacter.ViewTeacher;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import View.ViewItem.ViewAirFreshener;
import View.ViewItem.ViewBatSkin;
import View.ViewItem.ViewItem;
import Character.Character;
import Character.Student;
import Character.Teacher;
import Character.Cleaner;
import View.ViewItem.ViewSlideRule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A szoba grafikus osztálya.
 */
public class ViewRoom extends JComponent implements Subscriber {
	
	/**
	 * A modellbeli szoba, amit reprezentál.
	 */
	protected Room room;
	
	/**
	 * A szoba képe, ami megjelenik.
	 */
	protected Image image;
	
	/**
	 * A szoba képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	protected Coordinates coordinates;
	
	/**
	 * A szobában megjelenítendő tárgyak.
	 */
	protected ArrayList<ViewItem> itemsInRoom = new ArrayList<>();

	protected Coordinates[] fixedItemPositions;
	
	/**
	 * A szobában megjelenítendő karakterek.
	 */
	protected ArrayList<ViewCharacter> charactersInRoom = new ArrayList<>();
	
	/**
	 * A szobában megjelenítendő környezeti tényezők.
	 */
	protected ArrayList<ViewEnvironmentalFactors> environmentalFactorsInRoom = new ArrayList<>();
	
	/**
	 * Kijelölt-e az adott szoba (szoba váltásnál) és ha igen milyen színnel.
	 */
	protected SelectionColor selected;

	public ViewRoom(Room r, Coordinates pos){
		room = r;
		coordinates = pos;
		image = ImageReader.loadImage("res/images/test/testroom.png");
		size = new Size(100 + r.getCapacity() * 68,220);
		setFixedItemPositions();
		initRoom();
		// room.subscribe(this);
	}

	private void setFixedItemPositions() {
		if(!room.getItems().isEmpty()){
			fixedItemPositions = new Coordinates[room.getItems().size()];
			int startingXPos = coordinates.getX() + (size.getWidth() - (room.getItems().size() * 40 + 15)) / 2;
			for(int i = 0; i<room.getItems().size(); i++){
				startingXPos += 5;
				fixedItemPositions[i] = new Coordinates(startingXPos + i * 40, coordinates.getY() + size.getHeight() * 7/9);
			}
		}
	}

	private void initRoom() {
		createViewCharacters();
		createViewItems();
	}

	private void createViewItems() {
		itemsInRoom.clear();
		// View tárgyak (ami szobákban van)
		for(int i = 0; i < room.getItems().size(); i++){
			Item item = room.getItems().get(i);
			if(item instanceof AirFreshener){
				itemsInRoom.add(new ViewAirFreshener((AirFreshener) item, "res/images/test/items/testitem.png", fixedItemPositions[i]));
			}
			else if(item instanceof BatSkin){
				//itemsInRoom.add(new ViewBatSkin((BatSkin) item));
			}

			else if(item instanceof SlideRule){
				itemsInRoom.add(new ViewSlideRule((SlideRule) item, fixedItemPositions[i]));
			}
		}
	}

	private void createViewCharacters() {
		charactersInRoom.clear();
		// View Karakterek létrehozása
		for(int i = 0; i < room.getCharacters().size(); i++){
			Character character = room.getCharacters().get(i);
			if(character instanceof Student){
				charactersInRoom.add(new ViewStudent((Student) character, coordinates));
			}
			else if(character instanceof Teacher){
				charactersInRoom.add(new ViewTeacher((Teacher) character));
			}
			else if(character instanceof Cleaner){
				charactersInRoom.add(new ViewCleaner((Cleaner) character));
			}
		}
	}

	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * A szoba és benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

		for(ViewEnvironmentalFactors venvfact : environmentalFactorsInRoom){
			venvfact.paint(g);
		}
		for(ViewItem vitem : itemsInRoom){
			vitem.paint(g);
		}
		for(ViewCharacter vcharacter : charactersInRoom){
			vcharacter.paint(g);
		}
	}
}
