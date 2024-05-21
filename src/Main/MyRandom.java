package Main;

import java.util.Random;

/**
 * Saját random int generáló osztály.
 */
public class MyRandom {
	/**
	 * A Random objektum, amellyel a generálást végezzük.
	 */
	private Random random;
	
	/**
	 * Teszt módban vagyunk-e.
	 */
	public boolean test;
	
	/**
	 * Konstruktor egy saját random int generáló objektum létrehozásához.
	 */
	public MyRandom() {
		random=new Random();
		test=false;
	}
	
	/**
	 * Konstruktor egy saját random int generáló objektum létrehozásához.
	 * @param isTest teszt módban igaz, nem teszt módban hamis
	 */
	public MyRandom(boolean isTest) {
		random=new Random();
		test=isTest;
	}
	
	/**
	 * Random int szám generálása.
	 * @param upper_bound a random int szám felső maximuma (exclusive)
	 * @param testValue teszt módban ez az érték számít
	 * @return ha teszt módban vagyunk, akkor testValue, egyébként egy random int szám (0 ... upper_bound-1)
	 */
	public int nextInt(int upper_bound, int testValue) {
		if(test)
			return testValue;
		else
			return random.nextInt(upper_bound);
	}
}
