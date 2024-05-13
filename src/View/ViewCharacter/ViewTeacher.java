package View.ViewCharacter;

import Character.Student;
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

	
	public ViewTeacher(Teacher teacher, Coordinates pos){
		super("testpic.png", pos);
		character = teacher;
		character.subscribe(this);
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
