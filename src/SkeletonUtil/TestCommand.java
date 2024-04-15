package SkeletonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCommand {
    String command;
    String parameter;
    int quantity;
    String atLeast;

    public TestCommand(String command, String parameter, int quantity, String atLeast) {
	    this.command = command;
	    this.parameter = parameter;
	    this.quantity = quantity;
	    this.atLeast = atLeast;
    }

	public static List<TestCommand> readTestCommands(InputStream in) {
	    List<TestCommand> commands = new ArrayList<>();
		try (Scanner scanner = new Scanner(in)) {
			while(scanner.hasNext()) {
			    String command ="";
			    String parameter="";
			    Integer quantity=-1;
			    String atLeast="";
			    scanner.useDelimiter(" ");
			    if (scanner.hasNext()) {
			        command=scanner.next();
			    }
			    scanner.useDelimiter("\"");
			    if (scanner.hasNext()) {
			        scanner.next();
			    }
			    if (scanner.hasNext()) {
			        parameter=scanner.next();
			    }
			    String[] optional=null;
			    if (scanner.hasNextLine()) {
			       optional =scanner.nextLine().substring(1).trim().split(" ");
			    }
			    if (optional.length>=1 && !optional[0].isEmpty()) {
			        quantity=Integer.parseInt(optional[0]);
			    }
			    if (optional.length>=2) {
			        atLeast=optional[1];
			    }
			    commands.add(new TestCommand(command, parameter, quantity, atLeast));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return commands;
	}
	
	private int countMatches(Pattern pattern, String string)
	{
	    Matcher matcher = pattern.matcher(string);

	    int count = 0;
	    int pos = 0;
	    while (matcher.find(pos))
	    {
	        count++;
	        pos = matcher.start() + 1;
	    }

	    return count;
	}
	
	public boolean runCommand(String text) {
		switch (command) {
        case "contains":
        	if(quantity==-1)
        		return text.contains(parameter);
        	else {
        		Pattern pattern = Pattern.compile(parameter, Pattern.LITERAL);
        		int count = countMatches(pattern, text);
        		System.out.println("count: "+count);
        		if(atLeast.equals("m"))
        			return count<=quantity;
        		else if(atLeast.equals("l"))
        			return count>=quantity;
        		else
        			return count==quantity;
        	}
        case "notcontains":
        	return !text.contains(parameter);
        default:
            System.out.println("Invalid command: " + command);
            break;	
		}
		return false;
	}
}

