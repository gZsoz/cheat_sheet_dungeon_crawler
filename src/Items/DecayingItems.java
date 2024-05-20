package Items;

import ProtoUtil.ProtoUtil;
import Time.iTask;

import java.awt.*;

/**
 * Absztrakt osztály, amely összefoglalja az időérzékeny tárgyak közös tulajdonságait és metódusait.
 */
public abstract class DecayingItems extends Item implements iTask {
    
	public static int defaultDuration = 100000000;

	public int getDuration() {
		return duration;
	}

	/**
	 * Milyen hoszzú ideig jó a tárgy.
	 */
	protected int duration = defaultDuration;
	
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
		duration--;
		notifySubsribers("duration");
		if(duration==0) {
			notifySubsribers("itemexpired");
			owner.getInventory().remove(this);
			owner.notifySubsribers("inventory");
			onDrop();
			ProtoUtil.printLog("Decaying item expired and removed.");
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
		notifySubsribers("isactive");
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
    	if(duration>0 && isActive) reduceDuration();
    };
}