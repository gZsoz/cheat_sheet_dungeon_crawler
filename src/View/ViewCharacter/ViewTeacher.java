package View.ViewCharacter;

import Character.Character;
import Character.Teacher;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A tanár kirajzolásáért felelős osztály.
 */
public class ViewTeacher extends ViewCharacter {
	
	public ViewTeacher(Teacher teacher, Coordinates pos){
		super(teacher, pos);
		image = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher.png");
		GameFrame.container.add(this);
    	GameFrame.viewCharacters.add(this);
	}
	
	@Override
	public void setItemPositions() {}

	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("stun") || property.equals("angry")){
			if(character.isStunned()){
				if(((Teacher)character).isAngry())
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_stunned_and_angry.png");
				else
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_stunned.png");
			} else {
				if(((Teacher)character).isAngry())
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_angry.png");
				else
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher.png");
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
