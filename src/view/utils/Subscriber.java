package view.utils;

/**
 * A feliratkozók értesítéséért felelős interfész felelős interfész.
 */
public interface Subscriber {
	
	/**
	 * A feliratkozók értesítésére szolgáló függvény, megadható benne, hogy az osztály mely része változott.
	 * @param property az osztály megváltozott adattagja
	 */
	public void propertyChanged(String property);
}
