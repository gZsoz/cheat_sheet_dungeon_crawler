package View.ViewCharacter;

import Character.Teacher;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A tanár kirajzolásáért felelős osztály.
 */
public class ViewTeacher extends ViewCharacter {
	
	/**
	 * A modellbeli tanár, amit reprezentál.
	 */
	private Teacher character;

	public ViewTeacher(Teacher teach, Coordinates pos){
		character = teach;
		image = ImageReader.loadImage("res/images/test/testpic.png");
		size = new Size(60,200);
		coordinates = new Coordinates(pos.getX() + 15, pos.getY() - 20);
	}

	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

}
