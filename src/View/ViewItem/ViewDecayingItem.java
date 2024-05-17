package View.ViewItem;

import Items.DecayingItems;
import Items.Item;
import View.Utils.Coordinates;

import java.awt.*;

/**
 * A különböző időérzékeny tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewDecayingItem extends ViewItem {

	public ViewDecayingItem(Item item, Coordinates coor) {
		super(item, coor);
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.GREEN);
		g2D.fillRect(coordinates.getX(),coordinates.getY() + size.getHeight(), ((DecayingItems) item).getDuration() / DecayingItems.defaultDuration * size.getWidth(),10);
	}
}
