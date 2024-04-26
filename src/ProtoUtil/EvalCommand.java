package ProtoUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A kiértékelési parancsok kezeléséért felelős osztály. Ezen parancsok a szöveg kiértékelésére szolgálnak.
 */
public class EvalCommand {
    String command;
    String parameter;
    int quantity;
    String atLeast;

    /**
     * Konstruktor egy kiértékelési parancs létrehozásához.
     * 
     * @param command A parancs neve.
     * @param parameter A kiértékelési paraméter.
     * @param quantity A mennyiség.
     * @param atLeast A mennyiség minimuma.
     */
    public EvalCommand(String command, String parameter, int quantity, String atLeast) {
        this.command = command;
        this.parameter = parameter;
        this.quantity = quantity;
        this.atLeast = atLeast;
    }

    /**
     * Olvassa be a kiértékelési parancsokat a megadott bemenetről.
     * 
     * @param in A bemeneti adatfolyam.
     * @return A kiértékelési parancsok listája.
     */
    public static List<EvalCommand> readEvalCommands(InputStream in) {
        List<EvalCommand> commands = new ArrayList<>();
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
                String[] optional= new String[0];
                if (scanner.hasNextLine()) {
                   optional =scanner.nextLine().substring(1).trim().split(" ");
                }
                if (optional.length>=1 && !optional[0].isEmpty()) {
                    quantity=Integer.parseInt(optional[0]);
                }
                if (optional.length>=2) {
                    atLeast=optional[1];
                }
                commands.add(new EvalCommand(command, parameter, quantity, atLeast));
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
    
    /**
     * Végrehajtja a kiértékelési parancsot a megadott szövegen.
     * 
     * @param text A szöveg, amelyen a kiértékelés történik.
     * @return Visszatérési érték igaz, ha a feltétel teljesül, különben hamis.
     */
    public boolean runCommand(String text) {
        switch (command) {
        case "contains":
            if(quantity==-1)
                return text.contains(parameter);
            else {
                Pattern pattern = Pattern.compile(parameter, Pattern.LITERAL);
                int count = countMatches(pattern, text);
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