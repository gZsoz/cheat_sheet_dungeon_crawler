package view.viewitems.viewdecayingitems;

import model.items.decayingitems.FakeMask;
import view.utils.Coordinates;
import view.utils.ImageReader;
import view.viewitems.ViewItem;

/**
 * A hamis maszk kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewFakeMask extends ViewMask {
	
	/**
	 * Konstruktor egy hamis maszk nézet létrehozásához.
	 * @param fm a modellbeli hamis maszk
	 * @param coor a koordináták
	 */
	public ViewFakeMask(FakeMask fm, Coordinates coor) {
		super(fm, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
	}
	
	/**
	 * A maszk képének beállítása attól függően, hogy hamis-e.
	 */
	@Override
	public void setItemImage() {
		if(this.size.equals(ViewItem.inventorySize)) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"mask_fake.png");
		} else if(this.size.equals(ViewItem.roomSize)) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
		}
	}
}
