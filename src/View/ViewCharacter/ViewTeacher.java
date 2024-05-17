package View.ViewCharacter;

import Character.Teacher;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;

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
		super("teacher.png", pos);
		character = teacher;
		character.subscribe(this);
		GameFrame.container.add(this);
    	GameFrame.viewCharacters.add(this);
		Controller.characters.put(character, this);
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
