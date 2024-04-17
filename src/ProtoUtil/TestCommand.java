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

	public void runCommand(Labyrinth l) {
		switch (command) {
		case "create_room":
			return;
		case "create_item":
			return;
		case "create_factor":
			return;
		case "create_character":
			return;
		case "pickup_item":
			return;
		case "throw_item":
			return;
		case "use_item":
			return;
		case "enter_room":
			return;
		case "split":
			return;
		case "merge":
			return;
		case "kick":
			return;
		case "activate":
			return;
		case "select_actor":
			return;
		case "quit":
			return;
		default:
			System.out.println("Invalid command: " + command);
			break;
		}
	}
}
