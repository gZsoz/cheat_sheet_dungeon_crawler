package Model.Characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.EnvironmentalFactors.EnvironmentalFactors;
import Model.EnvironmentalFactors.Gas;
import Model.EnvironmentalFactors.Sticky;
import Model.Items.Item;
import Model.Map.Room;
import main.Main;

/**
 * A szobák takarítását ez osztály végzi. NPC karakter, bizonyos időközönként elhatározza, hogy átmegy egy másik szobába.
 */
public class Cleaner extends Character {
	
	/**
	 * Két elhatározás között átlagosan eltelt idő.
	 */
	public static int timeBetweenMoves = 10 * Main.fps;
	
	/**
	 * Mozgási idő. Ennyi idő múlva megy be egy szobába, ha már megszületett az elhatározása.
	 */
	private int moveTime = timeBetweenMoves;
	
	/**
	 * A takarító szobájával szomszédos gázzal teli szoba indexe.
	 */
	private int neighbourWithGas = 0;
	
	/**
	 * Kostruktor egy takarító létrehozásához.
	 * @param currentRoom melyik szobában van éppen a takarító
	 */
	public Cleaner(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.inventory = new ArrayList<Item>();
		this.stunned = 0;
		this.hasDefense = false;
	}
	
	/**
	 * Mozgási idő beállítása.
	 * @param i a mozgási idő
	 */
	private void setMoveTime(int i) {
		moveTime=i;
	}
	
	/**
	 * Kiszellőztet egy szobában, ezzel megszüntetve a szoba gázosságát,
	 * emellett a szobában található összes karaktert, akik nincsenek stunolva átlépteti egy szomszédos szobába
	 * és elhelyez egy Sticky Model.EnvironmentalFactor-t a szobában.
	 * @param r a kitakarítandó szoba
	 */
	public void clean(Room r) {
		Main.printLog("clean");
		
		// nem kábult karakterek kitessékelése
		Random rand = new Random();
		for(Character temp : new ArrayList<Character>(currentRoom.getCharacters())) {
			if(!temp.getClass().equals(Cleaner.class) && (temp.getStunned()==0 || temp.getStunned()>stunTime) && !r.getNeighbours().isEmpty()) {
				List<Room> roomsToMove = r.getNeighbours();
				int idx = rand.nextInt(roomsToMove.size()); // milyen indexű szobába tegyük a karaktert
				temp.enterRoom(roomsToMove.get(idx));
			}
		}
	
		boolean roomHasGas = r.getEnvironmentalFactors().stream().anyMatch(n -> n.getClass().equals(Gas.class));
		if(roomHasGas){
			EnvironmentalFactors expired=null;
			for(EnvironmentalFactors env: r.getEnvironmentalFactors())
				if(env instanceof Gas)
					expired=env;
			r.removeEnvironmentalFactor(expired); // gáz megszűntetése
			r.notifySubscribers("factors");
			Main.printLog("removeEnvironmentalFactor");
		}
	
		// új ragacsosság betétele ha még nincs benne
		r.spawnEnvironmentalFactor(new Sticky(r));
	}
	
	/**
	 * Ugyanaz, mint a Model.Character-nek, emelett ha a takarító befér a szobába, meghívja a clean() függvényét.
	 * @param r a szoba, amelybe be akar menni
	 * @return ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal
	 */
	@Override
	public boolean enterRoom(Room r) {
		setMoveTime(2*Main.random.nextInt(timeBetweenMoves, 20) + Main.fps);
		Main.printLog("enterRoom");
		if(r.getCharacters().size() < r.getCapacity() && !(stunned > 0 && stunned <= stunTime)) {
			Room temp=currentRoom;
			currentRoom = r;
			temp.removeCharacter(this);
			r.addCharacter(this);
			setStunned(0);
			clean(r);
			return true;
		}
		return false;
	}
	
	/**
	 * Ha a takarító szobájának valamelyik szomszédja gázzal teli, annak az indexét állítja be a neighbourWithGas-nek,
	 * egyébként Integer.MAX_VALUE-t.
	 */
	private void searchForGas() {
		for(int i=0;i<currentRoom.getNeighbours().size();i++) {
			for(EnvironmentalFactors factor : currentRoom.getNeighbours().get(i).getEnvironmentalFactors()) {
				if(factor instanceof Gas){
					neighbourWithGas=i;
					return;
				}
			}
		}
		for(int i=0;i<currentRoom.getNeighbours().size();i++) {
			for(EnvironmentalFactors factor : currentRoom.getNeighbours().get(i).getEnvironmentalFactors()) {
				if(factor instanceof Sticky && ((Sticky)factor).getRemainingEntries() <= 0){
					neighbourWithGas=i;
					return;
				}
			}
		}
		neighbourWithGas=Integer.MAX_VALUE;
	}
	
	/**
	 * Ugyanaz mint a Model.Character-nek, plusz a beállított időzítőknek megfelelően magától átmehet egy másik szobába.
	 * Ha van gázzal teli szoba a szomszédai között, akkor mindenképpen olyanba megy.
	 */
	@Override
	public void update() {
		if(--moveTime<=0&&!currentRoom.getNeighbours().isEmpty()) {
			if(neighbourWithGas < currentRoom.getNeighbours().size()) {
				enterRoom(currentRoom.getNeighbours().get(neighbourWithGas));
			}else {
				enterRoom(currentRoom.getNeighbours().get(Main.random.nextInt(currentRoom.getNeighbours().size(), 0)));
			}
			searchForGas();
		}
		super.update();
	}
}
