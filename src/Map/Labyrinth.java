package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import EnvironmentalFactor.Gas;
import EnvironmentalFactor.Sticky;
import Items.*;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import View.Utils.Subscriber;
import Character.*;

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth implements iTask{
	
	/*
	 * A labirintusban található szobák.
	 */
	private List<Room> rooms;
	
	/*
	 * A labirintus változásaira feliratkozott osztályok.
	 */
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * Konstruktor egy labirintus létrehozásához.
	 */
	public Labyrinth(){
		rooms=new ArrayList<Room>();
	}
	
    /**
     * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String) függvényüket a paraméterként kapott Stringgel.
     * @param str amilyen eseménnyel értesíti a feliratkozókat
     */
    public void notifySubsribers(String str) {
    	for(Subscriber sub : new ArrayList<>(subscribers))
    		sub.propertyChanged(str); // lehetséges értékek: "merge <indexOf(result)> <indexOf(merging)>", "split <indexOf(old)>"
    }
    
    /**
     * Hozzáadja a paraméterként kapott Subscriber objektumot a feliratkózók listájához,
     * ezentúl a propertyChanged függvénye meghívásával jelzi, ha belső állapota megváltozik.
     * @param sub a feliratkozó
     */
    public void subscribe(Subscriber sub) {
    	subscribers.add(sub);
    }
    
    /**
     * Eltávolítja a paraméterként kapott Subscriber objektumot a feliratkózók listájából,
     * ezentúl nem kap értesítést, ha az osztály belső állapota megváltozik.
     * @param sub a feliratkozott
     */
    public void unsubscribe(Subscriber sub) {
    	subscribers.remove(sub);
    }
    
    /**
	 * Visszaadja a labirintus szobáit.
	 * @return a labirintus szobái
	 */
	public List<Room> getRooms(){
		return rooms;
	}

    /**
     * Egy szoba hozzáadása a labirintushoz.
     * @param r a hozzáadandó szoba
     */
	public void addRoom(Room r) {
		ProtoUtil.printLog("addRoom");
		rooms.add(r);
    }

	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param r a törlendő szoba
	 */
	public void removeRoom(Room r) {
		ProtoUtil.printLog("removeRoom");
		rooms.remove(r);
	}

    /**
     * Összeolvaszt egy szobát egy másikkal a labirintusban.
     * @param result az összeolvadt szobák eredménye
     * @param merging a beolvasztandó szoba
     */
	public void mergeRoom(Room result, Room merging) {
		ProtoUtil.printLog("mergeRoom");
		int bigger;
		if(result.getCapacity()>merging.getCapacity()) bigger=result.getCapacity();
		else bigger=merging.getCapacity();
		if(result.getCharacters().size()+merging.getCharacters().size()>bigger){
			return;
		}else{
			notifySubsribers("merge "+rooms.indexOf(result)+" "+rooms.indexOf(merging));
			result.setCapacity(bigger);
			result.merge(merging);
			this.removeRoom(merging);
		}
    }
	
    /**
     * Egy szoba kettéosztása a labirintusban a tesztprogramhoz determisztikus.
     * @param r a szétosztandó szoba
     * @param neighbourcnt szomszédok száma
     * @param itemcnt tárgyak száma
     * @param charactercnt karakterek száma
     */
	public void splitRoomTest(Room old, int neighbourcnt, int itemcnt, int charactercnt) {
		ProtoUtil.printLog("splitRoom");
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
		ProtoUtil.printLog("Neighbours of old room: "+old.getNeighbours().size());
		ProtoUtil.printLog("Neighbours of new room: "+n.getNeighbours().size());
		ProtoUtil.printLog("Items of old room: "+old.getItems().size());
		ProtoUtil.printLog("Items of new room: "+n.getItems().size());
		ProtoUtil.printLog("Characters of old room: "+old.getCharacters().size());
		ProtoUtil.printLog("Characters of new room: "+n.getCharacters().size());
		ProtoUtil.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		ProtoUtil.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
		
    }

    /**
     * Egy szoba kettéosztása a labirintusban.
     * @param r a szétosztandó szoba
     */
	public void splitRoom(Room old) {
		ProtoUtil.printLog("splitRoom");
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
		int rand=random.nextInt(old.getNeighbours().size());
		for(int i=0; i<rand; i++){
			n.addNeighbour(old.getNeighbours().get(0));
			old.getNeighbours().get(0).addNeighbour(n);
			old.getNeighbours().get(0).removeNeighbour(old);
			old.removeNeighbour(old.getNeighbours().get(0));
		}
		old.addNeighbour(n);
		n.addNeighbour(old);
		// Tárgyak hozzáadása
		rand=random.nextInt(old.getItems().size());
		for(int i=0; i<rand; i++){
			n.addItem(old.getItems().get(0));
			old.removeItem(old.getItems().get(0));
		}
		// Karakterek hozzáadása
		rand=random.nextInt(old.getCharacters().size());
		for(int i=0; i<rand; i++){
			old.getCharacters().get(i).enterRoom(n);
		}
		// Környezeti tényezők hozzáadása
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			if(old.getEnvironmentalFactors().get(i) instanceof Gas) n.addEnvironmentalFactor(new Gas(n));
			else if(old.getEnvironmentalFactors().get(i) instanceof Sticky) n.addEnvironmentalFactor(new Sticky(n));
		}
		rooms.add(rooms.indexOf(old)+1, n);
		ProtoUtil.printLog("Neighbours of old room: "+old.getNeighbours().size());
		ProtoUtil.printLog("Neighbours of new room: "+n.getNeighbours().size());
		ProtoUtil.printLog("Items of old room: "+old.getItems().size());
		ProtoUtil.printLog("Items of new room: "+n.getItems().size());
		ProtoUtil.printLog("Characters of old room: "+old.getCharacters().size());
		ProtoUtil.printLog("Characters of new room: "+n.getCharacters().size());
		ProtoUtil.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		ProtoUtil.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
		notifySubsribers("split "+rooms.indexOf(old));
    }

	
	/**
	 * Véletlenszerűen kiválaszt egy tárgyat a megadott típusok közül.
	 * @return a véletlenszerűen kiválasztott tárgy
	 */
	private Item itemPicker(){
		switch(ProtoUtil.random.nextInt(10, 7)){
		case 0:
			return new AirFreshener();
		case 1:
			return new BatSkin();
		case 2:
			return new Beer();
		case 3:
			return new CabbageCamembert();
		case 4:
			return new FakeBatSkin();
		case 5:
			return new FakeMask();
		case 6:
			return new FakeSlideRule();
		case 7:
			return new Mask();
		case 8:
			return new Transistor();
		case 9:
			return new WetCloth();
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
     * @param blue a kék diák
     * @param red a piros diák
     */
	public void generateRooms(Student red, Student blue) {
		ProtoUtil.printLog("generateRooms");
		
		// szobák legenerálása (2-4 fősek)
		Room r1 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r2 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r3 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r4 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r5 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r6 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r7 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r8 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r9 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		Room r10 = new Room(ProtoUtil.random.nextInt(3, 2) + 2);
		rooms.add(r1);
		rooms.add(r2);
		rooms.add(r3);
		rooms.add(r4);
		rooms.add(r5);
		rooms.add(r6);
		rooms.add(r7);
		rooms.add(r8);
		rooms.add(r9);
		rooms.add(r10);
		
		/*EZEN A PONTON BÁRMELYIK SZOBÁBA BÁRMILYEN KÖRNYEZETI VÁLTOZÓT BE LEHET RAKNI*/
		//r1.envFactors.add(new Gas(r1));
		//r1.envFactors.add(new Sticky(r1));
		
		// környezeti változók legenerálása a szobákba
		int numberOfEnvFactors = ProtoUtil.random.nextInt(3, 2);
		for (int i = 0; i < numberOfEnvFactors; i++) {
			Room gassyRoom = rooms.get(ProtoUtil.random.nextInt(10, 2));
			if (gassyRoom.getEnvironmentalFactors().size() == 0) {
				gassyRoom.addEnvironmentalFactor(new Gas(gassyRoom));
			}
		}
		
		// szomszédos szobák beállítása
		for (Room room : rooms) { // minden szobához végigiterálunk az összes szobán és vagy berakjuk a szomszédok közé vagy nem
            for (Room otherRoom : rooms) {
                if (room != otherRoom) { // saját magát nem rakjuk be semmiképpen
                    if (ProtoUtil.random.nextInt(2, 0) == 0) {
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
                        if (ProtoUtil.random.nextInt(2, 0) == 0) {
                            room.addNeighbour(otherRoom);
                            otherRoom.addNeighbour(room);
                        }
                    }
                }
            }
        }
		
		// tárgyak legenerálása (0, 1, ..., 6 db minden szobába)
		Room roomWithSlideRule = rooms.get(ProtoUtil.random.nextInt(10, 0));
		roomWithSlideRule.addItem(new SlideRule()); // logarléc betétele egy random szobába
		
		/*EZEN A PONTON BÁRMELYIK SZOBÁBA BÁRMILYEN TÁRGYAT BE LEHET RAKNI*/
		//r1.items.add(new WetCloth());
		//r1.items.add(new BatSkin());
		//r1.items.add(new Beer());
		//r5.items.add(new Beer());
		
		for(Room r : rooms){ // random mennyiségű tárgy legenerálása a szobákba
			int numberOfSpawnedItems = ProtoUtil.random.nextInt(7 - r.currentNumOfItems(), 4);
			for(int i = 0; i < numberOfSpawnedItems; i++) {
				r.spawnItem(itemPicker());
			}
		}
		
		// diákok legenerálása
		List<Room> roomsWithStudents = new ArrayList<Room>();
		
		Room roomOfFirstStudent = rooms.get(ProtoUtil.random.nextInt(10, 2));
		roomOfFirstStudent.addCharacter(red); // első diák betétele egy random szobába
		red.setRoom(roomOfFirstStudent);
		roomsWithStudents.add(roomOfFirstStudent);
		
		Room roomOfSecondStudent = null;
		while(roomOfSecondStudent == null) {
			int roomidx = ProtoUtil.random.nextInt(10, 0);
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
		Room roomOfTeacher = roomsWithoutStudents.get(ProtoUtil.random.nextInt(roomsWithoutStudents.size(), 2));
		roomOfTeacher.addCharacter(new Teacher(roomOfTeacher)); // tanár betétele egy diákmentes szobába egy random szabad helyre
		
		// takarító legenerálása
		Room roomOfCleaner = null;
		while(roomOfCleaner == null) {
			int roomidx = ProtoUtil.random.nextInt(10, 3);
			if(rooms.get(roomidx).getCapacity() != rooms.get(roomidx).currentNumOfPlayers()) {
				roomOfCleaner = rooms.get(roomidx);
			}
		}
		roomOfCleaner.addCharacter(new Cleaner(roomOfCleaner)); // takarító betétele egy random szabad helyre
    }

	@Override
	public void update() {
		// a random események végrehajtása
		if(ProtoUtil.random.nextInt(1000, -1)==0) {
			//mergeRoom(rooms.get(rand.nextInt(rooms.size())), rooms.get(rand.nextInt(rooms.size()))); // merge
		}
		if(ProtoUtil.random.nextInt(1000, -1)==0) {
			//splitRoom(rooms.get(rand.nextInt(rooms.size()))); // split
		}
		for(Room r : rooms){
			if(ProtoUtil.random.nextInt(1000, -1)==0) {
				r.spawnItem(itemPicker()); // item legenerálása
			}
			r.update();
		}
	}
}
