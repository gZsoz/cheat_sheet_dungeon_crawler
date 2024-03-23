package Map;

import SkeletonUtil.SkeletonUtil;

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth {

	/**
	 * Létrehozza a labirintust.
	 */
	public void create(){
		SkeletonUtil.printLog("Labyrinth.create()");
	}
	
    /**
     * Szobát add a labirintushoz.
     * @param r A hozzáadandó szoba.
     */
	public void addRoom(Room r) {
		SkeletonUtil.printLog("Labyrinth.addRoom("+ r.name +")");
    }

    /**
     * Összeolvaszt egy szobát egy másikkal a labirintusban.
     * @param result Az összeolvadt szobák eredménye.
     * @param merging A beolvasztandó szoba.
     */
	public void mergeRoom(Room result, Room merging) {
		SkeletonUtil.printLog("Labyrinth.mergeRoom(" + result.name + ", " + merging.name +")");
		SkeletonUtil.increaseIndent();
		result.merge(merging);
		merging.getNeighbours();
		result.addNeighbour(new Room("Szoba_T"));
		new Room("Szoba_T").addNeighbour(result);
		new Room("Szoba_T").removeNeighbour(merging);
		this.removeRoom(merging);
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Egy szoba kettéosztása a labirintusban.
     * @param r A szétosztandó szoba.
     */
	public void splitRoom(Room r) {
		SkeletonUtil.printLog("Labyrinth.splitRoom(" + r.name + ")");
		SkeletonUtil.increaseIndent();
		if(SkeletonUtil.binaryQuestion("Elátkozott a kettéosztandó szoba?")) {
			// The room was cursed, so to new one will be too
			new CursedRoom("Szoba_R").create();
			new CursedRoom("Szoba_R").addEnvironmentalFactor(new Gas());
			new CursedRoom("Szoba_C").addNeighbour(new Room("Szoba_R"));
			r.removeNeighbour(new CursedRoom("Szoba_C"));
			
			if(SkeletonUtil.binaryQuestion("Van gáz a kettéosztandó szobában?")) {
				// The room had Gas, so add gas to the new room
				new Gas().create();
				new CursedRoom("Szoba_R").addEnvironmentalFactor(new Gas());
				new Gas().setLocation(new CursedRoom("Szoba_R"));
			}
		}
		else {
			new Room("Szoba_R").create();
			new Room("Szoba_C").addNeighbour(new Room("Szoba_R"));
			r.removeNeighbour(new Room("Szoba_C"));
			
			if(SkeletonUtil.binaryQuestion("Van gáz a kettéosztandó szobában?")) {
				// The room had Gas, so add gas to the new room
				new Gas().create();
				new Room("Szoba_R").addEnvironmentalFactor(new Gas());
				new Gas().setLocation(new Room("Szoba_R"));
			}
		}
    	SkeletonUtil.decreaseIndent();
    }
	
	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param r - A törlendő szoba.
	 */
	public void removeRoom(Room r) {
		SkeletonUtil.printLog("Labyrinth.removeRoom(" + r.name + ")");
	}

    /**
     * A szobák generálása a labirintusban.
     */
	public void generateRooms() {
		SkeletonUtil.printLog("Labyrinth.generateRooms()");
		SkeletonUtil.increaseIndent();
		// Create each room
		new Room("Szoba_C").create();
		new Room("Szoba_G").create();
		new Room("Szoba_E").create();
		new Room("Szoba_F").create();
		new Room("Szoba_T").create();
		
		// Add each to labyrinth
		this.addRoom(new Room("Szoba_C"));
		this.addRoom(new Room("Szoba_G"));
		this.addRoom(new Room("Szoba_E"));
		this.addRoom(new Room("Szoba_F"));
		this.addRoom(new Room("Szoba_T"));
		
		// Add every neighbour connection between rooms
		new Room("Szoba_C").addNeighbour(new Room("Szoba_G"));
		new Room("Szoba_C").addNeighbour(new Room("Szoba_E"));
		new Room("Szoba_C").addNeighbour(new Room("Szoba_F"));
		new Room("Szoba_C").addNeighbour(new Room("Szoba_T"));
		
		new Room("Szoba_G").addNeighbour(new Room("Szoba_C"));
		new Room("Szoba_G").addNeighbour(new Room("Szoba_E"));
		new Room("Szoba_G").addNeighbour(new Room("Szoba_F"));
		new Room("Szoba_G").addNeighbour(new Room("Szoba_T"));
		
		new Room("Szoba_E").addNeighbour(new Room("Szoba_C"));
		new Room("Szoba_E").addNeighbour(new Room("Szoba_G"));
		new Room("Szoba_E").addNeighbour(new Room("Szoba_F"));
		new Room("Szoba_E").addNeighbour(new Room("Szoba_T"));
		
		new Room("Szoba_F").addNeighbour(new Room("Szoba_C"));
		new Room("Szoba_F").addNeighbour(new Room("Szoba_G"));
		new Room("Szoba_F").addNeighbour(new Room("Szoba_E"));
		new Room("Szoba_F").addNeighbour(new Room("Szoba_T"));
		
		new Room("Szoba_T").addNeighbour(new Room("Szoba_C"));
		new Room("Szoba_T").addNeighbour(new Room("Szoba_G"));
		new Room("Szoba_T").addNeighbour(new Room("Szoba_E"));
		new Room("Szoba_T").addNeighbour(new Room("Szoba_F"));
		
		// Create every character
		new Student("Bela").create();
		new Student("Aladar").create();
		new Teacher("Tasnadi").create();
		new Teacher("Tivadar").create();
		
		// They enter their starting rooms
		new Student("Bela").enterRoom("Szoba_F");
		new Student("Aladar").enterRoom("Szoba_E");
		new Teacher("Tasnadi").enterRoom("Szoba_E");
		new Teacher("Tivadar").enterRoom("Szoba_T");
		
		// Each room notes who entered the room and is currently there
		new Room("Szoba_F").addCharacter(new Student("Bela"));
		new Room("Szoba_E").addCharacter(new Student("Aladar"));
		new Room("Szoba_E").addCharacter(new Teacher("Tasnadi"));
		new Room("Szoba_T").addCharacter(new Student("Tivadar"));
		
		// Load Room Szoba_F with all its items
		// Create items
		new SlideRule().create();
		new Beer().create();
		new WetCloth().create();
		new Transistor("transistor3").create();
		new Transistor("transistor4").create();
		
		// Spawn the items in
		new Room("Szoba_F").spawnItem(new SlideRule());
		new Room("Szoba_F").spawnItem(new Beer());
		new Room("Szoba_F").spawnItem(new WetCloth());
		new Room("Szoba_F").spawnItem(new Transistor("transistor3"));
		new Room("Szoba_F").spawnItem(new Transistor("transistor4"));
		
		// The room notes the items added to it
		new Room("Szoba_F").addItem(new SlideRule());
		new Room("Szoba_F").addItem(new Beer());
		new Room("Szoba_F").addItem(new WetCloth());
		new Room("Szoba_F").addItem(new Transistor("transistor3"));
		new Room("Szoba_F").addItem(new Transistor("transistor4"));
		
		// Make Gas inside Room Szoba_G 
		new Gas().create();
		new Room("Szoba_G").addEnvironmentalFactor(new Gas());
		
		// Add starting items to characters
		// Create items
		new Mask("mask1").create();
		new BatSkin().create();
		new Transistor("transistor1").create();
		new Transistor("transistor2").create();
		new WetCloth().create();
		new Camambert().create();
		new Mask("mask2").create();
		
		// Add to players their own
		new Student("Bela").pickupItem(new Camambert());
		new Student("Aladar").pickupItem(new Mask("mask1"));
		new Student("Aladar").pickupItem(new BatSkin());
		new Student("Aladar").pickupItem(new Transistor("transistor1"));
		new Student("Aladar").pickupItem(new Transistor("transistor2"));
		new Student("Aladar").pickupItem(new WetCloth());
		new Teacher("Tasnadi").pickupItem(new Mask("mask2"));
		
    	SkeletonUtil.decreaseIndent();
    }
}