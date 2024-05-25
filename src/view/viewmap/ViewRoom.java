package view.viewmap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

import model.characters.Character;
import model.characters.Cleaner;
import model.characters.Student;
import model.characters.Teacher;
import model.environmentalfactors.EnvironmentalFactor;
import model.environmentalfactors.Gas;
import model.environmentalfactors.Sticky;
import model.items.Item;
import model.items.decayingitems.Beer;
import model.items.decayingitems.FakeMask;
import model.items.decayingitems.Mask;
import model.items.decayingitems.WetCloth;
import model.items.numberofusesitems.AirFreshener;
import model.items.numberofusesitems.BatSkin;
import model.items.numberofusesitems.CabbageCamembert;
import model.items.numberofusesitems.FakeBatSkin;
import model.items.numberofusesitems.FakeSlideRule;
import model.items.numberofusesitems.SlideRule;
import model.items.specialitems.Transistor;
import model.map.Room;
import view.utils.Containers;
import view.utils.Coordinates;
import view.utils.GameFrame;
import view.utils.ImageReader;
import view.utils.SelectionColor;
import view.utils.Size;
import view.utils.Subscriber;
import view.viewcharacters.ViewCharacter;
import view.viewcharacters.ViewCleaner;
import view.viewcharacters.ViewTeacher;
import view.viewenvironmentalfactors.ViewGas;
import view.viewenvironmentalfactors.ViewSticky;
import view.viewitems.ViewItem;
import view.viewitems.viewdecayingitems.ViewBeer;
import view.viewitems.viewdecayingitems.ViewFakeMask;
import view.viewitems.viewdecayingitems.ViewMask;
import view.viewitems.viewdecayingitems.ViewWetCloth;
import view.viewitems.viewnumberofusesitems.ViewAirFreshener;
import view.viewitems.viewnumberofusesitems.ViewBatSkin;
import view.viewitems.viewnumberofusesitems.ViewCabbageCamembert;
import view.viewitems.viewnumberofusesitems.ViewFakeBatSkin;
import view.viewitems.viewnumberofusesitems.ViewFakeSlideRule;
import view.viewitems.viewnumberofusesitems.ViewSlideRule;
import view.viewitems.viewspecialitems.ViewTransistor;

/**
 * A szoba grafikus osztálya.
 */
