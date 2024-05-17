package View.ViewItem;

import Items.Item;
import View.Utils.Coordinates;

/**
 * A különböző valahányszor használható tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewNumberOfUsesItem extends ViewItem {

	public ViewNumberOfUsesItem(Item item, Coordinates coor) {
		super(item, coor);
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("remaininguses")) {
			
		}
	}
}
