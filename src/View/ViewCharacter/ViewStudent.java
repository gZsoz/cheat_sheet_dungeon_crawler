package View.ViewCharacter;

import Character.Student;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;
import java.io.Console;

/**
 * A diák kirajzolásáért felelős osztály.
 */
public class ViewStudent extends ViewCharacter {
	
	/**
	 * A modellbeli diák, amit reprezentál.
	 */
	private Student character;

	public ViewStudent(Student stud, Coordinates pos){
		super("testpic.png", pos);
		character = stud;
		character.subscribe(this);
	}

	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		// példa
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

		g2D.setPaint(Color.black);
		g2D.fillRect(coordinates.getX(),coordinates.getY()+size.getHeight()*3/4,size.getWidth(),size.getHeight() /15);

		g2D.setPaint(Color.green);
		g2D.fillRect(coordinates.getX(),coordinates.getY()+size.getHeight()*3/4,size.getWidth()*3/10,size.getHeight() /15);
	}
}
