package SkeletonUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Character.*;
import EnvironmentalFactor.*;
import Items.*;
import Map.*;

public class SkeletonUtil {
	private static String filename = "log.txt";
	private static String indent="";
	
	public static String getIndent() {
		return indent;
	}
	public static void increaseIndent() {
		indent+="   ";
	}
	public static void decreaseIndent() {
		indent=indent.substring(0, indent.length() - 3);
	}
	public static void printLog(String str) {
		String message=SkeletonUtil.getIndent()+str;
		System.out.println(message);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename, true);
			fos.write((message+'\n').getBytes());
	    	fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static boolean binaryQuestion(String question) {
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println('\n'+question+"\n1) Igen\n2) Nem");
		choice=sc.nextInt();
		sc.nextLine();	// eat the \n
		if(choice<1 || choice>2)
			System.out.println("Hibás input!\n Válasszon a fenti opciók közül!");
		}while(choice<1 || choice>2);
		return (choice==1);
	}
	
	public static int question(String question, String[] opt) {
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println('\n'+question);
		for(int i=0;i<opt.length;i++)
			System.out.println((i+1)+") "+opt[i]);
		choice=sc.nextInt();
		sc.nextLine();	// eat the \n
		if(choice<1 || choice>opt.length)
			System.out.println("Hibás input!\n Válasszon a fenti opciók közül!");
		}while(choice<1 || choice>opt.length);
		return choice;
	}
	public static void hallgatoTest() {
		String[] opt={
				"Bemmenni egy szobába",
				"Felvenni Itemet",
		};
		switch(question("Mit szeretnél tenni a Hallgatóval?", opt)) {
		case 1:
			new Student().enterRoom(new Room());
			break;
		case 2:
			break;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File myObj = new File(filename);
		boolean quit=false;
		do {
			String[] opt={
					"Hallgató",
					"Tanár",
					"Szoba",
					"Kilépés"
			};
			int ans=question("Mit szeretnél tesztelni?", opt);
			PrintWriter writer = new PrintWriter(myObj);
			writer.close();
			switch(ans) {
			case 1:
				hallgatoTest();
				break;
			case 2:
				System.out.println("2");
				break;
			case 3:
				System.out.println("3");
				break;
			case 4:
				quit=true;
				break;
			}
			System.out.println();
			Scanner filesc = new Scanner(myObj);
			while(filesc.hasNextLine())
				System.out.println(filesc.nextLine());
			filesc.close();
		}while(!quit);
	}
}
