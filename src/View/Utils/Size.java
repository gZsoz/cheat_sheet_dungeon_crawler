package View.Utils;

/**
 * A képek méretének tárolására használt osztály.
 */
public class Size {
	
	/**
	 * Szélesség lekérdezése
	 * @return szélesség
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Magasság lekérdezése
	 * @return magasság
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * A kép szélessége.
	 */
	private int width;
	
	/**
	 * A kép magassága.
	 */
	private int height;

	public Size(int w,int h){
		width = w;
		height = h;
	}
	
	@Override
    public boolean equals(Object obj) {
		return ((Size)obj).height==height&&((Size)obj).width==width;
	}

}
