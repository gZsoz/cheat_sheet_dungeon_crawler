package View.ViewCharacter;

import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.Utils.View;
import View.ViewItem.ViewItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * A különböző karakterek grafikus osztályának az ősosztálya.
 */
public abstract class ViewCharacter implements View, Subscriber {
	
	/**
	 * A karakter képe, ami megjelenik.
	 */
	protected Image image;
	
	/**
	 * A karakter képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	protected Coordinates coordinates;
	
	/**
	 * Az inventory-ban megjelenítendő tárgyak.
	 */
	protected ArrayList<ViewItem> itemsInInventory;
}
