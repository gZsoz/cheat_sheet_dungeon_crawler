package View.ViewCharacter;

import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.ViewItem.ViewItem;

import javax.swing.*;

import Character.Cleaner;

import java.awt.*;
import java.util.ArrayList;

/**
 * A különböző karakterek grafikus osztályának az ősosztálya.
 */
public abstract class ViewCharacter extends JComponent implements Subscriber {
	
	public ViewCharacter(String src_img, Coordinates pos){
		image = ImageReader.loadImage(ImageReader.path+src_img);
		size = new Size(60,200);
		coordinates = new Coordinates(pos.getX() + 15, pos.getY() - 20);
	}
	
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
