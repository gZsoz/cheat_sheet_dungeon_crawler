package ProtoUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import EnvironmentalFactor.Gas;
import EnvironmentalFactor.Sticky;
import Items.AirFreshener;
import Items.BatSkin;
import Items.Beer;
import Items.CabbageCamembert;
import Items.FakeBatSkin;
import Items.FakeMask;
import Items.FakeSlideRule;
import Items.Mask;
import Items.SlideRule;
import Items.Transistor;
import Items.WetCloth;
import Map.Labyrinth;
import Map.Room;
import Map.CursedRoom;
import Character.Student;
import Character.Character;

public class TestCommand {
	String command;
	String[] parameters;

	public TestCommand(String command, String[] parameters) {
		this.command = command;
		this.parameters = parameters;
	}

	public void readTestCommand(InputStream in) {
		try (Scanner scanner = new Scanner(in)) {
			if (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] temp = (line.split(" "));
				parameters = Arrays.copyOfRange(temp, 1, temp.length);
				command = temp[0];
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// returns false if quit
	public boolean runCommand(Labyrinth l, ArrayList<Character> characters, Character actor) {
		switch (command) {
		case "create_room":	// Balázs
			Room room;
			int capacity=Integer.parseInt(parameters[0]);
			int neighbourcount=parameters.length-2;
			if(l.getRooms().isEmpty()) {
				if(parameters[1]=="0")
					l.addRoom(new Room(capacity));
				else
					l.addRoom(new CursedRoom(capacity));
				return true;
			}
			List<Room> neighbours = new ArrayList<Room>();
			if(parameters[1]=="0")
				room=new Room(neighbours, capacity);
			else
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
		case "create_factor": // Bence
			String type = parameters[0];
			int roomIdx = Integer.parseInt(parameters[1]);
			if(type == "gas"){
				Gas gas = new Gas(l.getRooms().get(roomIdx));
			}
			if(type == "sticky"){
				Sticky sticky = new Sticky(l.getRooms().get(roomIdx));
			}
			break;
		case "create_character": // Sam
			break;
		case "pickup_item":
			actor.putdownItem(actor.getInventory().get(Integer.parseInt(parameters[0])));
			break;
		case "throw_item":
			actor.pickupItem(actor.getRoom().getItems().get(Integer.parseInt(parameters[0])));
			break;
		case "use_item": // Zsombor
			int useIdx = Integer.parseInt(parameters[0]);
			((Student)actor).activate(actor.getInventory().get(useIdx));
			break;
		case "enter_room": // Sam
			break;
		case "split": // Bence
			int splitIdx = Integer.parseInt(parameters[0]);
			l.splitRoom(l.getRooms().get(splitIdx));
			break;
		case "merge": // Bence
			int dstIdx = Integer.parseInt(parameters[0]);
			int srcIdx = Integer.parseInt(parameters[1]);
			l.mergeRoom(l.getRooms().get(dstIdx), l.getRooms().get(srcIdx));
			break;
		case "kick": // Sam
			break;
		case "select_actor": // Balázs
			actor=characters.get(Integer.parseInt(parameters[0]));
			break;
		case "quit":
			return false;
		default:
			System.out.println("Invalid command: " + command);
			break;
		}
		return true;
	}
}
