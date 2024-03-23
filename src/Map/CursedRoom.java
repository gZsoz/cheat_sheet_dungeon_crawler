package Map;

import Character.Character;
import EnvironmentalFactor.Gas;
import SkeletonUtil.SkeletonUtil;

/**
 * A CursedRoom osztály egy speciális típusú szobát reprezentál a játékban, amely az alap Room osztálytól származik.
 * Felelőssége megegyezik a szoba osztállyal, emellett felelőssége még az ajtók eltüntetése és megjelenítése.
 */
public class CursedRoom extends Room{
	
	/**
     * Konstruktor egy elátkozott szoba létrehozásához. CursedRoom-ra állítja a nevet.
     */
	public CursedRoom() {
		super();
		name="CursedRoom";
	}
	
	/**
     * Konstruktor egy elátkozott szoba létrehozásához.
     * @param name A szoba neve
     */
	public CursedRoom(String name) {
		super(name);
	}
	
	/**
     * Beállítja az ajtók állapotát zártnak és inicializálja a closeDuration-t a definiált kezdőértékre.
     */
    public void hideDoors() {
		SkeletonUtil.printLog("hideDoors()");
		SkeletonUtil.increaseIndent();
    	removeAllDoors();
    	SkeletonUtil.decreaseIndent();
	}
    
    /**
     * A szoba összes ajtaját eltávolítja.
     */
	public void removeAllDoors() {
		SkeletonUtil.printLog("removeAllDoors()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	
	/**
     * Hozzáadja a paraméterként kapott karaktert a listához, majd három másodpercre eltünteti a szoba összes ajtaját (szomszédait),
     * ha a szoba kapacitása ezt nem engedné meg, akkor Exceptiont dob.
     * @param character A hozzáadandó karakter
     */
    @Override
	public void addCharacter(Character character) {
		SkeletonUtil.printLog("addCharacter()");
		SkeletonUtil.increaseIndent();
    	hideDoors();
		if(SkeletonUtil.binaryQuestion("Gázos-e a szoba?")) {
			new Gas().stun(character);
		}else {
		}
    	SkeletonUtil.decreaseIndent();
	}
}