package View.ViewItem;

import View.Utils.*;

import javax.swing.*;

import Items.BatSkin;

import java.awt.*;

/**
 * A különböző tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewItem extends JComponent implements Subscriber {
	
	public ViewItem(String src_img,Coordinates coor) {
		image=ImageReader.loadImage(ImageReader.path+"Items/"+src_img);
		size= new Size(40, 40);
		coordinates = coor;
		selected= SelectionColor.Empty;
	}
	
	/**
	 * A tárgy képe, ami megjelenik.
	 */
	protected Image image;
	
	/**
	 * A tárgy képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	public Coordinates coordinates;
	
	/**
	 * Kijelölt-e az adott tárgy (letevésnél, felvevésnél vagy használatnál) és ha igen, milyen színnel.
	 */
	protected SelectionColor selected;

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates=coordinates;
		
	}
}
