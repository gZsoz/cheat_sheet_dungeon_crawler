package view.viewitems.viewnumberofusesitems;

import model.items.numberofusesitems.FakeBatSkin;
import view.utils.Coordinates;
import view.utils.ImageReader;
import view.viewitems.ViewItem;

/**
 * A hamis denevérbőr kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewFakeBatSkin extends ViewBatSkin {
	
	/**
	 * Konstruktor egy hamis denevérbőr nézet létrehozásához.
	 * @param fbs a modellbeli hamis denevérbőr
	 * @param coor a koordináták
	 */
	public ViewFakeBatSkin(FakeBatSkin fbs, Coordinates coor) {
		super(fbs, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
	}
	
	/**
	 * A denevérbőr képének beállítása attól függően, hogy hamis-e.
	 */
	@Override
	public void setItemImage() {
		if(this.size.equals(ViewItem.inventorySize)) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"batskin_fake.png");
		}else if(this.size.equals(ViewItem.roomSize)) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
		}
	}
}

