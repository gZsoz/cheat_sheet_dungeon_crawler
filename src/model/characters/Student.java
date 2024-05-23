package model.characters;

import java.util.ArrayList;

import main.Main;
import model.items.Item;
import model.map.Room;

/**
 * A Student osztály felelős a hallgatók inventory-jában lévő aktiválható tárgyak aktiválásáért,
 * valamint a hallgató érinthetetlenségének számontartásáért.
 */
public class Student extends Character {
	
	/**
	 * A hallgató érinthetetlensége.
	 */
	 private boolean invincible;
	 
	 /**
	 * Konstruktor egy hallgató létrehozásához.
	 */
	public Student() {
		this.inventory = new ArrayList<Item>();
		this.stunned = 0;
		this.hasDefense = false;
		this.invincible = false;
	}
	
	/**
	 * Kostruktor egy hallgató létrehozásához.
	 * @param currentRoom melyik szobában van éppen a hallgató
	 */
	public Student(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.inventory = new ArrayList<Item>();
		this.stunned = 0;
		this.hasDefense = false;
		this.invincible = false;
	}
	
	/**
	 * Érinthetetlenség lekérdezése.
	 * @return a hallgató érinthetetlenségi állapota
	 */
	public boolean getInvincible() {
		Main.printLog("getInvincible");
		return invincible;
	}
	
	/**
	 * Érinthetetlenség beállítása.
	 * @param state ha érinthetetlen a karakter, akkor igaz, egyébként hamis
	 */
	public void setInvincible(boolean state) {
		Main.printLog("setInvincible");
		notifySubscribers("invincible");
		invincible = state;
	}
	 
	/**
	 * A paraméterül kapott tárgy hatásának megkezdése.
	 * @param i az aktiválandó tárgy
	 */
	public void activate(Item i) {
		Main.printLog("activate");
		if (!(stunned > 0 && stunned <= stunTime)) {
			i.onActivate();
		}
	}
}