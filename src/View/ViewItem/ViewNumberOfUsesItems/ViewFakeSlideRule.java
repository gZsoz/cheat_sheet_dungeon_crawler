package View.ViewItem.ViewNumberOfUsesItems;

import Model.Items.NumberOfUsesItems.FakeSlideRule;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.ViewItem.ViewItem;

/**
 * A hamis logarléc kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewFakeSlideRule extends ViewSlideRule {
	
	/**
	 * Konstruktor egy hamis logarléc nézet létrehozásához.
	 * @param fsr a modellbeli hamis logarléc
	 * @param coor a koordináták
	 */
	public ViewFakeSlideRule(FakeSlideRule fsr, Coordinates coor) {
		super(fsr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
	
	/**
	 * A logarléc képének beállítása attól függően, hogy hamis-e.
	 */
	@Override
	public void setItemImage() {
		if(this.size.equals(ViewItem.inventorySize)) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule_fake.png");
		}else if(this.size.equals(ViewItem.roomSize)) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
		}
	}
}
