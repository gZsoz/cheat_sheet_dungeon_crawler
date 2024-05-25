package view.viewitems.viewdecayingitems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.items.Item;
import model.items.decayingitems.DecayingItem;
import view.utils.Coordinates;
import view.viewitems.ViewItem;

/**
 * A különböző időérzékeny tárgyak grafikus osztályának az ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewDecayingItem extends ViewItem {
	
	/**
	 * Konstruktor egy időérzékeny tárgy nézet létrehozásához.
	 * @param item a modellbeli időérzékeny tárgy
	 * @param coor a koordináták
	 */
	public ViewDecayingItem(Item item, Coordinates coor) {
		super(item, coor);
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}
	
	/**
	 * Az időérzékeny tárgyak kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);
		g2D.fillRect(coordinates.getX(), (int) (coordinates.getY() + size.getHeight() * 0.9), size.getWidth(),10);
		g2D.setColor(Color.GREEN);
	
		g2D.fillRect((int) (coordinates.getX()),
				(int) (coordinates.getY() + size.getHeight() * 0.9),
				(int)((((DecayingItem) item).getDuration() / (double) DecayingItem.defaultDuration) * size.getWidth()) ,
				10);
	}
}
