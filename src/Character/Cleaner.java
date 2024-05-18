package Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import EnvironmentalFactor.EnvironmentalFactors;
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
		this.stunned = 0;
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
		for(Character temp : new ArrayList<Character>(currentRoom.getCharacters())) {
			if(!temp.getClass().equals(Cleaner.class) && (temp.getStunned()==0 || temp.getStunned()>stunTime)) {
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
			r.notifySubsribers("factors");
			ProtoUtil.printLog("removeEnvironmentalFactor");
		}

		// új ragacsosság betétele ha még nincs benne
		r.spawnEnvironmentalFactor(new Sticky(r));
	}
	
	/**
	 * Ugyanaz, mint a Character-nek, emelett ha a takarító befér a szobába, meghívja a clean() függvényét.
	 * @param r A szoba, amelybe be akar menni.
	 * @return Ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal.
	 */
	@Override
	public boolean enterRoom(Room r) {
		ProtoUtil.printLog("enterRoom");
		if(r.getCharacters().size() < r.getCapacity() && !(stunned > 0 && stunned <= stunTime)) {
    		currentRoom.removeCharacter(this);
    		r.addCharacter(this);
    		currentRoom = r;
    		setStunned(0);
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
