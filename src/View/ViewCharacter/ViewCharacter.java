package View.ViewCharacter;

import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.Utils.View;
import View.ViewItem.ViewItem;
import java.awt.Image;
import java.util.ArrayList;

/**
 * A különböző karakterek grafikus osztályának az ősosztálya.
 */
public abstract class ViewCharacter implements View, Subscriber {
	
	/**
	 * A karakter képe, ami megjelenik.
	 */
	Image image;
	
	/**
	 * A karakter képének mérete.
	 */
	Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	Coordinates coordinates;
	
	/**
	 * Az inventory-ban megjelenítendő tárgyak.
	 */
	ArrayList<ViewItem> itemsInInventory;
}
