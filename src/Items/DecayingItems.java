package Items;

import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItems extends Item {

    /**
     * duration attribútum értéket csökkenti a Time osztály időmérése alapján, 
     * amennyiben a usable értéke true, illetve átbillenti azt hamis értékre, 
     * ha a duration eléri a 0-t.
     */
    public void reduceDuration() {
    	ProtoUtil.printLog(name+".reduceDuration()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    /**
     * a gyermekosztályok által leírt tárgyak használatát és hatását elindító függvény, 
     * gyermekosztályok implementálják. Item ősosztályból örökölt metódus
     */
}