package Character;

import Items.BatSkin;
import Items.Beer;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Kirúgás esetén a Student eltávolítását ez az osztály végzi.
 */
public class Teacher extends Character {
	
	/**
	 * Konstruktor egy oktató létrehozásához. Teacher-re állítja a nevet.
	 */
	public Teacher() {
		name = "Teacher";
	}
	
	/**
	 * Konstruktor egy oktató létrehozásához.
	 * @param name A oktató neve.
	 */
	public Teacher(String name) {
		this.name = name;
	}
	
    /**
     * Megvizsgálja, hogy van-e hallgató a szobában.
     * @return Ha van, akkor igaz értékkel tér vissza, egyébként hamissal.
     */
	public boolean checkCollision() {
		ProtoUtil.printLog(name + ".checkCollision()");
		ProtoUtil.increaseIndent();
		if(ProtoUtil.binaryQuestion("Van hallgató a szobában?")) {
			this.kick(new Student());
		} else {
		}
		ProtoUtil.decreaseIndent();
    	return false;
    }

    @Override
    public void update() {
    	ProtoUtil.printLog(name + ".update()");
		ProtoUtil.increaseIndent();
		this.checkCollision();
		ProtoUtil.decreaseIndent();
    }

    /**
     * A paraméterül kapott hallgató kirúgását valósítja meg, azaz 
     * eltávolítja a hallgatót a nyilvántartásból. Ha a hallgató inventory-jában van ez ellen
	 * védekező tárgy, akkor a kirúgás sikertelen és a védekező tárgyat használatba állítja.
     * @param s A hallgató, amelyet ki szándékozik rúgni.
     */
    public void kick(Student s) {
    	ProtoUtil.printLog(name + ".kick(" + s.name + ")");
		ProtoUtil.increaseIndent();
		s.getInventory();
		if(ProtoUtil.binaryQuestion("Van söröspohár a hallgatónál?")) {
			new Beer().use();
		} else if(ProtoUtil.binaryQuestion("Van denevérbőr a hallgatónál?")) {
			new BatSkin().use();
		} else {
			new Room().removeCharacter(s);
		}
		ProtoUtil.decreaseIndent();
    }
}