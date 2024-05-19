package View.ViewItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Items.DecayingItems;
import Items.Item;
import Items.NumberOfUsesItem;
import View.Utils.Coordinates;

/**
 * A különböző valahányszor használható tárgyak grafikus osztályának az ősosztálya.
 */
public abstract class ViewNumberOfUsesItem extends ViewItem {
	
	protected Coordinates[] fixedDotPositions=new Coordinates[3];

	public ViewNumberOfUsesItem(Item item, Coordinates coor) {
		super(item, coor);
		setFixedDotPositions();
	}
	
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
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}
	
	@Override
	public void setCoordinates(Coordinates coordinates) {
		super.setCoordinates(coordinates);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (size.getWidth() == 78) {
			Graphics2D g2D = (Graphics2D) g;
			setFixedDotPositions();
			g2D.setColor(Color.YELLOW);
			for (int i = 0; i < ((NumberOfUsesItem) item).getRemainingUses(); i++) {
				g2D.fillOval(fixedDotPositions[i].getX(), fixedDotPositions[i].getY(), size.getWidth() / 4, size.getHeight() / 4);
			} 
		}
	}
}
