package Model.Items.NumberOfUsesItems;

import Model.Items.Item;
import main.Main;

/**
 * Ebből az osztályból származnak le azok a tárgyak, amiket csak bizonyos számú alkalommal lehet használni. 
 * Felelőssége számon tartani, hogy hány alkalommal lehet még használatba venni az adott tárgyat.
 */
public abstract class NumberOfUsesItem extends Item {
	
	/**
	 * A maradék használatok száma.
	 */
	protected int RemainingUses;
	
	/**
	 * A maradék használatok számának lekérdezése.
	 * @return a maradék használatok száma
	 */
	public int getRemainingUses(){
	    Main.printLog("getRemainingUses"); // Logolás
	    return RemainingUses;
	}
	
	/**
	 * A maradék használatok számának beállítása.
	 * @param x az új maradék használatok száma
	 */
	public void setRemainingUses(int x){
	    Main.printLog("setRemainingUses"); // Logolás
	    RemainingUses=x;
	    notifySubscribers("remaininguses");
	    if(RemainingUses == 0) {
			notifySubscribers("itemexpired");
			int idx = owner.getInventory().indexOf(this);
			if(!owner.getInventory().remove(this)) {
				System.out.println("Olyan NumberOfUsesItem lett eltávolítva az inventoryból, ami nincs benne az inventoryban!");
			}
			owner.notifySubscribers("inventory removed "+idx);
	    }
	}
	
	/**
	 * Egy tárgy használata. A tárgyak a saját, egyedi módjukon kerülnek használatba, 
	 * ezért ezt külön-külön valósítják meg a leszármazottak.
	 */
	public abstract void use();
}