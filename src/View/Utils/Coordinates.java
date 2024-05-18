package View.Utils;

/**
 * Az x és y koordináták tárolása.
 */
public class Coordinates {

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * X koordináta a képernyőn.
	 */
	public int x;
	
	/**
	 * Y koordináta a képernyőn.
	 */
	private int y;

	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}

	public static double distanceBetweenCoords(Coordinates c1, Coordinates c2){
		return Math.sqrt(Math.pow(c1.x - c2.x,2) + Math.pow(c1.y - c2.y,2));
	}
}
