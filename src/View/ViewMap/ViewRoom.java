package View.ViewMap;

import Map.Room;
import View.Utils.*;
import View.ViewCharacter.ViewCharacter;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import View.ViewItem.ViewItem;

import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * A szoba grafikus osztálya.
 */
public class ViewRoom implements View, Subscriber {
	
	/**
	 * A modellbeli szoba, amit reprezentál.
	 */
	protected Room room;
	
	/**
	 * A szoba képe, ami megjelenik.
	 */
	protected ImageIO image;
	
	/**
	 * A szoba képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	protected Coordinates coordinates;
	
	/**
	 * A szobában megjelenítendő tárgyak.
	 */
	protected ArrayList<ViewItem> itemsInRoom;
	
	/**
	 * A szobában megjelenítendő karakterek.
	 */
	protected ArrayList<ViewCharacter> charactersInRoom;
	
	/**
	 * A szobában megjelenítendő környezeti tényezők.
	 */
	protected ArrayList<ViewEnvironmentalFactors> environmentalFactorsInRoom;
	
	/**
	 * Kijelölt-e az adott szoba (szoba váltásnál) és ha igen milyen színnel.
	 */
	protected SelectionColor selected;
	
	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * A szoba és benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint() {
	    // TODO document why this method is empty
	}
}
