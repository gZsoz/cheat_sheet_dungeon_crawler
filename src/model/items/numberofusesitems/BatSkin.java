package model.items.numberofusesitems;

import main.Main;
import model.characters.Student;

/**
 * A tárgyhoz tartozó objektum feladata, hogy veszély esetén megvédje a karaktereket 
 * az oktatók szipolyozásától és hogy nyilvántartsa a védelemnyújtások számát. 
 * Abban az esetben ha elfogytak a használási lehetőségek, a karakterek szempontjából 
 * használhatatlanná kell tegye magát.
 */
public class BatSkin extends NumberOfUsesItem {
	
	/**
	 * Ezzel a változóval állítható be a main-ban az alapértelmezett használatszám.
	 */
	public static int defaultRemainingUses = 3;
	
	/**
	 * Konstruktor egy BatSkin létrehozásához.
	 */
	public BatSkin() {
		sticky=false;
		RemainingUses=defaultRemainingUses;
	}
		
	/**
	 * Érinthetetlenné teszi a hallgatót az oktatókkal szemben. Automatikusan használódik, amikor a hallgatót kirúgnák
	 * és védelmet nyújt nekik, amíg az adott szobából ki nem mennek, hogy legyen esélyük elmenekülni az oktató elől.
	 */
	@Override
	public void use() {
	    Main.printLog("use");
	    if(owner instanceof Student) ((Student)owner).setInvincible(true);
	    if(RemainingUses>0) this.setRemainingUses(RemainingUses-1);
	}
	
	/**
	 * Végrehajtja azokat a tevékenységeket, amelyeknek a tárgy felvételénél kell megtörténniük.
	 */
	@Override
	public void onPickUp() {
		Main.printLog("onPickUp");
	}
}