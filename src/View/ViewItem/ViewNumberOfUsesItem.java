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
	
	protected Coordinates[] fixedDotPositions;

	public ViewNumberOfUsesItem(Item item, Coordinates coor) {
		super(item, coor);
		setFixedDotPositions();
	}
	
	private void setFixedDotPositions() {
		int remaininguses = ((NumberOfUsesItem) item).getRemainingUses();
		if(remaininguses != 0){
			fixedDotPositions = new Coordinates[remaininguses];
			int distance = size.getWidth() / (remaininguses + 1);
			int startingXPos = coordinates.getX() + distance;
			for(int i = 0; i < remaininguses; i++){
				fixedDotPositions[i] = new Coordinates(startingXPos, coordinates.getY() + size.getHeight() * 9/10);
				startingXPos += distance;
			}
		}
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("remaininguses")) {
			
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.YELLOW);
		/*g2D.fillRect(coordinates.getX(), (int) (coordinates.getY() + size.getHeight() * 0.9), size.getWidth(),10);
		g2D.setColor(Color.GREEN);

		g2D.fillRect((int) (coordinates.getX()),
				(int) (coordinates.getY() + size.getHeight() * 0.9),
				(int)((((DecayingItems) item).getDuration() / (double) DecayingItems.defaultDuration) * size.getWidth()) ,
				10);*/
		
		setFixedDotPositions();
		for(int i = 0; i < ((NumberOfUsesItem) item).getRemainingUses(); i++) {
			g2D.fillOval(fixedDotPositions[i].getX(), fixedDotPositions[i].getY(), 10, 10);
		}
	}
}
