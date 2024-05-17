package View.ViewItem;

import View.Controller.Controller;
import View.Utils.*;

import javax.swing.*;

import Items.BatSkin;
import Items.Item;

import java.awt.*;

/**
 * A különböző tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewItem extends JComponent implements Subscriber {
	
	protected Item item;
	
	protected final String itemsPath = "Items/"; 
	
	public ViewItem(Item item,Coordinates coor) {
		this.item = item;
		this.item.subscribe(this);
		size= new Size(40, 40);
		coordinates = coor;
		selected= SelectionColor.Empty;
		GameFrame.container.add(this);
    	GameFrame.viewItems.add(this);
		Controller.items.put(this.item, this);
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

	@Override
	public void propertyChanged(String property) {
		if(property.equals("itemexpired")) {
			Controller.items.remove(item);
			GameFrame.viewItems.remove(this);
			GameFrame.container.remove(this);
		}
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates=coordinates;
	}
	
	/**
	 * Tárgy kirajzolása a megadott koordinátákra.
	 */
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
    	Graphics2D g2D = (Graphics2D) g;
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
    }
}
