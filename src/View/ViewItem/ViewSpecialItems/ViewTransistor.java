package View.ViewItem.ViewSpecialItems;

import java.awt.Image;

import Model.Items.SpecialItems.Transistor;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.ViewItem.ViewItem;

/**
 * A tranzisztor kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewTransistor extends ViewItem {
	
	/**
	 * A tranzisztor képe, amikor párosítva van és be is van aktiválva.
	 */
	private Image pairedOnImage;
	
	/**
	 * A tranzisztor képe, amikor párosítva van, de nincs beaktiválva.
	 */
	private Image pairedOffImage;
	
	/**
	 * A tranzisztor képe, amikor nincs párosítva.
	 */
	private Image notPairedImage;
	
	/**
	 * Konstruktor egy tranzisztor nézet létrehozásához.
	 * @param tr a modellbeli tranzisztor
	 * @param coor a koordináták
	 */
	public ViewTransistor(Transistor tr, Coordinates coor) {
		super(tr, coor);
		pairedOnImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_on.png");
		pairedOffImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_pair.png");
		notPairedImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_off.png");
		image=notPairedImage;
	}
	
	/**
	 * A következőkről kap értesítést:
	 * a tranzisztor be lett aktiválva,
	 * a tranzisztor össze lett párosítva.
	 */
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("isactive")||property.equals("pair")) { // küldő: Transistor
			if(((Transistor)item).getActive()==true&&((Transistor)item).getPair()!=null) {
				image=pairedOnImage;
			}else if(((Transistor)item).getActive()==false&&((Transistor)item).getPair()!=null) {
				image=pairedOffImage;
			}else if(((Transistor)item).getActive()==false&&((Transistor)item).getPair()==null) {
				image=notPairedImage;
			}else if(((Transistor)item).getActive()==true&&((Transistor)item).getPair()==null){
				image=notPairedImage;
			}
		}
	}
}
