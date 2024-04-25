package Character;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import EnvironmentalFactor.Gas;
import EnvironmentalFactor.Sticky;
import Items.Item;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * A szobák takarítását ez osztály végzi.
 */
public class Cleaner extends Character {
	
	/**
	 * Kostruktor.
	 * @param currentRoom Melyik szobában van éppen a takarító.
	 */
	public Cleaner(Room currentRoom) {
		this.currentRoom = currentRoom;
		this.inventory = new ArrayList<Item>();
		this.stunned = false;
		this.hasDefense = false;
	}
	
	/**
	 * Kiszellőztet egy szobában, ezzel megszüntetve a szoba gázosságát, emellett a szobában található összes karaktert, akik nincsenek stunolva átlépteti egy szomszédos szobába és elhelyez egy Sticky EnvironmentalFactor-t a szobában.
	 * @param r A kitakarítandó szoba.
	 */
	public void clean(Room r) {
		ProtoUtil.printLog("clean");
		
		// nem kábult karakterek kitessékelése
		Random rand = new Random();
		/*for(int i = 0; i < r.getCharacters().size(); i++) {
			Character currentCharacter = r.getCharacters().get(i);
			if(!currentCharacter.getClass().equals(Cleaner.class) && !currentCharacter.getStunned()) {
				List<Room> roomsToMove = r.getNeighbours();
				int idx = rand.nextInt(roomsToMove.size()); // milyen indexű szobába tegyük a karaktert
				currentCharacter.enterRoom(roomsToMove.get(idx));
			}
		}*/
		
		for(Character temp : new ArrayList<Character>(currentRoom.getCharacters())) {
			if(!temp.getClass().equals(Cleaner.class) && !temp.getStunned()) {
				List<Room> roomsToMove = r.getNeighbours();
				int idx = rand.nextInt(roomsToMove.size()); // milyen indexű szobába tegyük a karaktert
				temp.enterRoom(roomsToMove.get(idx));
			}
		}

		boolean roomHasGas = r.getEnvironmentalFactors().stream().anyMatch(n -> n.getClass().equals(Gas.class));
		if(roomHasGas){
			r.getEnvironmentalFactors().removeIf(n -> n.getClass().equals(Gas.class)); // gáz megszűntetése
			ProtoUtil.printLog("removeEnvironmentalFactor");
		}


		// új ragacsosság betétele
		Sticky st = new Sticky(currentRoom);
		r.addEnvironmentalFactor(st);
	}
	
	/**
	 * Ugyanaz, mint a Character-nek, emelett ha a takarító befér a szobába, meghívja a clean() függvényét.
	 * @param r A szoba, amelybe be akar menni.
	 * @return Ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal.
	 */
	@Override
	public boolean enterRoom(Room r) {
		ProtoUtil.printLog("enterRoom");
		if(r.getCharacters().size() < r.getCapacity()) {
    		currentRoom.removeCharacter(this);
    		r.addCharacter(this);
    		currentRoom = r;
    		clean(r);
    		return true;
    	}
    	return false;
	}
	
	/**
	 * Ugyanaz mint a karakternek.
	 */
	@Override
	public void update() {
		super.update();
	}
}
