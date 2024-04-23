package Character;

import java.util.List;
import java.util.Random;

import EnvironmentalFactor.Gas;
import EnvironmentalFactor.Sticky;
import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * A szobák takarítását ez osztály végzi.
 */
public class Cleaner extends Character {
	
	/**
	 * Kiszellőztet egy szobában, ezzel megszüntetve a szoba gázosságát, emellett a szobában található összes karaktert, akik nincsenek stunolva átlépteti egy szomszédos szobába és elhelyez egy Sticky EnvironmentalFactor-t a szobában.
	 * @param r A kitakarítandó szoba.
	 */
	public void clean(Room r) {
		ProtoUtil.printLog("enterRoom");
		
		// nem kábult karakterek kitessékelése
		Random rand = new Random();
		for(int i = 0; i < r.getCharacters().size(); i++) {
			Character currentCharacter = r.getCharacters().get(i);
			if(currentCharacter.getStunned() == false) {
				List<Room> roomsToMove = r.getNeighbours();
				int idx = rand.nextInt(r.getCharacters().size()); // milyen indexű szobába tegyük a karaktert
				currentCharacter.enterRoom(roomsToMove.get(idx));
			}
		}
		
		r.getEnvironmentalFactors().removeIf(n -> n.getClass().equals(Gas.class)); // gáz megszűntetése
		
		// új ragacsosság betétele
		Sticky st = new Sticky();
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
}
