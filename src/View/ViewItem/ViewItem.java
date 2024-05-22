package View.ViewItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

import Model.Items.Item;
import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.SelectionColor;
import View.Utils.Size;
import View.Utils.Subscriber;

/**
 * A különböző tárgyak grafikus osztályának az ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewItem extends JComponent implements Subscriber {
	
	/**
	 * Az inventory-ban lévő tárgyak képének mérete.
	 */
	public static Size inventorySize = new Size(78,78);
	
	/**
	 * A szobákban lévő tárgyak képének mérete.
	 */
	public static Size roomSize = new Size(40,40);
	
	/**
	 * A modellbeli tárgy, amit reprezentál.
	 */
	protected Item item;
	
	/**
	 * A tárgyak képeinek elérési útja.
	 */
	protected final String itemsPath = "Items/"; 
	
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
	
	/**
	 * Konstruktor egy tárgy nézet létrehozásához.
	 * @param item a modellbeli tárgy
	 * @param coor a koordináták
	 */
	public ViewItem(Item item, Coordinates coor) {
		this.item = item;
		this.item.subscribe(this);
		size = roomSize;
		coordinates = coor;
		selected= SelectionColor.Empty;
		GameFrame.mainPanel.add(this);
		GameFrame.viewItems.add(this);
		Containers.items.put(this.item, this);
	}
	
	/**
	 * A tárgy képméretének beállítása attól függően, hogy hová kerül.
	 * @param size a tárgy képmérete
	 */
	public void setItemSize(Size size) {
		if(size.equals(inventorySize)&&!this.size.equals(inventorySize)) { // 78 a mérete az inventory tárgyaknak
			selected=SelectionColor.Empty;
		}
		this.size = size;
	}
	
	/**
	 * A tárgy képméretének lekérdezése.
	 * @return a tárgy képmérete
	 */
	public Size getItemSize() {
		return size;
	}
	
	/**
	 * A tárgy képének beállítása.
	 */
	public void setItemImage() {}
	
	/**
	 * A következőkről kap értesítést: a tárgy lejárt, elfogyott.
	 */
	@Override
	public void propertyChanged(String property) {
		if(property.equals("itemexpired")) { // küldő: DecayingItem, NumberOfUsesItem
			Containers.items.remove(item);
			GameFrame.viewItems.remove(this);
			GameFrame.mainPanel.remove(this);
			item.unsubscribe(this);
		}
	}
	
	/**
	 * A tárgy képkoordinátáinak beállítása.
	 * @param coordinates az új koordináták
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates=coordinates;
	}
	
	/**
	 * Beállítja, hogy melyik játékos jelöli ki éppen a tárgyat.
	 * @param selectionColor a kijelölő játékos színe
	 */
	public void setColor(SelectionColor selectionColor) {
		if(selected==SelectionColor.Empty)
			selected = selectionColor;
		else if(selected!=selectionColor)
			selected = SelectionColor.Both;
	}
	
	/**
	 * Eltávolítja egy játékos kijelölését a tárgyról. 
	 * @param selectionColor a játékos színe
	 */
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
	
	/**
	 * Tárgy kirajzolása a megadott koordinátákra.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		if(selected == SelectionColor.Red){
			g2D.setColor(Color.RED);
			g2D.fillRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
		}
		else if(selected == SelectionColor.Blue){
			g2D.setColor(Color.BLUE.brighter());
			g2D.fillRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
		}
		else if(selected == SelectionColor.Both){
			g2D.setColor(Color.BLUE.brighter());
			g2D.fillRect(coordinates.getX()-5,coordinates.getY()-5,size.getWidth()+10,size.getHeight()+10);
			g2D.setColor(Color.RED);
			g2D.fillRect(coordinates.getX()-5,coordinates.getY()-5,(size.getWidth()+10)/2,size.getHeight()+10);
		}
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
	}
}
