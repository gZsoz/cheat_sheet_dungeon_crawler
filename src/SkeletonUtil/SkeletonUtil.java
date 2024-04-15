package SkeletonUtil;

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
public class SkeletonUtil {
    private static String filename = "log.txt"; // A naplófájl neve
    public static void printLog(String str) {
        String message=str;
        System.out.println(message); // Üzenet kiírása a konzolra
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filename, true);
            fos.write((message+'\n').getBytes()); // Üzenet írása a naplófájlba
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    	out.println("almafa");
    	String line;
    	while (sc.hasNextLine()) {
    	    line = sc.nextLine();
    	    if (line.equals("quit"))
    	        break; // this will exit the loop
    	    System.out.println(line);
    	    out.println(line);
    	}
	    if(!input.equals(System.in))
	    	sc.close();
	    if(!output.equals(System.out))
	    	sc.close();
    }
    
	private static boolean evaluateTest(InputStream output, InputStream expected) {
		Scanner scanner = new Scanner( output );
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		System.out.println("Végső:\n"+text);
		List<TestCommand> commands = TestCommand.readTestCommands(expected);
		for (TestCommand command : commands) {
			if(!command.runCommand(text))
				return false;
		}
		return true;
	}
    
    /**
     * A program belépési pontja.
     * @param args A program argumentumai
     * @throws FileNotFoundException Ha a naplófájl nem található
     */
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File(filename); // Naplófájl inicializálása
        File myInpObj = new File("testinput.txt");
        boolean quit=false; // Kilépési feltétel
        do {
            String[] opt={
                    "Saját teszt futtatása konzolról",
                    "Teszt futtatása fájlból",
                    "Összes teszt futtatása",
                    "Kilépés"
            };
            int ans=question("Mit szeretnél tesztelni?", opt); // Felhasználói választás bekérése
            PrintWriter writer = new PrintWriter(myObj);
            writer.close(); // Naplófájl tartalmának törlése
            switch(ans) {
                case 1:
                    runTest(System.in,System.out);
                    break;
                case 2:
                	InputStream targetStream = new FileInputStream(myInpObj);
                    OutputStream outStream = new FileOutputStream(myObj);
                	runTest(targetStream, outStream);
					try {
						targetStream.close();
						outStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					InputStream output = new FileInputStream(myObj);
					InputStream expected = new FileInputStream(new File("testexpected.txt"));
					System.out.println("A teszt eredménye: "+evaluateTest(output, expected));	
                    break;
                case 3:
                	break;
                case 4:
                    quit=true; // Kilépés
                    break;
            }
            System.out.println(); // Üres sor a jobb olvashatóság érdekében
            Scanner filesc = new Scanner(myObj); // Naplófájl beolvasása
            while(filesc.hasNextLine())
                System.out.println(filesc.nextLine()); // Naplófájl tartalmának kiírása
            filesc.close();
        } while(!quit);
    }


}