@SuppressWarnings("serial")
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
	 * A szobák képeinek elérési útja.
	 */
	protected String roomPath = "Rooms/";
	
	/**
	 * A szoba képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	public Coordinates coordinates;
	
	/**
	 * A tárgyak pozíciói a szobán belül.
	 */
	protected Coordinates[] fixedItemPositions;
	
	/**
	 * A pin-ek pozíciói a szoba szélén.
	 */
	protected Coordinates[] fixedRoutePins;
	
	/**
	 * A karakterek pozíciói a szobán belül.
	 */
	protected Coordinates[] fixedCharacterPositions;
	
	/**
	 * Kijelölt-e az adott szoba (szoba váltásnál) és ha igen milyen színnel.
	 */
	protected SelectionColor selected;
	
	/**
	 * Konstruktor egy szoba nézet létrehozásához.
	 * @param r a modellbeli szoba
	 * @param coor a koordináták
	 */
	public ViewRoom(Room r, Coordinates coor){
		room = r;
		coordinates = coor;
		size = new Size(360,220);
		setFixedRoutePins();
		image = ImageReader.loadImage(ImageReader.path + roomPath + "room.png");
		selected = SelectionColor.Empty;
		this.setBackground(null);
		GameFrame.mainPanel.add(this);
		GameFrame.viewRooms.add(this);
		Containers.rooms.put(r, this);
		room.subscribe(this);
	}
	
	/**
	 * Kijelölőszín lekérdezése.
	 * @return a kijelölőszín
	 */
	public SelectionColor getSelected() {
		return selected;
	}
	
	/**
	 * A modellbeli szoba lekérdezése.
	 * @return a modellbeli szoba
	 */
	public Room getRoom() {
		return room;
	}
	
	/**
	 * A pin-ek pozícióinak lekérdezése.
	 * @return a pin-ek pozíciói
	 */
	public Coordinates[] getFixedRoutePins() {
		return fixedRoutePins;
	}
	
	/**
	 * A szobán belüli tárgyak pozícióinak kiszámítása.
	 */
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
	
	/**
	 * A szobán belüli karakterek pozícióinak kiszámítása.
	 */
	private void setFixedCharacterPositions() {
		if(!room.getCharacters().isEmpty()){
			fixedCharacterPositions = new Coordinates[room.getCharacters().size()];
			int startingXPos = coordinates.getX() + (size.getWidth() - (room.getCharacters().size() * 84)) / 2;
			for(int i = 0; i<room.getCharacters().size(); i++){
				startingXPos += 10;
				fixedCharacterPositions[i] = new Coordinates(startingXPos + i * 80, coordinates.getY()+18);
			}
		}
	}
	
	/**
	 * A pin-ek pozícióinak beállítása.
	 */
	private void setFixedRoutePins(){
		fixedRoutePins = new Coordinates[]{
			new Coordinates(coordinates.getX() - 20 -1, coordinates.getY() + size.getHeight() / 2 - 20),
			new Coordinates(coordinates.getX() + size.getWidth()/2 - 20, coordinates.getY() - 20 - 2),
			new Coordinates(coordinates.getX() + size.getWidth() - 20 + 3, coordinates.getY() + size.getHeight() / 2 - 20),
			new Coordinates(coordinates.getX() + size.getWidth()/2 - 20, coordinates.getY() + size.getHeight() - 20 + 4)
		};
	}
	
	/**
	 * A szobán belüli tárgypozíciók beállítása, majd azokra tárgyak létrehozása.
	 */
	private void createViewItems() {
		setFixedItemPositions();
		for(int i = 0; i < room.getItems().size(); i++){
			Item item = room.getItems().get(i);
			createViewItem(item, i);
		}
	}
	
	/**
	 * Egy tárgy nézet létrehozása egy modellbeli tárgyból és elhelyezése a megfelelő pozícióra.
	 * @param item a modellbeli tárgy
	 * @param i a megfelelő pozíció indexe
	 */
	private void createViewItem(Item item, int i) {
		if(item instanceof AirFreshener){
			new ViewAirFreshener((AirFreshener) item, fixedItemPositions[i]);
		}
		else if(item instanceof FakeBatSkin){
			new ViewFakeBatSkin((FakeBatSkin) item, fixedItemPositions[i]);
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
		else if(item instanceof FakeSlideRule){
			new ViewFakeSlideRule((FakeSlideRule) item, fixedItemPositions[i]);
		}
		else if(item instanceof SlideRule){
			new ViewSlideRule((SlideRule) item, fixedItemPositions[i]);
		}
		else if(item instanceof FakeMask){
			new ViewFakeMask((FakeMask) item, fixedItemPositions[i]);
		}
		else if(item instanceof Mask){
			new ViewMask((Mask) item, fixedItemPositions[i]);
		}
		else if(item instanceof Transistor){
			new ViewTransistor((Transistor) item, fixedItemPositions[i]);
		}
		else if(item instanceof WetCloth){
			new ViewWetCloth((WetCloth) item, fixedItemPositions[i]);
		}
	}
	
	/**
	 * A szobán belüli karakterpozíciók beállítása, majd azokra karakterek létrehozása.
	 */
	private void createViewCharacters() {
		setFixedCharacterPositions();
		for(int i = 0; i < room.getCharacters().size(); i++){
			Character character = room.getCharacters().get(i);
			if(character instanceof Student){
				ViewCharacter c = Containers.characters.get(character);
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
	
	/**
	 * A szobán belüli környezeti változók pozíciójának beállítása, majd azokra környezeti változók létrehozása.
	 */
	private void createViewEnvFactors() {
		for(int i = 0; i < room.getEnvironmentalFactors().size(); i++){
			EnvironmentalFactor factor = room.getEnvironmentalFactors().get(i);
			createViewEnvFactor(factor);
		}
	}
	
	/**
	 * Egy környezeti változó nézet létrehozása egy modellbeli környezeti változóból és elhelyezése a megfelelő pozícióra.
	 * @param factor a modellbeli környezeti változó
	 */
	private void createViewEnvFactor(EnvironmentalFactor factor) {
		if(factor instanceof Gas){
			new ViewGas((Gas) factor, coordinates);
		}
		else if(factor instanceof Sticky){
			new ViewSticky((Sticky) factor, coordinates);
		}
	}
	
	/**
	 * A karakterek koordinátáinak beállítása a megfelelő szobán belüli karakterpozícióra.
	 */
	private void setCharacterPositions() {
		for(int i=0;i<room.getCharacters().size();i++) {
			Containers.characters.get(room.getCharacters().get(i)).setCoordinates(fixedCharacterPositions[i]);
		}
	}
	
	/**
	 * A tárgyak koordinátáinak beállítása a megfelelő szobán belüli tárgypozícióra.
	 */
	private void setItemPositions() {
		for(int i=0;i<room.getItems().size();i++) {
			ViewItem item=Containers.items.get(room.getItems().get(i));
			item.setCoordinates(fixedItemPositions[i]);
			item.setItemSize(ViewItem.roomSize);
			item.setItemImage();
		}
	}
	
	/**
	 * A következőkről kap értesítést:
	 * a szobában tartózkodó karakterek listája megváltozott,
	 * a szobában lévő tárgyak listája megváltozott,
	 * a szobába spawn-olt egy tárgy,
	 * a szobába spawn-olt egy környezeti változó,
	 * a szoba el lett távolítva a modellből,
	 * a szobában tartózkodó környezeti változók listája megváltozott
	 */
	@Override
	public void propertyChanged(String property) {
	    if(property.equals("characters")) { // küldő: Room
	    	setFixedCharacterPositions();
	    	setCharacterPositions();
	    }
	    else if(property.contains("items")) { // kell a contains!! // küldő: Room
	    	setFixedItemPositions();
	    	setItemPositions();
	    }
	    else if(property.contains("spawnitem")) { // kell a contains!! // küldő: Room
	    	int idx = Integer.parseInt(property.split(" ")[1]);
	    	setFixedItemPositions();
	    	createViewItem(room.getItems().get(idx), idx);
	    	setItemPositions();
	    }else if(property.contains("spawnfactor")) { // kell a contains!! // küldő: Room
	    	int idx = Integer.parseInt(property.split(" ")[1]);
	    	createViewEnvFactor(room.getEnvironmentalFactors().get(idx));
	    }else if(property.equals("roomremoved")) { // küldő: Labyrinth
	    	Containers.rooms.remove(room);
			GameFrame.viewRooms.remove(this);
			GameFrame.mainPanel.remove(this);
			room.unsubscribe(this);
	    }else if(property.equals("factors")) { // küldő: Room, Cleaner, Sticky, AirFreshener
	    	for(EnvironmentalFactor env : room.getEnvironmentalFactors()) {
	    		Containers.envs.get(env).setCoordinates(coordinates);
	    	}
	    }
	}
	
	/**
	 * A szobán belüli nézetek létrehozása.
	 */
	public void addview() {
		createViewCharacters();
		createViewItems();
		createViewEnvFactors();
	}
	
	/**
	 * Beállítja, hogy melyik játékos jelöli ki éppen a szobát.
	 * @param selectionColor a kijelölő játékos színe
	 */
	public void setColor(SelectionColor selectionColor) {
		if(selected==SelectionColor.Empty)
			selected = selectionColor;
		else if(selected!=selectionColor)
			selected = SelectionColor.Both;
	}
	
	/**
	 * Eltávolítja egy játékos kijelölését a szobáról. 
	 * @param selectionColor a játékos színe
	 */
	public void removeColor(SelectionColor selectionColor) {
		if(selected==selectionColor)
			selected = SelectionColor.Empty;
		else if(selected==SelectionColor.Both) {
			if(selectionColor==SelectionColor.Blue)
				selected=SelectionColor.Red;
			else if(selectionColor==SelectionColor.Red)
				selected=SelectionColor.Blue;	
		}	
	}
	
	/**
	 * A szoba kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(10));
		if(selected == SelectionColor.Red){
			g2D.setColor(Color.RED);
			g2D.drawRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
			//g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}
		else if(selected == SelectionColor.Blue){
			g2D.setColor(Color.BLUE.brighter());
			g2D.drawRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
			//g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}
		else if(selected == SelectionColor.Both){
			g2D.setColor(Color.BLUE.brighter());
			g2D.drawRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
			//g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
			g2D.setColor(Color.RED);
			g2D.drawRect(coordinates.getX()-5,coordinates.getY()-5,(size.getWidth()+10)/2,size.getHeight()+10);
			//g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,(size.getWidth()+20)/2,size.getHeight()+20);
		}
	
		// szoba kirajzolása
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	
		// szobaméret kiírása a szoba bal felső sarkába
		g2D.setFont(new Font("Monospaced", Font.BOLD, 45));
	    g2D.setColor(new Color(115, 80, 44));
	    g2D.drawString(Integer.toString(room.getCapacity()), coordinates.getX()+2, coordinates.getY()+32);
	}
}
