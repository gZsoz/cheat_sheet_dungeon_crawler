package View.ViewItem;

import View.Utils.*;

import java.awt.Image;

/**
 * A különböző tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewItem implements View, Subscriber {
	
	/**
	 * A tárgy képe, ami megjelenik.
	 */
	Image image;
	
	/**
	 * A tárgy képének mérete.
	 */
	Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	Coordinates coordinates;
	
	/**
	 * Kijelölt-e az adott tárgy (letevésnél, felvevésnél vagy használatnál) és ha igen milyen színnel.
	 */
	SelectionColor selected;
}
