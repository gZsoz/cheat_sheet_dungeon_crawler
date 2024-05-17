package View.ViewCharacter;

import Character.Student;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
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
		Controller.characters.put(character, this);
	}

	public void setImage(SelectionColor color){
		if(color == SelectionColor.Blue){
			image = ImageReader.loadImage("res/images/Characters/student_blue.png");
		}
		else if(color == SelectionColor.Red){
			image = ImageReader.loadImage("res/images/Characters/student_red.png");
		}
	}

	private void setItemPositions() {
		for(int i=0;i<character.getInventory().size();i++) {
			Controller.items.get(character.getInventory().get(i)).setCoordinates(new Coordinates(100,800));
		}
	}
	
	@Override
	public void propertyChanged(String property) {
		if(property.equals("inventory")) {
			setItemPositions();
			
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		// példa
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}
}
