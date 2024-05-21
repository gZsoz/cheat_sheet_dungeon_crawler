package View.ViewCharacter;

import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.ViewItem.ViewItem;
import Character.Character;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * A különböző karakterek grafikus osztályának az ősosztálya.
 */
public abstract class ViewCharacter extends JComponent implements Subscriber {
	protected Character character;
	
	protected final String charactersPath = "Characters/";
	
	public ViewCharacter(Character character, Coordinates coords){
		this.character = character;
		this.character.subscribe(this);
		size = new Size(60,150);
		coordinates = coords;
		Containers.characters.put(this.character, this);
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
	
	public void setCoordinates(Coordinates c) {
		coordinates=c;
	}
	
	/**
	 * Az inventory-ban megjelenítendő tárgyak.
	 */
	protected ArrayList<ViewItem> itemsInInventory;
	
	public abstract void setItemPositions();
	
	public void propertyChanged(String property) {
		if(property.contains("inventory")) { // küldő: Character
			setItemPositions();
		}
	}
}
