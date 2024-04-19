package ProtoUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Map.Labyrinth;

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
			break;
		case "create_item": // Imi
			break;
		case "create_factor": // Bence
			break;
		case "create_character": // Sam
			break;
		case "pickup_item": // Imi
			break;
		case "throw_item": // Imi
			break;
		case "use_item": // Zsombor
			break;
		case "enter_room": // Sam
			break;
		case "split": // Bence
			break;
		case "merge": // Bence
			break;
		case "kick": // Sam
			break;
		case "activate": // Bence
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
