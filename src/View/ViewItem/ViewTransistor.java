package View.ViewItem;

import Items.Transistor;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A tranzisztor kirajzolásáért felelős.
 */
public class ViewTransistor extends ViewItem {
	
	private Image pairedOnImage;
	private Image pairedOffImage;
	private Image notPairedOffImage;
	
	public ViewTransistor(Transistor tr, Coordinates coor) {
		super(tr, coor);
		pairedOnImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_on.png");
		pairedOffImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_pair.png");
		notPairedOffImage=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_off.png");
		image=notPairedOffImage;
		
	}
	
	//@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("isactive")||property.equals("pair")) {
			if(((Transistor)item).getActive()==true&&((Transistor)item).getPair()!=null) {
				image=pairedOnImage;
			}else if(((Transistor)item).getActive()==false&&((Transistor)item).getPair()!=null) {
				image=pairedOffImage;
			}else if(((Transistor)item).getActive()==false&&((Transistor)item).getPair()==null) {
				image=notPairedOffImage;
			}else if(((Transistor)item).getActive()==true&&((Transistor)item).getPair()==null){
				image=notPairedOffImage;
			}
		}
	}

}
