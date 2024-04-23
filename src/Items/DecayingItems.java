package Items;

import ProtoUtil.ProtoUtil;
import Time.iTask;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItems extends Item implements iTask {
    
	/**
	 * Milyen hoszzú ideig jó a tárgy.
	 */
	protected int duration = 5;
	
	/**
	 * Használható-e a tárgy.
	 */
	protected boolean usable = true;
	
	/**
	 * Aktív-e a tárgy.
	 */
	protected boolean isActive = false;

	/**
     * A duration attribútum értékét csökkenti a Time osztály időmérése alapján, 
     * amennyiben a usable értéke true, illetve átbillenti azt hamis értékre, 
     * ha a duration eléri a 0-t.
     */
    public void reduceDuration() {
    	ProtoUtil.printLog("reduceDuration");
    	if(duration>0 && usable==true)
    		duration-=1;
    	else {
    		isActive=false;
    		usable=false;
    		owner.getInventory().remove(this);
    	}
    }

	/**
	 * A tárgy aktív állapotának lekérdezése.
	 * @return A tárgy aktív állapota.
	 */
	public boolean getIsActive() {
		ProtoUtil.printLog("getIsActive");
		return isActive;
	}
	
	/**
	 * A tárgy aktív állapotának beállítása.
	 * @param isactive A tárgy aktív állapota.
	 */
	public void setIsActive(boolean isactive) {
		ProtoUtil.printLog("setIsActive");
		this.isActive = isactive;
	}
   
    /**
     * A gyermekosztályok által leírt tárgyak használatát és hatását elindító függvény, 
     * gyermekosztályok implementálják. Item ősosztályból örökölt metódus.
     */
    public abstract void use();
    
    /**
     * Az időérzékeny tárgyak frissítése.
     */
    @Override
    public void update() {
		if(isActive)
			reduceDuration();
    };

}