package Character;

import java.util.ArrayList;

import Items.BatSkin;
import Items.Beer;
import Items.FakeBatSkin;
import Items.Item;
import Items.Transistor;
import Items.WetCloth;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Kirúgás esetén a Student eltávolítását ez az osztály végzi.
 */
public class Teacher extends Character {
	
	public static int angryTime = 3 * ProtoUtil.fps; // mennyi ideig lesz angry
	public static int moveTime = 7 * ProtoUtil.fps; // menny idő telik el átlagosan 2 enterroom között
	private int timeToMove=moveTime; // menny idő múlva megy be egy szobába
	private int neighbourWithStudent=0;
	
	/**
	 * Kostruktor.
	 * @param currentRoom Melyik szobában van éppen a tanár.
	 */
	public Teacher(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.inventory = new ArrayList<Item>();
		this.stunned = 0;
		this.hasDefense = false;
	}
	
	public void setTimeToMove(int timetomove) {
		timeToMove=timetomove;
		notifySubsribers("angry");
	}
	
	public boolean isAngry() {
		return timeToMove<=angryTime;
	}
	
	private void searchForStudents() {
		for(int i=0;i<currentRoom.getNeighbours().size();i++) {
			for(Character c : currentRoom.getNeighbours().get(i).getCharacters()) {
				if(c instanceof Student){
					neighbourWithStudent=i;
					return;
				}
			}
		}
	}
	
    /**
     * Megvizsgálja, hogy van-e hallgató a szobában.
     */
	public void checkCollision() {
		ProtoUtil.printLog("checkCollision");
		for(Character temp : new ArrayList<Character>(currentRoom.getCharacters())) {
			if((Object)temp instanceof Student){
				kick((Student) temp); // ha találunk, kirúgjuk a hallgatót
			}
		}
    }

    /**
     * A paraméterül kapott hallgató kirúgását valósítja meg, azaz 
     * eltávolítja a hallgatót a nyilvántartásból. Ha a hallgató inventory-jában van ez ellen
	 * védekező tárgy, akkor a kirúgás sikertelen és a védekező tárgyat használatba állítja.
     * @param s A hallgató, amelyet ki szándékozik rúgni.
     */
    public void kick(Student s) {
    	ProtoUtil.printLog("kick");
    	if(!s.getInvincible() && !(stunned > 0 && stunned <= stunTime)) {
    		BatSkin b = null;
    		for(int i = 0; i < s.getInventory().size(); i++){
    			Item currentItem = s.getInventory().get(i);
    			if(currentItem instanceof Beer) return; // ha sört találtunk, az minden további nélkül megvéd
    			if(currentItem instanceof WetCloth) return; // ha nedves törlőrongyot találtunk, az elkábítja a tanárt
    			if(currentItem instanceof BatSkin && !(currentItem instanceof FakeBatSkin)) {b = (BatSkin) currentItem;} // denevérbőrt találtunk
    		}
    		if(b != null) { // nem volt sör, de volt denevérbőr
    			b.use();
    		} else { // nem volt sör és denevérbőr sem
    			
    			// kirúgás előtt a diák összes tárgyát lerakatjuk vele a szobába
    			for(Item currentItem : new ArrayList<Item>(s.getInventory())){
    				if(currentItem instanceof Transistor) { // ha tranzisztor, annak az értékeit default-ra állítjuk
    					Transistor t = (Transistor) currentItem;
    					if (t.getPair() != null) {
							t.getPair().setLocation(null);
							t.getPair().setActive(false);
							t.getPair().setPair(null);
						}
						t.setPair(null);
    					t.setActive(false);
    					t.setLocation(null);
    				}
    				s.putdownItem(currentItem);
    			}
    			
    			currentRoom.removeCharacter(s); // diák eltávolítása
    			s.notifySubsribers("kicked");
    			ProtoUtil.printLog("student removed from game");
    		}
    	}
    }

    @Override
    public boolean enterRoom(Room r) {
    	setTimeToMove(2*ProtoUtil.random.nextInt(moveTime, 20)+angryTime+1);
    	return super.enterRoom(r);
    }
    
	/**
	 * Ugyanaz mint a Character-nek, plusz még meghívja a checkCollision() függvényt.
	 */
    @Override
    public void update() {
    	if(!isStunned() && --timeToMove==angryTime) {
    		notifySubsribers("angry");
    		searchForStudents();
    	}else if(timeToMove<=0&&!currentRoom.getNeighbours().isEmpty()) {
    		if(neighbourWithStudent < currentRoom.getNeighbours().size())
    			enterRoom(currentRoom.getNeighbours().get(neighbourWithStudent));
    		else
    			enterRoom(currentRoom.getNeighbours().get(ProtoUtil.random.nextInt(currentRoom.getNeighbours().size(), 0)));
    		neighbourWithStudent=0;
    	}
    	super.update();
		checkCollision();
    }
}