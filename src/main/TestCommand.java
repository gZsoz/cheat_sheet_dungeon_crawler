package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.EnvironmentalFactors.Gas;
import Model.EnvironmentalFactors.Sticky;
import Model.Items.NumberOfUsesItems.AirFreshener;
import Model.Items.NumberOfUsesItems.BatSkin;
import Model.Items.DecayingItems.Beer;
import Model.Items.NumberOfUsesItems.CabbageCamembert;
import Model.Items.NumberOfUsesItems.FakeBatSkin;
import Model.Items.DecayingItems.FakeMask;
import Model.Items.NumberOfUsesItems.FakeSlideRule;
import Model.Items.DecayingItems.Mask;
import Model.Items.NumberOfUsesItems.SlideRule;
import Model.Items.SpecialItems.Transistor;
import Model.Items.DecayingItems.WetCloth;
import Model.Map.Labyrinth;
import Model.Map.Room;
import Model.Map.CursedRoom;
import Model.Characters.Student;
import Model.Characters.Teacher;
import Model.Characters.Character;
import Model.Characters.Cleaner;

/**
 * A tesztparancsok kezeléséért felelős osztály. A tesztparancsoknak megfelelő műveleteket hajt végre a labirintuson, karaktereken és tárgyakon.
 */
public class TestCommand {
	String command;
	String[] parameters;
	
	/**
	 * Konstruktor a tesztparancsok inicializálásához.
	 */
	public TestCommand() {}
	
	/**
	 * Konstruktor a tesztparancsok inicializálásához.
	 * 
	 * @param command a parancs neve
	 * @param parameters a parancs paraméterei
	 */
	public TestCommand(String command, String[] parameters) {
		this.command = command;
		this.parameters = parameters;
	}
	
	/**
	 * Az összes lehetséges parancsot és funkcióját kiírja a konzolra.
	 */
	private void help() {
		System.out.println("Create_Room <capacity> <type> [<neighbors>]\n"
				+ "    Új szobát vagy elátkozott szobát hoz létre, opcionálisan megadva a szomszédos szobákat.\n"
				+ "\n"
				+ "Create_Item <type> <room_number>\n"
				+ "    Létrehoz és elhelyez egy tárgyat adott szobában.\n"
				+ "\n"
				+ "Create_Factor <type> <room_number>\n"
				+ "    Környezeti anomáliákat (gázt vagy ragacsot) hoz létre adott szobában.\n"
				+ "\n"
				+ "Create_Character <type> <room_number>\n"
				+ "    Létrehoz egy tanárt vagy egy hallgatót, majd elhelyezi egy adott szobában.\n"
				+ "\n"
				+ "Pickup_item <item_number>\n"
				+ "    Felvehető tárgyak közül választhat egyet, hogy azt az inventoryban tárolja.\n"
				+ "\n"
				+ "Throw_item <item_number>\n"
				+ "    Elhajíthat egy tárgyat az inventoryból.\n"
				+ "\n"
				+ "Use_item <item_number>\n"
				+ "    Használhat egy tárgyat az inventoryból.\n"
				+ "\n"
				+ "split <room_number> [<number_of_neighbours_to_move> <number_of_items_to_move> <number_of_characters_to_move>]\n"
				+ "    Kettéoszt egy már létező szobát az új szoba szon szoba mögé kerül a labirintus listájában, melyre meghívjuk a split commandot. Opcionális paramétereiben meg lehet adni, hogy hány elemet kapjon az új szoba az adott játék elemekből.\n"
				+ "\n"
				+ "merge <room_to_merge_into> <room_to_merge>"
				+ "    Összeolvasztja a paraméterként kapott szobákakat, a szobákat számokkal lehet megadni.\n"
				+ "\n"
				+ "Enter_Room <room_number>\n"
				+ "    Belépés egy adott szobába a szomszédos szobák közül választva.\n"
				+ "\n"
				+ "update\n"
				+ "    Futtat 3 update() ciklust a játékon.\n"
				+ "\n"
				+ "quit\n"
				+ "    A parancs, melynek segítségével kiléphetsz konzolról teszteléskor."
				);
	}
	
	/**
	 * Beolvassa a tesztparancsot a megadott sorból.
	 * 
	 * @param line a beolvasandó sor
	 */
	public void readTestCommand(String line) {
		String[] temp = (line.split(" "));
		parameters = Arrays.copyOfRange(temp, 1, temp.length);
		command = temp[0];
	}
	
