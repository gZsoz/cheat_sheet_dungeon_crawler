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

	public Room getRoom() {
		return room;
	}

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

	protected Coordinates[] fixedItemPositions;

	protected Coordinates[] fixedCharacterPositions;
	
	/**
	 * Kijelölt-e az adott szoba (szoba váltásnál) és ha igen milyen színnel.
	 */
	protected SelectionColor selected;

	public ViewRoom(Room r, Coordinates pos){
		room = r;
		coordinates = pos;
		image = ImageReader.loadImage("res/images/test/testroom.png");
		size = new Size(r.getCapacity() * 90,220);
		selected = SelectionColor.Empty;
		this.setBackground(null);
		GameFrame.container.add(this);
    	GameFrame.viewRooms.add(this);
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

	private void createViewItem(Item item, int i) {
		if(item instanceof AirFreshener){
			new ViewAirFreshener((AirFreshener) item, fixedItemPositions[i]);
		}
		else if(item instanceof BatSkin){
			new ViewBatSkin((BatSkin) item, fixedItemPositions[i]);
		}
		else if(item instanceof Beer){
			new ViewBeer((Beer) item, fixedItemPositions[i]);
		}
		else if(item instanceof CabbageCamembert){
			new ViewCabbageCamembert((CabbageCamembert) item, fixedItemPositions[i]);
		}
		else if(item instanceof FakeBatSkin){
			new ViewFakeBatSkin((FakeBatSkin) item, fixedItemPositions[i]);
		}
		else if(item instanceof FakeMask){
			new ViewFakeMask((FakeMask) item, fixedItemPositions[i]);
		}
		else if(item instanceof FakeSlideRule){
			new ViewFakeSlideRule((FakeSlideRule) item, fixedItemPositions[i]);
		}
		else if(item instanceof Mask){
			new ViewMask((Mask) item, fixedItemPositions[i]);
		}
		else if(item instanceof SlideRule){
			new ViewSlideRule((SlideRule) item, fixedItemPositions[i]);
		}
		else if(item instanceof Transistor){
			new ViewTransistor((Transistor) item, fixedItemPositions[i]);
		}
		else if(item instanceof WetCloth){
			new ViewWetCloth((WetCloth) item, fixedItemPositions[i]);
		}
	}
	
	private void createViewItems() {
		setFixedItemPositions();
		for(int i = 0; i < room.getItems().size(); i++){
			Item item = room.getItems().get(i);
			createViewItem(item, i);
		}
	}

	private void setCharacterPositions() {
		for(int i=0;i<room.getCharacters().size();i++) {
			Controller.characters.get(room.getCharacters().get(i)).setCoordinates(fixedCharacterPositions[i]);
		}
	}
	
	private void setItemPositions() {
		for(int i=0;i<room.getItems().size();i++) {
			Controller.items.get(room.getItems().get(i)).setCoordinates(fixedItemPositions[i]);
		}
	}
	
	private void createViewCharacters() {
		setFixedCharacterPositions();
		for(int i = 0; i < room.getCharacters().size(); i++){
			Character character = room.getCharacters().get(i);
			if(character instanceof Student){
				//charactersInRoom.add(new ViewStudent((Student) character, fixedCharacterPositions[i]));
				ViewCharacter c=Controller.characters.get(character);
				c.setCoordinates(fixedCharacterPositions[i]);
				GameFrame.viewCharacters.add(c);
			}
			else if(character instanceof Teacher){
				new ViewTeacher((Teacher) character, fixedCharacterPositions[i]);
			}
			else if(character instanceof Cleaner){
				new ViewCleaner((Cleaner) character, fixedCharacterPositions[i]);
			}
		}
	}

	private void createViewEnvFactors() {
		for(int i = 0; i < room.getEnvironmentalFactors().size(); i++){
			EnvironmentalFactors factor = room.getEnvironmentalFactors().get(i);
			if(factor instanceof Gas){
				new ViewGas((Gas) factor, coordinates);
			}
			else if(factor instanceof Sticky){
				new ViewSticky((Sticky) factor, coordinates);
			}
		}
	}

	@Override
	public void propertyChanged(String property) {
	    if(property.equals("characters")) {
	    	setFixedCharacterPositions();
	    	setCharacterPositions();
	    }
	    else if(property.equals("items")) {
	    	setFixedItemPositions();
	    	setItemPositions();
	    }
	    else if(property.contains("spawnitem")) {
	    	int idx = Integer.parseInt(property.split(" ")[1]);
	    	setFixedItemPositions();
	    	createViewItem(room.getItems().get(idx), idx);
	    	setItemPositions();
	    }
	}
	
	public void addview() {
		createViewCharacters();
		createViewItems();
		createViewEnvFactors();
	}
	
	/**
	 * A szoba és benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		if(selected == SelectionColor.Red){
			g2D.setColor(Color.RED);
			g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}
		else if(selected == SelectionColor.Blue){
			g2D.setColor(Color.BLUE.brighter());
			g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}

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

	public void setColor(SelectionColor selectionColor) {
		selected = selectionColor;
	}
}
