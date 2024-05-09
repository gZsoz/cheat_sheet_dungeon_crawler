package View.ViewItem;

import View.Utils.*;

import java.awt.*;

/**
 * A különböző tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewItem implements View, Subscriber {
	
	/**
	 * A tárgy képe, ami megjelenik.
	 */
	protected Image image;
	
	/**
	 * A tárgy képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	protected Coordinates coordinates;
	
	/**
	 * Kijelölt-e az adott tárgy (letevésnél, felvevésnél vagy használatnál) és ha igen, milyen színnel.
	 */
	protected SelectionColor selected;
}
