package view.viewcharacters;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import model.characters.Teacher;
import view.utils.Coordinates;
import view.utils.GameFrame;
import view.utils.ImageReader;

/**
 * A tanár kirajzolásáért felelős osztály.
 */
@SuppressWarnings("serial")
public class ViewTeacher extends ViewCharacter {
	
	/**
	 * A tanár normál képe.
	 */
	private Image normalImage = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher.png");
	
	/**
	 * A tanár képe, amikor el van kábítva.
	 */
	private Image stunnedImage = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_stunned.png");
	
	/**
	 * A tanár képe, amikor mérges.
	 */
	private Image angryImage = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_angry.png");
	
	/**
	 * A tanár képe, amikor el van kábítva és mérges.
	 */
	private Image stunnedAndAngryImage = ImageReader.loadImage(ImageReader.path+charactersPath+"teacher_stunned_and_angry.png");
	
	/**
	 * Konstruktor egy takarító nézet létrehozásához.
	 * @param character a modellbeli takarító
	 * @param coor a koordináták
	 */
	public ViewTeacher(Teacher teacher, Coordinates coor){
		super(teacher, coor);
		image = normalImage;
		GameFrame.mainPanel.add(this);
		GameFrame.viewCharacters.add(this);
	}
	
	/**
	 * A tanár birtokában lévő tárgyak pozíciójának beállítása.
	 */
	@Override
	public void setItemPositions() {}
	
	/**
	 * A következőkről kap értesítést: a tanár el lett kábítva vagy mérges lett.
	 */
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("stun") || property.equals("angry")){ // küldő: Character || Teacher
			if(character.isStunned()){
				if(((Teacher)character).isAngry())
					image = stunnedAndAngryImage;
				else
					image = stunnedImage;
			} else {
				if(((Teacher)character).isAngry())
					image = angryImage;
				else
					image = normalImage;
			}
		}
	}
	
	/**
	 * A tanár kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}

}
