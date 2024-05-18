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
	
	public ViewItem(Item item, Coordinates coor) {
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

	public void setItemSize(Size size) {
		if(size.getHeight()==78&&size.getHeight()!=78) {
			selected=SelectionColor.Empty;
		}
		this.size = size;

	}

	public Size getItemSize() {
		return size;
	}

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
			item.unsubscribe(this);
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
		if(selected == SelectionColor.Red){
			g2D.setColor(Color.RED.darker());
			g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}
		else if(selected == SelectionColor.Blue){
			g2D.setColor(Color.BLUE.brighter());
			g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
		}
		else if(selected == SelectionColor.Both){
    		g2D.setColor(Color.GRAY);
			g2D.fillRect(coordinates.getX()-10,coordinates.getY()-10,size.getWidth()+20,size.getHeight()+20);
    	}
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
    	
    }

	public void setColor(SelectionColor selectionColor) {
		if(selected==SelectionColor.Empty)
			selected = selectionColor;
		else if(selected!=selectionColor)
			selected = SelectionColor.Both;
	}
	
	public void removeColor(SelectionColor selectionColor) {
		if(selected==selectionColor)
			selected = SelectionColor.Empty;
		else if(selected==SelectionColor.Both) {
			if(selectionColor==SelectionColor.Blue)
				selected=SelectionColor.Red;
			else if(selectionColor==SelectionColor.Red)
				selected=SelectionColor.Blue;	
			else 
				selected = SelectionColor.Empty;
		}	
	}
}
