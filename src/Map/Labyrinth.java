package Map;

import Character.Student;
import Character.Teacher;
import EnvironmentalFactor.Gas;
import Items.*;
import ProtoUtil.ProtoUtil;

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth {

    /**
     * Szobát add a labirintushoz.
     * @param r A hozzáadandó szoba.
     */
	public void addRoom(Room r) {
		ProtoUtil.printLog("addRoom");
    }

    /**
     * Összeolvaszt egy szobát egy másikkal a labirintusban.
     * @param result Az összeolvadt szobák eredménye.
     * @param merging A beolvasztandó szoba.
     */
	public void mergeRoom(Room result, Room merging) {
		ProtoUtil.printLog("mergeRoom");
		result.merge(merging);
		this.removeRoom(merging);
    }

    /**
     * Egy szoba kettéosztása a labirintusban.
     * @param r A szétosztandó szoba.
     */
	public void splitRoom(Room r) {
		ProtoUtil.printLog("splitRoom");
		if(ProtoUtil.binaryQuestion("Elátkozott a kettéosztandó szoba?")) {
			// The room was cursed, so to new one will be too
			new CursedRoom("Szoba_R").create();
			new CursedRoom("Szoba_R").addEnvironmentalFactor(new Gas());
			new CursedRoom("Szoba_C").addNeighbour(new Room("Szoba_R"));
			r.removeNeighbour(new CursedRoom("Szoba_C"));
			
			if(ProtoUtil.binaryQuestion("Van gáz a kettéosztandó szobában?")) {
				// The room had Gas, so add gas to the new room
				new Gas().create();
				new CursedRoom("Szoba_R").addEnvironmentalFactor(new Gas());
			}
		}
		else {
			new Room("Szoba_R").create();
			new Room("Szoba_C").addNeighbour(new Room("Szoba_R"));
			r.removeNeighbour(new Room("Szoba_C"));
			
			if(ProtoUtil.binaryQuestion("Van gáz a kettéosztandó szobában?")) {
				// The room had Gas, so add gas to the new room
				new Gas().create();
				new Room("Szoba_R").addEnvironmentalFactor(new Gas());
			}
		}
    }
	
	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param r - A törlendő szoba.
	 */
	public void removeRoom(Room r) {
		ProtoUtil.printLog("removeRoom");
	}

    /**
     * A szobák generálása a labirintusban.
     */
	public void generateRooms() {
		ProtoUtil.printLog("generateRooms");
    }
}
