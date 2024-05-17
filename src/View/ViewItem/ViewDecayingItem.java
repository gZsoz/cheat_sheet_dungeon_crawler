package View.ViewItem;

import Items.Item;
import View.Utils.Coordinates;

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
    
}
