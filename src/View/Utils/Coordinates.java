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
}
