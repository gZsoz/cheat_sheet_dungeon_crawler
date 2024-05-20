package Items;

import ProtoUtil.ProtoUtil;

/**
 * Ebből az osztályból származnak le azok a tárgyak, 
 * amiket csak bizonyos számú alkalommal lehet használni. 
 * Felelőssége számon tartani, hogy hány alkalommal lehet még használatba venni az adott tárgyat.
 */
public abstract class NumberOfUsesItem extends Item {

    protected int RemainingUses; // A maradék használatok száma

    /**
     * A maradék használatok számának lekérdezése.
     * @return A maradék használatok száma
     */
    public int getRemainingUses(){
        ProtoUtil.printLog("getRemainingUses"); // Logolás
        return RemainingUses;
    }

    /**
     * A maradék használatok számának beállítása.
     * @param x Az új maradék használatok száma
     */
    public void setRemainingUses(int x){
        ProtoUtil.printLog("setRemainingUses"); // Logolás
        RemainingUses=x;
        notifySubsribers("remaininguses");
        if(RemainingUses == 0) {
			notifySubsribers("itemexpired");
			int idx = owner.getInventory().indexOf(this);
			if(!owner.getInventory().remove(this)) {
				System.out.println("Olyan NumberOfUsesItem lett eltávolítva az inventoryból, ami nincs benne az inventoryban!");
			}
			owner.notifySubsribers("inventory removed "+idx);
        }
    }
    
    /**
     * Egy tárgy használata. A tárgyak a saját, egyedi módjukon kerülnek használatba, 
     * ezért ezt külön-külön valósítják meg a leszármazottak.
     */
    public abstract void use();
}