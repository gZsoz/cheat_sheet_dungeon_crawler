package View.Utils;

/**
 * Az x és y koordináták tárolása.
 */
public class Coordinates {
	
	/**
	 * X koordináta a képernyőn.
	 */
	public int x;
	
	/**
	 * Y koordináta a képernyőn.
	 */
	private int y;
	
	/**
	 * Konstruktor egy koordinátapár létrehozásához.
	 * @param x az x koordináta
	 * @param y az y koordináta
	 */
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * X koordináta lekérdezése.
	 * @return x koordináta
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Y koordináta lekérdezése.
	 * @return y koordináta
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Visszaadja, hogy a beadott koordinátapárossal azonos-e.
	 */
	@Override
	public boolean equals(Object obj) {
		return ((Coordinates)obj).x==x&&((Coordinates)obj).y==y;
	}
	
	/**
	 * A két beadott koordinátapáros által meghatározott távolságot határozza meg.
	 * @param c1 az első koordinátapáros
	 * @param c2 a második koordinátapáros
	 * @return a két koordinátapáros által meghatározott távolság
	 */
	public static double distanceBetweenCoords(Coordinates c1, Coordinates c2){
		return Math.sqrt(Math.pow(c1.x - c2.x,2) + Math.pow(c1.y - c2.y,2));
	}
}
