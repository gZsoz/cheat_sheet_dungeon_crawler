package view.viewcharacters;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import controller.PlayerController;
import model.characters.Student;
import view.utils.Containers;
import view.utils.Coordinates;
import view.utils.ImageReader;
import view.utils.SelectionColor;
import view.viewitems.ViewItem;

/**
 * A diák kirajzolásáért felelős osztály.
 */
@SuppressWarnings("serial")
public class ViewStudent extends ViewCharacter {
	
	/**
	 * A kék játékos képe.
	 */
	private Image blueImage = ImageReader.loadImage(ImageReader.path+charactersPath+"student_blue.png");
	
	/**
	 * A piros játékos képe.
	 */
	private Image redImage = ImageReader.loadImage(ImageReader.path+charactersPath+"student_red.png");
	
	/**
	 * A diák színe.
	 */
	private SelectionColor color;
	
	/**
	 * Konstruktor egy karakter nézet létrehozásához.
	 * @param character a modellbeli karakter
	 * @param coords a koordináták
	 */
	public ViewStudent(Student student, Coordinates pos){
		super(student, pos);
	}
	
	/**
	 * Diák színének beállítása.
	 * @param color a szín
	 */
	public void setImage(SelectionColor color){
		this.color = color;
		if(color == SelectionColor.Blue){
			image = blueImage;
		}
		else if(color == SelectionColor.Red){
			image = redImage;
		}
	}
	
	/**
	 * A diák birtokában lévő tárgyak pozíciójának beállítása.
	 */
	@Override
	public void setItemPositions() {
		if(color == SelectionColor.Red){
			for(int i=0;i<character.getInventory().size();i++) {
				ViewItem item = Containers.items.get(character.getInventory().get(i));
				item.setCoordinates(PlayerController.leftInventoryPositions[i]);
				item.setItemSize(ViewItem.inventorySize);
				item.setItemImage();
			}
		}
		else if(color == SelectionColor.Blue){
			for(int i=0;i<character.getInventory().size();i++) {
				ViewItem item = Containers.items.get(character.getInventory().get(i));
				item.setCoordinates(PlayerController.rightInventoryPositions[i]);
				item.setItemSize(ViewItem.inventorySize);
				item.setItemImage();
			}
		}
	}
	
	/**
	 * A következőkről kap értesítést:
	 * a diákot kirúgták,
	 * a diák el lett kábítva.
	 */
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		if(property.equals("kicked")) { // küldő: Teacher
			character.unsubscribe(this);
		}
		else if(property.equals("stun")){ // küldő: Character
			if(character.isStunned()){
				if(color == SelectionColor.Red){
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_red_stunned.png");
				}
				else if(color == SelectionColor.Blue){
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_blue_stunned.png");
				}
			}
			else {
				if(color == SelectionColor.Blue){
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_blue.png");
				}
				else if(color == SelectionColor.Red){
					image = ImageReader.loadImage(ImageReader.path+charactersPath+"student_red.png");
				}
			}
		}
	}
	
	/**
	 * A diák kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}
}
