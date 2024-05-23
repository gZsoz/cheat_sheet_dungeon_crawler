package view.utils;

/**
 * A képek méretének tárolására használt osztály.
 */
public class Size {
	
	/**
	 * A kép szélessége.
	 */
	private int width;
	
	/**
	 * A kép magassága.
	 */
	private int height;
	
	/**
	 * Konstruktor egy méret létrehozásához.
	 * @param w a szélesség
	 * @param h s magasság
	 */
	public Size(int w,int h){
		width = w;
		height = h;
	}
	
	/**
	 * Szélesség lekérdezése.
	 * @return a szélesség
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Magasság lekérdezése.
	 * @return a magasság
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Visszaadja, hogy a beadott mérettel azonos-e.
	 */
	@Override
	public boolean equals(Object obj) {
		return ((Size)obj).height==height&&((Size)obj).width==width;
	}

}
