package Character;

import Items.BatSkin;
import Map.Room;
import SkeletonUtil.SkeletonUtil;

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
		SkeletonUtil.printLog(name + ".checkCollision()");
		SkeletonUtil.increaseIndent();
		if(SkeletonUtil.binaryQuestion("Van hallgató a szobában?")) {
			this.kick(new Student());
		} else {
		}
		SkeletonUtil.decreaseIndent();
    	return false;
    }

    @Override
    public void update() {
    	SkeletonUtil.printLog(name + ".update()");
		SkeletonUtil.increaseIndent();
		this.checkCollision();
		SkeletonUtil.decreaseIndent();
    }

    /**
     * A paraméterül kapott hallgató kirúgását valósítja meg, azaz 
     * eltávolítja a hallgatót a nyilvántartásból. Ha a hallgató inventory-jában van ez ellen
	 * védekező tárgy, akkor a kirúgás sikertelen és a védekező tárgyat használatba állítja.
     * @param s A hallgató, amelyet ki szándékozik rúgni.
     */
    public void kick(Student s) {
    	SkeletonUtil.printLog(name + ".kick(Student)");
		SkeletonUtil.increaseIndent();
		s.getInventory();
		if(SkeletonUtil.binaryQuestion("Van söröspohár a hallgatónál?")) {
		} else if(SkeletonUtil.binaryQuestion("Van denevérbőr a hallgatónál?")) {
			new BatSkin().use();
		} else {
			new Room().removeCharacter(s);
		}
		SkeletonUtil.decreaseIndent();
    }
}