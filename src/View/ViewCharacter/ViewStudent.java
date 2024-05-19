package View.ViewCharacter;

import Character.Student;
import Character.Character;
import View.Controller.Controller;
import View.Controller.PlayerController;
import View.Utils.*;
import View.ViewItem.ViewItem;

import java.awt.*;

/**
 * A diák kirajzolásáért felelős osztály.
 */
public class ViewStudent extends ViewCharacter {

	private SelectionColor color;

	public ViewStudent(Student student, Coordinates pos){
		super(student, pos);
	}

	public void setImage(SelectionColor color){
		this.color = color;
		if(color == SelectionColor.Blue){
			image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_blue.png");
		}
		else if(color == SelectionColor.Red){
			image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_red.png");
		}
	}
	
	@Override
	public void setItemPositions() {
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
		super.propertyChanged(property);
		if(property.equals("kicked")) {
			character.unsubscribe(this);
		}
		else if(property.equals("stun")){
			if(character.getStunned()>0 && character.getStunned()< Character.stunTime){
				if(color == SelectionColor.Red){
					image = ImageReader.loadImage("res/images/Characters/student_red_stunned.png");
				}
				else if(color == SelectionColor.Blue){
					image = ImageReader.loadImage("res/images/Characters/student_blue_stunned.png");
				}
			}
			else {
				if(color == SelectionColor.Blue){
					image = ImageReader.loadImage("res/images/Characters/student_blue.png");
				}
				else if(color == SelectionColor.Red){
					image = ImageReader.loadImage("res/images/Characters/student_red.png");
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;

		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

	}
}
