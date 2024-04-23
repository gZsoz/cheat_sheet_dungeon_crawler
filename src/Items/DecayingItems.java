package Items;

import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItems extends Item {

    
	int duration=5;
	boolean usable=true;
	boolean isactive=false;
	
	
	/**
     * duration attribútum értéket csökkenti a Time osztály időmérése alapján, 
     * amennyiben a usable értéke true, illetve átbillenti azt hamis értékre, 
     * ha a duration eléri a 0-t.
     */
    public void reduceDuration() {
    	if(duration>0 && usable==true)
    		duration-=1;
    	else
    		usable=false;
    	ProtoUtil.printLog("reduceDuration()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    public boolean getisactive() {
    	return isactive;
    }
    /**
     * a gyermekosztályok által leírt tárgyak használatát és hatását elindító függvény, 
     * gyermekosztályok implementálják. Item ősosztályból örökölt metódus
     */
    public abstract void use();
    public void update() {
    	ProtoUtil.printLog("update()");
		if(isactive)
    	reduceDuration();
		ProtoUtil.increaseIndent();
		ProtoUtil.printLog("ReduceDuration()");
		ProtoUtil.decreaseIndent();
    };

}