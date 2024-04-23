package Items;

import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItems extends Item {

    
	protected int duration=5;
	protected boolean usable=true;
	protected boolean isactive=false;
	
	
	/**
     * duration attribútum értéket csökkenti a Time osztály időmérése alapján, 
     * amennyiben a usable értéke true, illetve átbillenti azt hamis értékre, 
     * ha a duration eléri a 0-t.
     */
    public void reduceDuration() {
    	if(duration>0 && usable==true)
    		duration-=1;
    	else
    		isactive=false;
    		usable=false;
    	ProtoUtil.printLog("reduceDuration");
    }

	/**
	 * A tárgy aktív állapotának lekérdezése
	 * @return A tárgy aktív állapota
	 */
	public boolean getisactive(){
		ProtoUtil.printLog("getisactive");
		return isactive;}
   
    /**
     * a gyermekosztályok által leírt tárgyak használatát és hatását elindító függvény, 
     * gyermekosztályok implementálják. Item ősosztályból örökölt metódus
     */
    public abstract void use();
    public void update() {
		if(isactive)
    	reduceDuration();
    };

}