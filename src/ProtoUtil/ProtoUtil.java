package ProtoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import Map.CursedRoom;
import Map.Labyrinth;
import View.Controller.PlayerController;
import View.Utils.GameFrame;
import View.Utils.SelectionColor;
import View.ViewMap.ViewLabyrinth;
import Character.Character;
import Character.Student;
import EnvironmentalFactor.Sticky;
import Items.AirFreshener;
import Items.BatSkin;
import Items.CabbageCamembert;
import Items.DecayingItems;
import Items.FakeBatSkin;
import Items.Transistor;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A ProtoUtil osztály tartalmazza az alapvető segédmetódusokat és tesztfüggvényeket a programhoz.
 */
public class ProtoUtil {
	private static final String RESET = "\033[0m";  // Text Reset
    private static final String RED = "\033[0;31m";     // RED
    private static final String GREEN = "\033[0;32m";   // GREEN
	public static int aa=0;
	private static final String dirName = "test/";
    private static PrintStream logOutput = null;
    
    public static MyRandom random;
    
    /**
     * A beállított OutputStreambe írja az a tesztprogram logokat.
     * @param question A kérdés
     */
    public static void printLog(String str) {
    	if(logOutput!=null)
    		logOutput.println(str);
    }
    
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
                System.out.println(question);
                for(int i=0;i<opt.length;i++)
                    System.out.println((i+1)+") "+opt[i]);
                choice=sc.nextInt();
                sc.nextLine();	// a \n beolvasása
                if(choice<1 || choice>opt.length)
                    System.out.println("Hibás input!\nVálasszon a fenti opciók közül!\n");
            } catch(InputMismatchException e) {
                System.out.println("Hibás input!\nVálasszon a fenti opciók közül, csak egy számot írjon be!\n");
                sc.nextLine();
                choice=0;
            }
        } while(choice<1 || choice>opt.length);
        return choice;
    }

    /**
     * Teszt futtatása bemenetről és kimenetre.
     * @param input A bemeneti adatfolyam
     * @param output A kimeneti adatfolyam
     */
    public static void runTest(InputStream input, OutputStream output) {
    	Scanner sc = new Scanner(input);
    	logOutput = new PrintStream(output);
    	String line;
    	Labyrinth l=new Labyrinth();
    	ArrayList<Character> characters = new ArrayList<Character>();
    	ArrayList<Character> actor = new ArrayList<Character>(1);
    	actor.add(null);
    	while (sc.hasNextLine()) {
    	    line = sc.nextLine();
    	    TestCommand tc= new TestCommand();
    	    tc.readTestCommand(line);
    	    if (!tc.runCommand(l, characters, actor))
    	        break; // kilépés a ciklusból
    	    l.update(); l.update(); l.update();
    	}
	    if(!input.equals(System.in))
	    	sc.close();
	    if(!output.equals(System.out))
	    	logOutput.close();
    }
    
    /**
     * Teszt kiértékelése az elvárt kimenettel.
     * @param output A teszt kimenete
     * @param expected Az elvárt kimenet
     * @return Igaz, ha a teszt sikeresen kiértékelhető, különben hamis
     */
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
	
    /**
     * Tesztfájlok neveinek lekérdezése.
     * @return A tesztfájlok neveinek listája
     */
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
	
    /**
     * Teszt futtatása név alapján.
     * @param name A teszt neve
     */
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
			// Hibaüzenet kezelése
			e.printStackTrace();
		};
	}
	
    /**
     * Teszt kiértékelése név alapján.
     * @param name A teszt neve
     * @return Igaz, ha a teszt sikeresen kiértékelhető, különben hamis
     */
	private static boolean evaluateTestFromName(String name) {
		boolean result=false;
		try {
			InputStream output = new FileInputStream(new File(dirName+"output/"+name+"_output.txt"));		
			InputStream expected = new FileInputStream(new File(dirName+"expected/"+name+"_expected.txt"));
			result=evaluateTest(output, expected);
			output.close();
			expected.close();
		} catch (IOException e) {
			// Hibaüzenet kezelése
			e.printStackTrace();
		}
		return result;
	}
	
	private static void test(String[] args) {
    	Character.restTime=10;
    	Character.stunTime=4;
    	Sticky.defaultRemainingEntries=2;
    	AirFreshener.defaultRemainingUses=1;
    	BatSkin.defaultRemainingUses=3;
    	DecayingItems.defaultDuration=6;
    	CabbageCamembert.defaultRemainingUses=1;
    	CursedRoom.defaultCloseDuration=5;
    	if(args.length==2 && args[1].equals("all")) {
    		ArrayList<String> alltests=getTestNames();
        	boolean success=true;
        	for(String test : alltests) {
        		runTestFromName(test);
        		if(evaluateTestFromName(test))
        			System.out.printf(GREEN+"%-40sSuccess\n"+RESET,test);
        		else {
        			System.out.printf(RED+"%-40sFailure\n"+RESET,test);
        			success=false;
        		}
        	}
        	if(success)
    			System.out.println(GREEN+"\nMinden teszt sikeresen lefutott!"+RESET);
    		else 
    			System.out.println(RED+"\nEgy vagy több teszt hibás eredményt adott!"+RESET);
        	return;
    	}
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
					System.out.println("Írd be a teszthez szükséges parancsokat kézzel.\n\tInformáció a parancsokról: help\n\tKilépési parancs: quit");
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
            			System.out.printf("%-40s"+GREEN+"Success\n"+RESET,tests.get(selectedTest));
            		else
            			System.out.printf("%-40s"+RED+"Failure\n"+RESET,tests.get(selectedTest));
                    break;
                case 3:
                	ArrayList<String> alltests=getTestNames();
                	boolean success=true;
                	for(String test : alltests) {
                		runTestFromName(test);
                		if(evaluateTestFromName(test))
                			System.out.printf("%-40s"+GREEN+"Success\n"+RESET,test);
                		else {
                			System.out.printf("%-40s"+RED+"Failure\n"+RESET,test);
                			success=false;
                		}
                	}
                	if(success)
            			System.out.println(GREEN+"\nMinden teszt sikeresen lefutott!"+RESET);
            		else 
            			System.out.println(RED+"\nEgy vagy több teszt hibás eredményt adott!"+RESET);
                	break;
                case 4:
                    quit=true; // Kilépés
                    break;
            }
            System.out.println(); // Üres sor a jobb olvashatóság érdekében
        } while(!quit);
	}
    
    /**
     * A program belépési pontja.
     * @param args A program argumentumai
     * @throws FileNotFoundException Ha a naplófájl nem található
     */
    public static void main(String[] args) throws FileNotFoundException {
    	if(args.length>0 && args[0].equals("test")) {
    		random = new MyRandom(true);
    		test(args);	// első parancssori argumentum "test" összes teszt futtatása: "test all"
    	}
    	else {
    		random = new MyRandom(false);
    		Student reds=new Student();
    		Student blues=new Student();
    		PlayerController red = new PlayerController(SelectionColor.Red, reds);
			PlayerController blue = new PlayerController(SelectionColor.Blue, blues);
            Labyrinth labyrinth = new Labyrinth();
            labyrinth.generateRooms(reds, blues);
            ViewLabyrinth viewLabyrinth = new ViewLabyrinth(labyrinth);
			red.setLabyrinthView(viewLabyrinth);
			blue.setLabyrinthView(viewLabyrinth);

			GameFrame mf = new GameFrame(viewLabyrinth,red,blue);

            viewLabyrinth.initLab();
            EventQueue.invokeLater(new Runnable() {
                public void run() {
		        	mf.setVisible(true);
                }
        	});
            Timer timer = new Timer(1000, a ->{
                    //System.out.printn("update()");
                    //viewLabyrinth.roomsInLabyrinth.get(0).coordinates.x+=1;
                    //viewLabyrinth.roomsInLabyrinth.get(2).coordinates.x-=1;
                    //viewLabyrinth.roomsInLabyrinth.get(0).itemsInRoom.get(0).coordinates.x+=2;
					mf.container.repaint();
					labyrinth.update();
					if(!reds.getRoom().getItems().isEmpty())
					reds.pickupItem(reds.getRoom().getItems().get(0));
					if(!reds.getRoom().getItems().isEmpty())
					reds.pickupItem(reds.getRoom().getItems().get(0));
					labyrinth.update();
					//labyrinth.getRooms().get(0).spawnItem(new Transistor());
					reds.enterRoom(labyrinth.getRooms().get(aa++%10));
					labyrinth.update();
					if(!reds.getInventory().isEmpty())
					reds.putdownItem(reds.getInventory().get(0));
					
					//labyrinth.update();
					//labyrinth.update();
            }
            );
            timer.start();
    	}
    }
}
