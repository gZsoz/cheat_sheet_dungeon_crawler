package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import main.Main;
import model.characters.Cleaner;
import model.characters.Student;
import model.characters.Teacher;
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
import model.modelupdate.iTask;
import view.utils.Subscriber;

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth implements iTask {
	
	/**
	 * Ezzel a változóval állítható be a main-ban a tárgyak spawn-olásának gyakoriságának átlagos időtartama.
	 */
	public static int itemSpawnFrequency = 100 * Main.fps;
	
	/**
	 * Ezzel a változóval állítható be a main-ban a szobák összeolvadásának gyakoriságának átlagos időtartama.
	 */
	public static int mergeFrequency = 100 * Main.fps;
	
	/**
	 * Ezzel a változóval állítható be a main-ban a szobák szétválásának gyakoriságának átlagos időtartama.
	 */
	public static int splitFrequency = 100 * Main.fps;
	
	/**
	 * A labirintus változásaira feliratkozott osztályok.
	 */
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * A labirintus szobái.
	 */
	private List<Room> rooms;
	
	/**
	 * Konstruktor egy labirintus létrehozásához.
	 */
	public Labyrinth(){
		rooms=new ArrayList<Room>();
	}
	
	/**
	 * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
	 * függvényüket a paraméterként kapott Stringgel.
	 * @param str üzenet arról, hogy mi változott meg, lehetséges értékek:
	 * "merge <indexOf(result)> <indexOf(merging)>", "split <indexOf(old)>", "modifyneighbours", "gamewon"
	 */
	public void notifySubsribers(String str) {
		for(Subscriber sub : new ArrayList<>(subscribers))
			sub.propertyChanged(str);
	}
	
	/**
	 * Hozzáadja a paraméterként kapott Subscriber objektumot a feliratkózók listájához,
	 * ezentúl a propertyChanged függvénye meghívásával jelzi, ha belső állapota megváltozik.
	 * @param sub a feliratkozó View objektum
	 */
	public void subscribe(Subscriber sub) {
		subscribers.add(sub);
	}
	
	/**
	 * Eltávolítja a paraméterként kapott Subscriber objektumot a feliratkózók listájából,
	 * ezentúl nem kap értesítést, ha az osztály belső állapota megváltozik.
	 * @param sub a leiratkozó View objektum
	 */
	public void unsubscribe(Subscriber sub) {
		subscribers.remove(sub);
	}
	
	/**
	 * A labirintus szobáinak lekérdezése.
	 * @return a labirintus szobái
	 */
	public List<Room> getRooms(){
		return rooms;
	}
	
	/**
	 * Szobát ad a labirintushoz.
	 * @param r a hozzáadandó szoba
	 */
	public void addRoom(Room r) {
		Main.printLog("addRoom");
		rooms.add(r);
	}
	
	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param a törlendő szoba
	 */
	public void removeRoom(Room r) {
		Main.printLog("removeRoom");
		rooms.remove(r);
	}
	
	/**
	 * Összeolvaszt egy szobát egy másikkal a labirintusban.
	 * @param result az összeolvadt szobák eredménye
	 * @param merging a beolvasztandó szoba
	 */
	public void mergeRoom(Room result, Room merging) {
		Main.printLog("mergeRoom");
		if(result.equals(merging))
			return;
		int bigger;
		if(result.getCapacity()>merging.getCapacity()) bigger=result.getCapacity();
		else bigger=merging.getCapacity();
		if(result.getCharacters().size()+merging.getCharacters().size()>bigger){
			return;
		}
		else if(result.getNeighbours().isEmpty() || merging.getNeighbours().isEmpty()){
			return;
		}
		else{
			notifySubsribers("merge "+rooms.indexOf(result)+" "+rooms.indexOf(merging));
			notifySubsribers("modifyneighbours");
			result.setCapacity(bigger);
			result.merge(merging);
			this.removeRoom(merging);
			merging.notifySubscribers("roomremoved");
			notifySubsribers("neighboursmodified");
		}
	}
	
	/**
	 * Egy szoba kettéosztása a labirintusban a tesztprogramhoz determisztikus.
	 * @param r a szétosztandó szoba
	 */
	public void splitRoomTest(Room old, int neighbourcnt, int itemcnt, int charactercnt) {
		Main.printLog("splitRoom");
		Room n;
		// Az új szoba típusa a régi szobától függ
		if(old instanceof CursedRoom) {
			n=new CursedRoom(old.getCapacity());
		}
		else {
			n=new Room(old.getCapacity());
		}
		// Szomszédok hozzáadása
		for(int i=0; i<neighbourcnt; i++){
			n.addNeighbour(old.getNeighbours().get(0));
			old.getNeighbours().get(0).addNeighbour(n);
			old.getNeighbours().get(0).removeNeighbour(old);
			old.removeNeighbour(old.getNeighbours().get(0));
		}
		old.addNeighbour(n);
		n.addNeighbour(old);
		// Tárgyak hozzáadása
		for(int i=0; i<itemcnt; i++){
			n.addItem(old.getItems().get(0));
			old.removeItem(old.getItems().get(0));
		}
		// Karakterek hozzáadása
		for(int i=0; i<charactercnt; i++){
			old.getCharacters().get(i).enterRoom(n);
		}
		// Környezeti tényezők hozzáadása
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			if(old.getEnvironmentalFactors().get(i) instanceof Gas) n.addEnvironmentalFactor(new Gas(n));
			else if(old.getEnvironmentalFactors().get(i) instanceof Sticky) n.addEnvironmentalFactor(new Sticky(n));
		}
		rooms.add(rooms.indexOf(old)+1, n);
		Main.printLog("Neighbours of old room: "+old.getNeighbours().size());
		Main.printLog("Neighbours of new room: "+n.getNeighbours().size());
		Main.printLog("Model.Items of old room: "+old.getItems().size());
		Main.printLog("Model.Items of new room: "+n.getItems().size());
		Main.printLog("Characters of old room: "+old.getCharacters().size());
		Main.printLog("Characters of new room: "+n.getCharacters().size());
		Main.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		Main.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
		
	}
	
	/**
	 * Egy szoba kettéosztása a labirintusban.
	 * @param r a szétosztandó szoba
	 */
	public void splitRoom(Room old) {
		Main.printLog("splitRoom");
		if(old.getNeighbours().isEmpty())
			return;
		notifySubsribers("modifyneighbours");
		Room n;
		// Az új szoba típusa a régi szobától függ
		if(old instanceof CursedRoom) {
			n=new CursedRoom(old.getCapacity());
		}
		else {
			n=new Room(old.getCapacity());
		}
		// Szomszédok hozzáadása
		Random random=new Random();
		int rand;
		if(!old.getNeighbours().isEmpty()) {
			rand=random.nextInt(old.getNeighbours().size());
			for(int i=0; i<=rand; i++){
				n.addNeighbour(old.getNeighbours().get(0));
				old.getNeighbours().get(0).addNeighbour(n);
				old.getNeighbours().get(0).removeNeighbour(old);
				old.removeNeighbour(old.getNeighbours().get(0));
			}
		}
		old.addNeighbour(n);
		n.addNeighbour(old);
		
		rooms.add(rooms.indexOf(old)+1, n);
		notifySubsribers("split "+rooms.indexOf(old));
		notifySubsribers("neighboursmodified");
		
		// Tárgyak hozzáadása
		if(!old.getItems().isEmpty()) {
			rand=random.nextInt(old.getItems().size());
			for(int i=0; i<=rand; i++){
				n.addItem(old.getItems().get(0));
				old.removeItem(old.getItems().get(0));
			}
		}
		old.notifySubscribers("items");
		n.notifySubscribers("items");
		
		// Karakterek hozzáadása
		if(!old.getCharacters().isEmpty()) {
			rand=random.nextInt(old.getCharacters().size());
			for(int i=0; i<=rand; i++){
				old.getCharacters().get(0).enterRoom(n);
			}
		}
		old.notifySubscribers("characters");
		n.notifySubscribers("characters");
		
		// Környezeti tényezők hozzáadása
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			if(old.getEnvironmentalFactors().get(i) instanceof Gas) n.spawnEnvironmentalFactor(new Gas(n));
			else if(old.getEnvironmentalFactors().get(i) instanceof Sticky) n.spawnEnvironmentalFactor(new Sticky(n)); 
		}
		old.notifySubscribers("factors");
		n.notifySubscribers("factors");
		
		Main.printLog("Neighbours of old room: "+old.getNeighbours().size());
		Main.printLog("Neighbours of new room: "+n.getNeighbours().size());
		Main.printLog("Model.Items of old room: "+old.getItems().size());
		Main.printLog("Model.Items of new room: "+n.getItems().size());
		Main.printLog("Characters of old room: "+old.getCharacters().size());
		Main.printLog("Characters of new room: "+n.getCharacters().size());
		Main.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		Main.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
	}
	
	
	/**
	 * Véletlenszerűen kiválaszt egy tárgyat a megadott típusok közül.
	 * @return a véletlenszerűen kiválasztott tárgy
	 */
	private Item itemPicker(){
		switch(Main.random.nextInt(10, 8)){
		case 0:
			return new FakeBatSkin();
		case 1:
			return new FakeMask();
		case 2:
			return new AirFreshener();
		case 3:
			if(Main.random.nextInt(2, 0)==0)
				return new BatSkin();
			else
				return new FakeBatSkin();
		case 4:
			return new Beer();
		case 5:
			return new CabbageCamembert();
		case 6:
			return new Mask();
		case 7:
			return new Transistor();
		case 8:
			return new WetCloth();
		case 9:
			return new FakeSlideRule();
		default:
			return null;
		}
	}
	
	
	
	/**
	 * Mélységi keresést hajt végre a szobákon, hogy ellenőrizze, elérhető-e minden szoba egy adott szobából.
	 * @param room a szoba, amelyből a mélységi keresést indítjuk
	 * @param rooms a labirintus szobái
	 * @param visited tömb annak számontartására, hogy mely szobákat jártuk be
	 */
	private void dfs(Room room, List<Room> rooms, boolean[] visited) {
	    int index = rooms.indexOf(room);
	    visited[index] = true;
	    for (Room neighbour : room.getNeighbours()) {
	        int neighbourIndex = rooms.indexOf(neighbour);
	        if (!visited[neighbourIndex]) {
	            dfs(neighbour, rooms, visited);
	        }
	    }
	}
	
	/**
	 * Leellenőrzi, hogy minden szobából minden szobába el lehet-e jutni a labirintusban.
	 * @param rooms a labirintus szobái 
	 * @return igaz, ha minden szobából minden szobába el lehet jutni a labirintusban, egyébként hamis
	 */
	private boolean isStronglyConnected(List<Room> rooms) {
	    for (Room room : rooms) { // minden szobából mélységi keresést indítunk, hogy megnézzük, minden szoba elérhető-e belőle
	        boolean[] visited = new boolean[rooms.size()];
	        dfs(room, rooms, visited);
	        for (boolean v : visited) {
	            if (!v) { // ha bármelyik szoba nem elérhető valamelyik szobából, akkor még nem vagyunk készen 
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	
	/**
	 * A szobák generálása a labirintusban.
	 * @param blue a kék játékoshoz tartozó diák
	 * @param red a piros játékoshoz tartozó diák
	 */
	public void generateRooms(Student blue, Student red) {
		Main.printLog("generateRooms");
		
		// szobák legenerálása (7-8 darab, köztük 1-2 elátkozott, mindegyik 2-4 fős)
		int numOfRooms = Main.random.nextInt(2, 1) + 7;
		int numOfCursedRooms = Main.random.nextInt(2, 0) + 1;
		ArrayList<Room> allRooms = new ArrayList<>();
		for(int i = 0; i < numOfCursedRooms; i++) {
			allRooms.add(new CursedRoom(Main.random.nextInt(3, 2) + 2));
		}
		for(int i = 0; i < numOfRooms - numOfCursedRooms; i++) {
			allRooms.add(new Room(Main.random.nextInt(3, 2) + 2));
		}
		Collections.shuffle(allRooms);
		rooms = new ArrayList<Room>(allRooms);
		
		/*EZEN A PONTON BÁRMELYIK SZOBÁBA BÁRMILYEN KÖRNYEZETI VÁLTOZÓT BE LEHET RAKNI*/
		
		// környezeti változók legenerálása a szobákba
		int numberOfEnvFactors = Main.random.nextInt(2, 0) + 1;
		for (int i = 0; i < numberOfEnvFactors; i++) {
			Room gassyRoom = rooms.get(Main.random.nextInt(rooms.size(), 2));
			if (gassyRoom.getEnvironmentalFactors().size() == 0) {
				gassyRoom.addEnvironmentalFactor(new Gas(gassyRoom));
			}
		}
	
		// szomszédos szobák beállítása
		for (Room room : rooms) { // minden szobához végigiterálunk az összes szobán és vagy berakjuk a szomszédok közé vagy nem
	        for (Room otherRoom : rooms) {
	            if (room != otherRoom) { // saját magát nem rakjuk be semmiképpen
	                if (Main.random.nextInt(5, 0) == 0) {
	                    room.addNeighbour(otherRoom);
	                    otherRoom.addNeighbour(room);
	                }
	            }
	        }
	    }
		while (!isStronglyConnected(rooms)) { // addig adogatunk véletlenszerűen szomszédokat, amíg a minden szoba elérhető nem lesz minden szobából
	        for (Room room : rooms) {
	            for (Room otherRoom : rooms) {
	                if (room != otherRoom && !room.getNeighbours().contains(otherRoom)) { // saját magát és már felvett szomszédokat nem rakunk be
	                    if (Main.random.nextInt(3, 0) == 0) {
	                        room.addNeighbour(otherRoom);
	                        otherRoom.addNeighbour(room);
	                    }
	                }
	            }
	        }
	    }
	
		
		// tárgyak legenerálása (0, 1, ..., maxItemCapacity db minden szobába)
		Room roomWithSlideRule = rooms.get(Main.random.nextInt(rooms.size(), 0));
		roomWithSlideRule.addItem(new SlideRule()); // logarléc betétele egy random szobába
		
		/*EZEN A PONTON BÁRMELYIK SZOBÁBA BÁRMILYEN TÁRGYAT BE LEHET RAKNI*/
		rooms.get(0).items.add(new FakeSlideRule());	// balance changes
		
		for(Room r : rooms){ // random mennyiségű tárgy legenerálása a szobákba
			int numberOfSpawnedItems = Main.random.nextInt((Room.maxItemCapacity+1) - 2 - r.currentNumOfItems(), 4) + 2; // hogy mindig legalább kettőt spawnoljon
			for(int i = 0; i < numberOfSpawnedItems; i++) {
				r.addItem(itemPicker());
			}
		}
		
		Collections.shuffle(roomWithSlideRule.getItems());
		
		// diákok legenerálása
		List<Room> roomsWithStudents = new ArrayList<Room>();
		
		Room roomOfFirstStudent = rooms.get(Main.random.nextInt(rooms.size(), 1));
		roomOfFirstStudent.addCharacter(red); // első diák betétele egy random szobába
		red.setRoom(roomOfFirstStudent);
		roomsWithStudents.add(roomOfFirstStudent);
		
		Room roomOfSecondStudent = null;
		while(roomOfSecondStudent == null) {
			int roomidx = Main.random.nextInt(rooms.size(), 0);
			if(!(rooms.get(roomidx).equals(roomOfFirstStudent) && roomOfFirstStudent.getCapacity() == 1)) {
				roomOfSecondStudent = rooms.get(roomidx);
			}
		}
		roomOfSecondStudent.addCharacter(blue); // második diák betétele egy random szabad helyre
		blue.setRoom(roomOfSecondStudent);
		roomsWithStudents.add(roomOfSecondStudent);
		
		List<Room> roomsWithoutStudents = new ArrayList<Room>(rooms);
		roomsWithoutStudents.removeAll(roomsWithStudents);
		
		// tanár legenerálása
		Room roomOfTeacher = roomsWithoutStudents.get(Main.random.nextInt(roomsWithoutStudents.size(), 2));
		roomOfTeacher.addCharacter(new Teacher(roomOfTeacher)); // tanár betétele egy diákmentes szobába egy random szabad helyre
		
		// takarító legenerálása
		Room roomOfCleaner = null;
		while(roomOfCleaner == null) {
			int roomidx = Main.random.nextInt(rooms.size(), 3);
			if(rooms.get(roomidx).getCapacity() != rooms.get(roomidx).currentNumOfPlayers()) {
				roomOfCleaner = rooms.get(roomidx);
			}
		}
		roomOfCleaner.addCharacter(new Cleaner(roomOfCleaner)); // takarító betétele egy random szabad helyre
	}
	
	/**
	 * Bizonyos eséllyel a random események végrehajtása: szobák összeolvasztása, szobaszétválasztás, tárgy spawn-olás.
	 */
	@Override
	public void update() {
		Random rand=new Random();
		if(Main.random.nextInt(mergeFrequency, -1)==0) {
			if (rooms.size() > 2) {
				mergeRoom(rooms.get(rand.nextInt(rooms.size())), rooms.get(rand.nextInt(rooms.size()))); // merge
			}
		}
		if(Main.random.nextInt(splitFrequency, -1)==0) {
			if(rooms.size()<10) {
				splitRoom(rooms.get(rand.nextInt(rooms.size()))); // split
			}
		}
		for(Room r : rooms){
			if(Main.random.nextInt(itemSpawnFrequency, -1)==0) {
				r.spawnItem(itemPicker()); // item legenerálása
			}
			r.update();
		}
	}
}
