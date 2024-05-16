package View.ViewCharacter;

import Character.Student;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import java.awt.*;

/**
 * A diák kirajzolásáért felelős osztály.
 */
public class ViewStudent extends ViewCharacter {
	
	/**
	 * A modellbeli diák, amit reprezentál.
	 */
	private Student character;

	public ViewStudent(Student stud, Coordinates pos){
		super("student_blue.png", pos);

		character = stud;
		character.subscribe(this);
	}

	public void setImage(SelectionColor color){
		if(color == SelectionColor.Blue){
			image = ImageReader.loadImage("res/images/Characters/student_blue.png");
		}
		else if(color == SelectionColor.Red){
			image = ImageReader.loadImage("res/images/Characters/student_red.png");
		}
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
	}
}
