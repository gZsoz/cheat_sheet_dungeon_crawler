package Map;

import java.util.ArrayList;
import java.util.List;

import Character.Character;
import ProtoUtil.ProtoUtil;

/**
 * A CursedRoom osztály egy speciális típusú szobát reprezentál a játékban, amely az alap Room osztálytól származik.
 * Felelőssége megegyezik a szoba osztállyal, emellett felelőssége még az ajtók eltüntetése és megjelenítése.
 */
public class CursedRoom extends Room{
	int closeDuration=2;
    boolean isOpen=true;
	
    /**
     * Beállítja az ajtók állapotát zártnak és inicializálja a closeDuration-t a definiált kezdőértékre.
     */
    public void hideDoors() {
        ProtoUtil.printLog("hideDoors");
        removeAllDoors();
    }
    
    /**
     * A szoba összes ajtaját eltávolítja.
     */
    public void removeAllDoors() {
        ProtoUtil.printLog("removeAllDoors");
        isOpen = false;
    }
	
    /**
     * Hozzáadja a paraméterként kapott karaktert a listához, majd három másodpercre eltünteti a szoba összes ajtaját (szomszédait),
     * ha a szoba kapacitása ezt nem engedné meg, akkor Exceptiont dob.
     * @param character A hozzáadandó karakter
     * @throws Exception Ha a szoba kapacitása nem engedi meg a karakter hozzáadását
     */
    @Override
    public void addCharacter(Character character) {
        ProtoUtil.printLog("addCharacter");
        super.addCharacter(character);
        hideDoors();
    }
    
    /**
     * Csökkenti a szoba zárva maradásának idejét.
     */
    public void reduceDuration() {
        ProtoUtil.printLog("reduceDuration");
        if (closeDuration > 0) {
            closeDuration--;
            if (closeDuration == 0)
                isOpen = true;
        }
    }
    
    /**
     * Visszaadja a szoba szomszédos szobáit. Ha a szoba zárva van, üres listát ad vissza.
     * @return A szomszédos szobák listája
     */
    @Override
    public List<Room> getNeighbours() {
        ProtoUtil.printLog("getNeighbours");
        if (isOpen)
            return neighbours;
        return new ArrayList<Room>();
    }
    
    /**
     * Meghívja a tárolt Character-ek és EnvironmentalFactorok update() metódusát 
     * és csökkenti a szoba zárva maradásának idejét.
     */
    @Override
    public void update() {
        super.update();
        reduceDuration();
    }
}