	/**
	 * Végrehajtja a megadott tesztparancsot a labirintuson, karaktereken és tárgyakon.
	 * 
	 * @param l a labirintus, amelyen a műveletek végrehajtódnak
	 * @param characters a karakterek listája, amelyen a műveletek végrehajtódnak
	 * @param actorArr a cselekvő karakter tömbje
	 * @return visszatérési érték igaz, ha a művelet sikeresen lefutott, különben hamis
	 */
	public boolean runCommand(Labyrinth l, ArrayList<Character> characters, ArrayList<Character> actorArr) {
		Character actor=actorArr.get(0);
		switch (command) {
		case "create_room":
			Room room=null;
			int capacity=Integer.parseInt(parameters[0]);
			int neighbourcount=parameters.length-2;
			if(l.getRooms().isEmpty()) {
				if(parameters[1].equals("normal")) {
					l.addRoom(new Room(capacity));
				}
				else if(parameters[1].equals("cursed"))
					l.addRoom(new CursedRoom(capacity));
				return true;
			}
			List<Room> neighbours = new ArrayList<Room>();
			if(parameters[1].equals("normal"))
				room=new Room(neighbours, capacity);
			else if(parameters[1].equals("cursed"))
				room=new CursedRoom(neighbours, capacity);
			if(neighbourcount == 0)
				neighbours.add(l.getRooms().get(l.getRooms().size()-1));
			else
				for(int i=0;i<neighbourcount;i++)
					neighbours.add(l.getRooms().get(Integer.parseInt(parameters[i+2])));
			for(Room r : neighbours)
				r.addNeighbour(room);
			l.addRoom(room);
			return true;
		case "create_item":
			switch(parameters[0]){
			case "airfreshener":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new AirFreshener());
				break;
			case "batskin":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new BatSkin());
				break;
			case "beer":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new Beer());
				break;
			case "cabbagecamembert":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new CabbageCamembert());
				break;
			case "fakebatskin":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new FakeBatSkin());
				break;
			case "fakemask":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new FakeMask());
				break;
			case "fakesliderule":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new FakeSlideRule());
				break;
			case "mask":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new Mask());
				break;
			case "sliderule":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new SlideRule());
				break;
			case "transistor":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new Transistor());
				break;
			case "wetcloth":
				l.getRooms().get(Integer.parseInt(parameters[1])).addItem(new WetCloth());
				break;
			default:
				System.out.println("Invalid command: " + command + " " + parameters[0]);
				break;
			}	
			break;
		case "create_factor":
			String type = parameters[0];
			int roomIdx = Integer.parseInt(parameters[1]);
			if(type.equals("gas")){
				l.getRooms().get(roomIdx).addEnvironmentalFactor(new Gas(l.getRooms().get(roomIdx)));
			}
			if(type.equals("sticky")){
				l.getRooms().get(roomIdx).addEnvironmentalFactor(new Sticky(l.getRooms().get(roomIdx)));
			}
			break;
		case "create_character":
			Character temp=null;
			switch(parameters[0]) {
				case "student":
					temp=new Student(l.getRooms().get(Integer.parseInt(parameters[1])));
					break;
				case "teacher":
					temp=new Teacher(l.getRooms().get(Integer.parseInt(parameters[1])));
					break;
				case "cleaner":
					temp=new Cleaner(l.getRooms().get(Integer.parseInt(parameters[1])));
					break;
				default:
					System.out.println("Invalid command: " + command + " " + parameters[0]);
					break;
			}
			characters.add(temp);
			temp.getRoom().addCharacter(temp);
			break;
		case "pickup_item":
			actor.pickupItem(actor.getRoom().getItems().get(Integer.parseInt(parameters[0])));
			break;
		case "throw_item":
			actor.putdownItem(actor.getInventory().get(Integer.parseInt(parameters[0])));
			break;
		case "use_item":
			int useIdx = Integer.parseInt(parameters[0]);
			((Student)actor).activate(actor.getInventory().get(useIdx));
			break;
		case "enter_room":
			actor.enterRoom(l.getRooms().get(Integer.parseInt(parameters[0])));
			break;
		case "split":
			int splitIdx = Integer.parseInt(parameters[0]);
			if(parameters.length>1)
				l.splitRoomTest(l.getRooms().get(splitIdx),Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]),Integer.parseInt(parameters[3]));
			else
				l.splitRoom(l.getRooms().get(splitIdx));
			break;
		case "merge":
			int dstIdx = Integer.parseInt(parameters[0]);
			int srcIdx = Integer.parseInt(parameters[1]);
			l.mergeRoom(l.getRooms().get(dstIdx), l.getRooms().get(srcIdx));
			break;
		case "select_actor":
			actorArr.set(0, characters.get(Integer.parseInt(parameters[0])));
			break;
		case "update":
			return true;
		case "help":
			help();
			return true;
		case "quit":
			return false;
		default:
			System.out.println("Invalid command: " + command);
			break;
		}
		return true;
	}
}
