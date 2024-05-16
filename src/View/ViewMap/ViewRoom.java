package View.ViewMap;

import Items.*;
import Map.Room;
import View.Controller.Controller;
import View.Utils.*;
import View.ViewCharacter.*;
import View.ViewEnvironmentalFactor.*;
import View.ViewItem.*;
import Character.*;
import Character.Character;
import EnvironmentalFactor.*;
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
	public Coordinates coordinates;
	
	/**
	 * A szobában megjelenítendő tárgyak.
	 */
	public ArrayList<ViewItem> itemsInRoom = new ArrayList<>();

	protected Coordinates[] fixedItemPositions;

	protected Coordinates[] fixedCharacterPositions;
	
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
		size = new Size(r.getCapacity() * 90,220);
		setFixedItemPositions();
		setFixedCharacterPositions();
		initRoom();
		this.setBackground(null);
		Controller.rooms.put(r, this);
		room.subscribe(this);
	}
	
	private void setFixedItemPositions() {
		if(!room.getItems().isEmpty()){
			fixedItemPositions = new Coordinates[room.getItems().size()];
			int startingXPos = coordinates.getX() + (size.getWidth() - (room.getItems().size() * 50 )) / 2;
			for(int i = 0; i<room.getItems().size(); i++){
				startingXPos += 5;
				fixedItemPositions[i] = new Coordinates(startingXPos + i * 40, coordinates.getY() + size.getHeight() * 7/9);
				startingXPos += 5;
			}
		}
	}

	private void setFixedCharacterPositions() {
		if(!room.getCharacters().isEmpty()){
			fixedCharacterPositions = new Coordinates[room.getCharacters().size()];
			int startingXPos = coordinates.getX() + (size.getWidth() - (room.getCharacters().size() * 84)) / 2;
			for(int i = 0; i<room.getCharacters().size(); i++){
				startingXPos += 10;
				fixedCharacterPositions[i] = new Coordinates(startingXPos + i * 80, coordinates.getY()+10);

			}
		}
	}

	private void initRoom() {
		createViewCharacters();
		createViewItems();
		createViewEnvFactors();
	}

	private void createViewItems() {
		itemsInRoom.clear();
		// View tárgyak (ami szobákban van)
		for(int i = 0; i < room.getItems().size(); i++){
			Item item = room.getItems().get(i);
			if(item instanceof AirFreshener){
				itemsInRoom.add(new ViewAirFreshener((AirFreshener) item, fixedItemPositions[i]));
			}
			else if(item instanceof BatSkin){
				itemsInRoom.add(new ViewBatSkin((BatSkin) item, fixedItemPositions[i]));
			}
			else if(item instanceof Beer){
				itemsInRoom.add(new ViewBeer((Beer) item, fixedItemPositions[i]));
			}
			else if(item instanceof CabbageCamembert){
				itemsInRoom.add(new ViewCabbageCamembert((CabbageCamembert) item, fixedItemPositions[i]));
			}
			else if(item instanceof FakeBatSkin){
				itemsInRoom.add(new ViewFakeBatSkin((FakeBatSkin) item, fixedItemPositions[i]));
			}
			else if(item instanceof FakeMask){
				itemsInRoom.add(new ViewFakeMask((FakeMask) item, fixedItemPositions[i]));
			}
			else if(item instanceof FakeSlideRule){
				itemsInRoom.add(new ViewFakeSlideRule((FakeSlideRule) item, fixedItemPositions[i]));
			}
			else if(item instanceof Mask){
				itemsInRoom.add(new ViewMask((Mask) item, fixedItemPositions[i]));
			}
			else if(item instanceof SlideRule){
				itemsInRoom.add(new ViewSlideRule((SlideRule) item, fixedItemPositions[i]));
			}
			else if(item instanceof Transistor){
				itemsInRoom.add(new ViewTransistor((Transistor) item, fixedItemPositions[i]));
			}
			else if(item instanceof WetCloth){
				itemsInRoom.add(new ViewWetCloth((WetCloth) item, fixedItemPositions[i]));
			}
		}
	}

	private void setCharacterPositions() {
		for(int i=0;i<room.getCharacters().size();i++) {
			Controller.characters.get(room.getCharacters().get(i)).setCoordinates(fixedCharacterPositions[i]);
		}
	}
	
	private void createViewCharacters() {
		charactersInRoom.clear();
		// View Karakterek létrehozása
		for(int i = 0; i < room.getCharacters().size(); i++){
			Character character = room.getCharacters().get(i);
			if(character instanceof Student){
				//charactersInRoom.add(new ViewStudent((Student) character, fixedCharacterPositions[i]));
				Controller.characters.get(character).setCoordinates(fixedCharacterPositions[i]);
			}
			else if(character instanceof Teacher){
				charactersInRoom.add(new ViewTeacher((Teacher) character, fixedCharacterPositions[i]));
			}
			else if(character instanceof Cleaner){
				charactersInRoom.add(new ViewCleaner((Cleaner) character, fixedCharacterPositions[i]));
			}
		}
	}

	private void createViewEnvFactors() {
		environmentalFactorsInRoom.clear();
		// View Környezeti tényezők létrehozása
		for(int i = 0; i < room.getEnvironmentalFactors().size(); i++){
			EnvironmentalFactors factor = room.getEnvironmentalFactors().get(i);
			if(factor instanceof Gas){
				environmentalFactorsInRoom.add(new ViewGas((Gas) factor, coordinates));
			}
			else if(factor instanceof Sticky){
				environmentalFactorsInRoom.add(new ViewSticky((Sticky) factor, coordinates));
			}
		}
	}

	@Override
	public void propertyChanged(String property) {
	    if(property.equals("characters")) {
	    	setFixedCharacterPositions();
	    	setCharacterPositions();
	    }
	}
	
	public void addview() {
		for(ViewItem r:itemsInRoom)
			GameFrame.container.add(r);
		for(ViewCharacter r:charactersInRoom)
			GameFrame.container.add(r);
	}
	
	/**
	 * A szoba és benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

		
		//for(ViewEnvironmentalFactors venvfact : environmentalFactorsInRoom){
		//	venvfact.paint(g);
		//}
		//for(ViewItem vitem : itemsInRoom){
		//	vitem.paint(g);
		//}
		//for(ViewCharacter vcharacter : charactersInRoom){
		//	vcharacter.paint(g);
		//}
	}
}
