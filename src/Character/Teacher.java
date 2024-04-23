package Character;

import Items.BatSkin;
import Items.Beer;
import Items.Item;
import Items.Transistor;
import ProtoUtil.ProtoUtil;

/**
 * Kirúgás esetén a Student eltávolítását ez az osztály végzi.
 */
public class Teacher extends Character {
	
	
    /**
     * Megvizsgálja, hogy van-e hallgató a szobában.
     */
	public void checkCollision() {
		ProtoUtil.printLog("checkCollision");
		for(int i = 0; i < currentRoom.getCharacters().size(); i++) {
			Student studentForKick = (Student) currentRoom.getCharacters().get(i);
			if(studentForKick instanceof Student) kick(studentForKick);
		}
    }

	/**
	 * Ugyanaz mint a Character-nek, plusz még meghívja a checkCollision() függvényt.
	 */
    @Override
    public void update() {
		checkCollision();
    }

    /**
     * A paraméterül kapott hallgató kirúgását valósítja meg, azaz 
     * eltávolítja a hallgatót a nyilvántartásból. Ha a hallgató inventory-jában van ez ellen
	 * védekező tárgy, akkor a kirúgás sikertelen és a védekező tárgyat használatba állítja.
     * @param s A hallgató, amelyet ki szándékozik rúgni.
     */
    public void kick(Student s) {
    	ProtoUtil.printLog("kick");
    	if(!s.getInvincible()) {
    		BatSkin b = null;
    		for(int i = 0; i < s.getInventory().size(); i++){
    			Item currentItem = s.getInventory().get(i);
    			if(currentItem instanceof Beer) return; // ha sört találtunk, az minden további nélkül megvéd
    			if(currentItem instanceof BatSkin) b = (BatSkin) currentItem; // denevérbőrt találtunk
    		}
    		if(b != null) { // nem volt sör, de volt denevérbőr
    			b.use();
    		} else { // nem volt sör és denevérbőr sem
    			
    			// kirúgás előtt a diák összes tárgyát lerakatjuk vele a szobába
    			for(int i = 0; i < s.getInventory().size(); i++){
    				Item currentItem = s.getInventory().get(i);
    				if(currentItem instanceof Transistor) { // ha tranzisztor, annak az értékeit default-ra állítjuk
    					Transistor t = (Transistor) currentItem;
    					t.getPair().setLocation(null);
    					t.getPair().setActive(false);
    					t.getPair().setPair(null);
    					t.setPair(null);
    					t.setActive(false);
    					t.setLocation(null);
    				}
    				s.putdownItem(currentItem);
    				currentRoom.addItem(currentItem);
    			}
    			
    			currentRoom.removeCharacter(s); // diák eltávolítása
    		}
    	}
    }
}