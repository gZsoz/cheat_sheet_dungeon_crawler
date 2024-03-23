package Character;

import Items.Item;
import SkeletonUtil.SkeletonUtil;

/**
 * A Student osztály felelős a hallgatók inventory-jában lévő aktiválható tárgyak aktiválásáért.
 */
public class Student extends Character {
	
	
	/**
	 * Konstruktor egy hallgató létrehozásához. Student-re állítja a nevet.
	 */
	public Student() {
		name = "Student";
	}
	
	/**
	 * Konstruktor egy hallgató létrehozásához.
	 * @param name A hallgató neve.
	 */
	public Student(String name) {
		this.name = name;
	}
	
    /**
     * A paraméterül kapott tárgy hatásának megkezdése.
     * @param i A tárgy.
     */
    public void activate(Item i) {
    	SkeletonUtil.printLog(name + ".activate(Item)");
		SkeletonUtil.increaseIndent();
		i.use();
		SkeletonUtil.decreaseIndent();
    }

    @Override
    public void update() {
    }
}