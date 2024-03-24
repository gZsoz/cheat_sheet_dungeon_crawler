package SkeletonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
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
    private static String indent=""; // Behúzás az üzenetekhez

    /**
     * Visszaadja az aktuális behúzást.
     * @return Az aktuális behúzás
     */
    public static String getIndent() {
        return indent;
    }

    /**
     * Növeli az aktuális behúzást.
     */
    public static void increaseIndent() {
        indent+="   ";
    }

    /**
     * Csökkenti az aktuális behúzást.
     */
    public static void decreaseIndent() {
        indent=indent.substring(0, indent.length() - 3);
    }

    /**
     * Kiírja az üzenetet a konzolra és a naplófájlba.
     * @param str Az üzenet
     */
    public static void printLog(String str) {
        String message=SkeletonUtil.getIndent()+str; // Az üzenet behúzással ellátva
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

    /**
     * Inicializáló teszt.
     */
    public static void initTest() {
        new Labyrinth().generateRooms();
    }

    /**
     * Szoba teszt.
     */
    public static void szobaTest() {
        String[] opt={
                "Szobák összeolvadása",
                "Szoba osztódása",
                "Tárgy megidézése"
        };
        switch(question("Mit szeretnél tenni a Szobával?", opt)) {
            case 1:
                new Labyrinth().mergeRoom(new Room("Szoba_1"),new Room("Szoba_2"));
                break;
            case 2:
                new Labyrinth().splitRoom(new Room());
                break;
            case 3:
                String[] opt2 = {
                        "Söröspohár",		// 1
                        "Maszk",			// 2
                        "Táblatörlő rongy", // 3
                        "Camembert",		// 4
                        "Denevérbőr",		// 5
                        "Logarléc",			// 6
                        "Tranzisztor", 		// 7
                };
                switch(SkeletonUtil.question("Milyen tárgyat idéz meg?", opt2)) {
                    case 1:
                        Beer b= new Beer();
                        b.create();
                        new Room().spawnItem(b);
                        break;
                    case 2:
                        Mask m= new Mask();
                        m.create();
                        new Room().spawnItem(m);
                        break;
                    case 3:
                        WetCloth w= new WetCloth();
                        w.create();
                        new Room().spawnItem(w);
                        break;
                    case 4:
                        CabbageCamembert c= new CabbageCamembert();
                        c.create();
                        new Room().spawnItem(c);
                        break;
                    case 5:
                        BatSkin i= new BatSkin();
                        i.create();
                        new Room().spawnItem(i);
                        break;
                    case 6:
                        SlideRule s= new SlideRule();
                        s.create();
                        new Room().spawnItem(s);
                        break;
                    case 7:
                        Transistor t= new Transistor();
                        t.create();
                        new Room().spawnItem(t);
                        break;
                    default:
                }
                break;
        }
    }

    /**
     * Tanár teszt.
     */
    public static void tanarTest() {
        String[] opt={
                "Bemmenni egy szobába",
                "Felvenni egy tárgyat",
                "Kirúgni egy diákot"
        };
        switch(question("Mit szeretnél tenni a Tanárral?", opt)) {
            case 1:
                new Teacher().enterRoom(new Room());
                break;
            case 2:
                String[] opt2 = {
                        "Söröspohár",		// 1
                        "Maszk",			// 2
                        "Táblatörlő rongy", // 3
                        "Camembert",		// 4
                        "Denevérbőr",		// 5
                        "Logarléc",			// 6
                        "Tranzisztor" 		// 7
                };
                switch(SkeletonUtil.question("Milyen tárgyat vesz fel a tanár?", opt2)) {
                    case 1:
                        new Teacher().pickupItem(new Beer());
                        break;
                    case 2:
                        new Teacher().pickupItem(new Mask());
                        break;
                    case 3:
                        new Teacher().pickupItem(new WetCloth());
                        break;
                    case 4:
                        new Teacher().pickupItem(new CabbageCamembert());
                        break;
                    case 5:
                        new Teacher().pickupItem(new BatSkin());
                        break;
                    case 6:
                        new Teacher().pickupItem(new SlideRule());
                        break;
                    case 7:
                        new Teacher().pickupItem(new Transistor());
                        break;
                    default:
                }
                break;
            case 3:
                new Teacher().update();
                break;
        }
    }

    /**
     * Hallgató teszt.
     */
    public static void hallgatoTest() {
        String[] opt={
                "Bemmenni egy szobába",
                "Felvenni egy tárgyat",
                "Letenni egy tárgyat",
                "Használni egy tárgyat"
        };
        switch(question("Mit szeretnél tenni a Hallgatóval?", opt)) {
            case 1:
                new Student().enterRoom(new Room());
                break;
            case 2:
                String[] opt2 = {
                        "Söröspohár",		// 1
                        "Maszk",			// 2
                        "Táblatörlő rongy", // 3
                        "Camembert",		// 4
                        "Denevérbőr",		// 5
                        "Logarléc",			// 6
                        "Tranzisztor" 		// 7
                };
                switch(SkeletonUtil.question("Milyen tárgyat vesz fel a hallgató?", opt2)) {
                    case 1:
                        new Student().pickupItem(new Beer());
                        break;
                    case 2:
                        new Student().pickupItem(new Mask());
                        break;
                    case 3:
                        new Student().pickupItem(new WetCloth());
                        break;
                    case 4:
                        new Student().pickupItem(new CabbageCamembert());
                        break;
                    case 5:
                        new Student().pickupItem(new BatSkin());
                        break;
                    case 6:
                        new Student().pickupItem(new SlideRule());
                        break;
                    case 7:
                        new Student().pickupItem(new Transistor());
                        break;
                    default:
                }
                break;
            case 3:
                String[] opt3 = {
                        "Söröspohár",		// 1
                        "Maszk",			// 2
                        "Táblatörlő rongy", // 3
                        "Camembert",		// 4
                        "Denevérbőr",		// 5
                        "Logarléc",			// 6
                        "Tranzisztor" 		// 7
                };
                switch(SkeletonUtil.question("Milyen tárgyat tesz le a hallgató?", opt3)) {
                    case 1:
                        new Student().putdownItem(new Beer());
                        break;
                    case 2:
                        new Student().putdownItem(new Mask());
                        break;
                    case 3:
                        new Student().putdownItem(new WetCloth());
                        break;
                    case 4:
                        new Student().putdownItem(new CabbageCamembert());
                        break;
                    case 5:
                        new Student().putdownItem(new BatSkin());
                        break;
                    case 6:
                        new Student().putdownItem(new SlideRule());
                        break;
                    case 7:
                        new Student().putdownItem(new Transistor());
                        break;
                    default:
                }
                break;
            case 4:
                String[] opt4 = {
                        "Camembert",
                        "Tranzisztor"
                };
                switch(SkeletonUtil.question("Milyen tárgyat használjon a hallgató?", opt4)) {
                    case 1:
                        new Student().activate(new CabbageCamembert());
                        break;
                    case 2:
                        new Student().pickupItem(new Transistor());
                        break;
                    default:
                }
                break;
        }
    }

    /**
     * A program belépési pontja.
     * @param args A program argumentumai
     * @throws FileNotFoundException Ha a naplófájl nem található
     */
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File(filename); // Naplófájl inicializálása
        boolean quit=false; // Kilépési feltétel
        do {
            String[] opt={
                    "Hallgató",
                    "Tanár",
                    "Szoba",
                    "Inicializálás",
                    "Kilépés"
            };
            int ans=question("Mit szeretnél tesztelni?", opt); // Felhasználói választás bekérése
            PrintWriter writer = new PrintWriter(myObj);
            writer.close(); // Naplófájl tartalmának törlése
            switch(ans) {
                case 1:
                    hallgatoTest(); // Hallgató teszt végrehajtása
                    break;
                case 2:
                    tanarTest(); // Tanár teszt végrehajtása
                    break;
                case 3:
                    szobaTest(); // Szoba teszt végrehajtása
                    break;
                case 4:
                    initTest(); // Inicializáló teszt végrehajtása
                    break;
                case 5:
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

