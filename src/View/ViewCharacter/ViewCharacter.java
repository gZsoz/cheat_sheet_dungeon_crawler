package View.ViewCharacter;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

import Model.Characters.Character;
import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.ViewItem.ViewItem;

/**
 * A különböző karakterek grafikus osztályának az ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewCharacter extends JComponent implements Subscriber {
	/**
	 * A modellbeli karakter, amit reprezentál.
	 */
	protected Character character;
	
	/**
	 * A karakterek képeinek elérési útja.
	 */
	protected final String charactersPath = "Characters/";
	
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
	
	/**
	 * Konstruktor egy karakter nézet létrehozásához.
	 * @param character a modellbeli karakter
	 * @param coor a koordináták
	 */
	public ViewCharacter(Character character, Coordinates coor){
		this.character = character;
		this.character.subscribe(this);
		size = new Size(60,150);
		coordinates = coor;
		Containers.characters.put(this.character, this);
	}
	
	/**
	 * A koordináták beállítása.
	 * @param coordinates a koordináták
	 */
	public void setCoordinates(Coordinates c) {
		coordinates=c;
	}
	
	/**
	 * A karakter birtokában lévő tárgyak pozíciójának beállítása.
	 */
	public abstract void setItemPositions();
	
	/**
	 * A következőkről kap értesítést: a karakter inventory-ja megváltozott.
	 */
	public void propertyChanged(String property) {
		if(property.contains("inventory")) { // küldő: Model.Character
			setItemPositions();
		}
	}
	
	/**
	 * A karakter kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
