package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Items.*;
import ProtoUtil.ProtoUtil;
import Time.iTask;

// Labyrinth

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth implements iTask{

	private List<Room> Rooms;

	public Labyrinth(){
		Rooms=new ArrayList<Room>();
	}

	public List<Room> getRooms(){
		return Rooms;
	}

    /**
     * Szobát add a labirintushoz.
     * @param r A hozzáadandó szoba.
     */
	public void addRoom(Room r) {
		ProtoUtil.printLog("addRoom");
		Rooms.add(r);
    }

	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param r - A törlendő szoba.
	 */
	public void removeRoom(Room r) {
		ProtoUtil.printLog("removeRoom");
		Rooms.remove(r);
	}

    /**
     * Összeolvaszt egy szobát egy másikkal a labirintusban.
     * @param result Az összeolvadt szobák eredménye.
     * @param merging A beolvasztandó szoba.
     */
	public void mergeRoom(Room result, Room merging) {
		ProtoUtil.printLog("mergeRoom");
		int bigger;
		if(result.getCapacity()>merging.getCapacity()) bigger=result.getCapacity();
		else bigger=merging.getCapacity();
		if(result.getCharacters().size()+merging.getCharacters().size()<bigger){
			return;
		}else{
			result.setCapacity(bigger);
			result.merge(merging);
			this.removeRoom(merging);
		}
    }

    /**
     * Egy szoba kettéosztása a labirintusban.
     * @param r A szétosztandó szoba.
     */
	public void splitRoom(Room old) {
		ProtoUtil.printLog("splitRoom");
		Room n;
		if(old instanceof CursedRoom) {
			n=new CursedRoom(old.getCapacity());
		}
		else {
			n=new Room(old.getCapacity());
		}
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
		rand=random.nextInt(old.getItems().size());
		for(int i=0; i<rand; i++){
			n.addItem(old.getItems().get(0));
			old.removeItem(old.getItems().get(0));
		}
		rand=random.nextInt(old.getCharacters().size());
		for(int i=0; i<rand; i++){
			n.addCharacter(old.getCharacters().get(0));
			old.removeCharacter(old.getCharacters().get(0));
		}
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			n.addEnvironmentalFactor(old.getEnvironmentalFactors().get(i));
		}
		Rooms.add(Rooms.indexOf(old), n);
    }

	private Item itemPicker(){
		int rand=new Random().nextInt(11);
		switch(rand){
		case 1:
			return new AirFreshener();
		case 2:
			return new BatSkin();
		case 3:
			return new Beer();
		case 4:
			return new CabbageCamembert();
		case 5:
			return new FakeBatSkin();
		case 6:
			return new FakeMask();
		case 7:
			return new FakeSlideRule();
		case 8:
			return new Mask();
		case 9:
			return new SlideRule();
		case 10:
			return new Transistor();
		case 11:
			return new WetCloth();
		default:
			return null;
		}
	}

    /**
     * A szobák generálása a labirintusban.
     */
	public void generateRooms() {
		ProtoUtil.printLog("generateRooms");
    }

	@Override
	public void update() {
		//Ide kellenek majd a random események pl.: merge, split
		for(Room r : Rooms){
			//Ide kell majd a random item generálás: r.addItem(itemPicker());
			r.update();
		}
	}
}
