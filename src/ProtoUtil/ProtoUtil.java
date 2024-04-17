package ProtoUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Character.*;
import EnvironmentalFactor.*;
import Items.*;
import Map.*;

/**
 * A SkeletonUtil osztály tartalmazza az alapvető segédmetódusokat és tesztfüggvényeket a programhoz.
 */
public class ProtoUtil {
	private static final String dirName = "test/";
    private static String testname = ""; // A naplófájl neve
    
    public static void printLog(String str) {
        String message=str;
        System.out.println(message); // Üzenet kiírása a konzolra
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(dirName+"output/"+testname+"_output.txt", true);
            fos.write((message+'\n').getBytes()); // Üzenet írása a naplófájlba
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // csak azért vannak itt mert nem akartam belenyúlni az osztályokba
    public static void increaseIndent(){}
    public static void decreaseIndent(){}
    /**
     * Bináris kérdést tesz fel a felhasználónak.
     * @param question A kérdés
     * @return A felhasználó válasza
     */
    public static boolean binaryQuestion(String question) {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println('\n'+question+"\n1) Igen\n2) Nem");
                choice=sc.nextInt();
                sc.nextLine();	// a \n beolvasása
                if(choice<1 || choice>2)
                    System.out.println("Hibás input!\n Válasszon a fenti opciók közül!");
            } catch(InputMismatchException e) {
                System.out.println("Hibás input!\n Válasszon a fenti opciók közül, csak egy számot írjon be!");
                sc.nextLine();
                choice=0;
            }
        } while(choice<1 || choice>2);
        return (choice==1);
    }

    /**
     * Általános kérdést tesz fel a felhasználónak.
     * @param question A kérdés
     * @param opt A válaszlehetőségek tömbje
     * @return A felhasználó válasza
     */
    public static int question(String question, String[] opt) {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println('\n'+question);
                for(int i=0;i<opt.length;i++)
                    System.out.println((i+1)+") "+opt[i]);
                choice=sc.nextInt();
                sc.nextLine();	// a \n beolvasása
                if(choice<1 || choice>opt.length)
                    System.out.println("Hibás input!\n Válasszon a fenti opciók közül!");
            } catch(InputMismatchException e) {
                System.out.println("Hibás input!\n Válasszon a fenti opciók közül, csak egy számot írjon be!");
                sc.nextLine();
                choice=0;
            }
        } while(choice<1 || choice>opt.length);
        return choice;
    }
    
    public static void runTest(InputStream input, OutputStream output) {
    	Scanner sc = new Scanner(input);
    	PrintStream out = new PrintStream(output);
    	String line;
    	while (sc.hasNextLine()) {
    	    line = sc.nextLine();
    	    if (line.equals("quit"))
    	        break; // this will exit the loop
    	    out.println(line);
    	    // !!!!!!! Ide jön majd a parancs felismerése, futtatása !!!!!!!
    	}
	    if(!input.equals(System.in))
	    	sc.close();
	    if(!output.equals(System.out))
	    	out.close();
    }
    
	private static boolean evaluateTest(InputStream output, InputStream expected) {
		Scanner scanner = new Scanner( output );
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		List<EvalCommand> commands = EvalCommand.readEvalCommands(expected);
		for (EvalCommand command : commands) {
			if(!command.runCommand(text))
				return false;
		}
		return true;
	}
	
	private static ArrayList<String> getTestNames() {
		File folder = new File(dirName+"input");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> names=new ArrayList<String>();
		if(listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String fullname=listOfFiles[i].getName();
					names.add(fullname.substring(0,fullname.lastIndexOf('_')));
				}
			}
		}
		return names;
	}
	
	private static void runTestFromName(String name) {
		try {
			File output = new File(dirName+"output/"+name+"_output.txt");
	        File input = new File(dirName+"input/"+name+"_input.txt");
	        PrintWriter writer = new PrintWriter(output);
	        writer.close();
	        InputStream inStream = new FileInputStream(input);
	        OutputStream outStream = new FileOutputStream(output);
	    	runTest(inStream, outStream);
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	private static boolean evaluateTestFromName(String name) {
		boolean result=false;
		try {
			InputStream output = new FileInputStream(new File(dirName+"output/"+name+"_output.txt"));		
			InputStream expected = new FileInputStream(new File(dirName+"expected/"+name+"_expected.txt"));
			result=evaluateTest(output, expected);
			output.close();
			expected.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
    /**
     * A program belépési pontja.
     * @param args A program argumentumai
     * @throws FileNotFoundException Ha a naplófájl nem található
     */
    public static void main(String[] args) throws FileNotFoundException {
        boolean quit=false; // Kilépési feltétel
        do {
            String[] opt={
                    "Saját teszt futtatása konzolról",
                    "Teszt futtatása fájlból",
                    "Összes teszt futtatása",
                    "Kilépés"
            };
            int ans=question("Mit szeretnél tesztelni?", opt); // Felhasználói választás bekérése
            switch(ans) {
                case 1:
                    runTest(System.in,System.out);
                    break;
                case 2:
                	ArrayList<String> tests=getTestNames();
                	tests.add(0, "Kilépés teszt futtatása nélkül");
                	String[] opt2=new String[tests.size()];
                	int selectedTest=question("Melyik tesztet szeretnéd futtatni?", tests.toArray(opt2))-1;
                	if(selectedTest==0)
                		break;
                	runTestFromName(tests.get(selectedTest));
                	if(evaluateTestFromName(tests.get(selectedTest)))
            			System.out.printf("%-30sSuccess\n",tests.get(selectedTest));
            		else
            			System.out.printf("%-30sFailure\n",tests.get(selectedTest));
                    break;
                case 3:
                	ArrayList<String> alltests=getTestNames();
                	boolean success=true;
                	for(String test : alltests) {
                		runTestFromName(test);
                		if(evaluateTestFromName(test))
                			System.out.printf("%-30sSuccess\n",test);
                		else {
                			System.out.printf("%-30sFailure\n",test);
                			success=false;
                		}
                	}
                	if(success)
            			System.out.println("\nMinden teszt sikeresen lefutott!");
            		else 
            			System.out.println("\nEgy vagy több teszt hibás eredményt adott!");
                	break;
                case 4:
                    quit=true; // Kilépés
                    break;
            }
            System.out.println(); // Üres sor a jobb olvashatóság érdekében
        } while(!quit);
    }
}

