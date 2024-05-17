package View.ViewCharacter;

import Character.Student;
import View.Controller.Controller;
import View.Controller.PlayerController;
import View.Utils.*;
import View.ViewItem.ViewItem;

import java.awt.*;

/**
 * A diák kirajzolásáért felelős osztály.
 */
public class ViewStudent extends ViewCharacter {
	
	/**
	 * A modellbeli diák, amit reprezentál.
	 */
	private Student character;
	private SelectionColor color;

	public ViewStudent(Student stud, Coordinates pos){
		super("student_blue.png", pos);
		character = stud;
		character.subscribe(this);
		Controller.characters.put(character, this);
	}

	public void setImage(SelectionColor color){
		this.color = color;
		if(color == SelectionColor.Blue){
			image = ImageReader.loadImage("res/images/Characters/student_blue.png");
		}
		else if(color == SelectionColor.Red){
			image = ImageReader.loadImage("res/images/Characters/student_red.png");
		}
	}

	private void setItemPositions() {

			if(color == SelectionColor.Red){
				for(int i=0;i<character.getInventory().size();i++) {
					ViewItem item = Controller.items.get(character.getInventory().get(i));
					item.setCoordinates(PlayerController.leftInventoryPositions[i]);
					item.setItemSize(new Size(78,78));
				}
			}
			else if(color == SelectionColor.Blue){
				for(int i=0;i<character.getInventory().size();i++) {
					ViewItem item = Controller.items.get(character.getInventory().get(i));
					item.setCoordinates(PlayerController.rightInventoryPositions[i]);
					item.setItemSize(new Size(78,78));
				}
			}


	}
	
	@Override
	public void propertyChanged(String property) {
		if(property.equals("inventory")) {
			setItemPositions();

		}
		if(property.equals("kicked")) {
			character.unsubscribe(this);
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
