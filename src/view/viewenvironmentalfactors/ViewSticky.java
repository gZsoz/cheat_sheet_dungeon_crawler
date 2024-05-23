package view.viewenvironmentalfactors;

import java.awt.Graphics;
import java.awt.Graphics2D;

import model.environmentalfactors.Sticky;
import view.utils.Coordinates;
import view.utils.ImageReader;

/**
 * A ragacs grafikus osztálya.
 */
@SuppressWarnings("serial")
public class ViewSticky extends ViewEnvironmentalFactor {
	
	/**
	 * Konstruktor egy ragacs nézet létrehozásához.
	 * @param sticky a modellbeli ragacs
	 * @param c a koordináták
	 */
	public ViewSticky(Sticky sticky, Coordinates c){
		super(sticky, c);
		image = ImageReader.loadImage(ImageReader.path+envFactorsPath+"sticky.png");
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}
	
	/**
	 * A ragacs kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(((Sticky)environmentalFactor).getRemainingEntries() <= 0) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(image,coordinates.getX()-15,coordinates.getY()-15,size.getWidth(),size.getHeight(),null);
		}
	}
}
