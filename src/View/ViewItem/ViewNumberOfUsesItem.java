package View.ViewItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Items.Item;
import Items.NumberOfUsesItem;
import View.Utils.Coordinates;
import View.Utils.Size;

/**
 * A különböző valahányszor használható tárgyak grafikus osztályának az ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewNumberOfUsesItem extends ViewItem {
	
	/**
	 * A hátralévő használati lehetőségek számát jelző pöttyök koordinátái.
	 */
	protected Coordinates[] fixedDotPositions = new Coordinates[3];
	
	/**
	 * Konstruktor egy valahányszor használható tárgy nézet létrehozásához.
	 * @param item a modellbeli valahányszor használható tárgy
	 * @param coor a koordináták
	 */
	public ViewNumberOfUsesItem(Item item, Coordinates coor) {
		super(item, coor);
		setFixedDotPositions();
	}
	
	/**
	 * A hátralévő használati lehetőségek számát jelző pöttyök pozícióinak kiszámítása.
	 */
	private void setFixedDotPositions() {
		int remaininguses = ((NumberOfUsesItem) item).getRemainingUses();
		if(remaininguses != 0){
			int dotwidth = size.getWidth() / 4;
			int distance = size.getWidth() / 8;
			int middlepart = dotwidth * remaininguses + (remaininguses - 1) * distance;
			int startingXPos = coordinates.getX() + (size.getWidth() - middlepart) / 2;
			for(int i = 0; i < remaininguses; i++){
				fixedDotPositions[i] = new Coordinates(startingXPos, coordinates.getY() + size.getHeight() * 75/100);
				startingXPos += dotwidth + distance;
			}
		}
	}
	
	/**
	 * Beállítja a hátralévő használati lehetőségek számát jelző pöttyök helyét.
	 */
	@Override
	public void setItemSize(Size size) {
		super.setItemSize(size);
		setFixedDotPositions();
	}
	
	/**
	 * A következőkről kap értesítést: a hátralévő használati lehetőségek száma változott.
	 */
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("remaininguses")) // küldő: NumberOfUsesItem
			setFixedDotPositions();
	}
	
	/**
	 * A különböző valahányszor használható tárgyak kirajzolása.
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (size.equals(ViewItem.inventorySize)) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.setColor(Color.YELLOW);
			for (int i = 0; i < ((NumberOfUsesItem) item).getRemainingUses(); i++) {
				g2D.fillOval(fixedDotPositions[i].getX(), fixedDotPositions[i].getY(), size.getWidth() / 4, size.getHeight() / 4);
			} 
		}
	}
}
