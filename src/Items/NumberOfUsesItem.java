package Items;
/**
 * Ebből az osztályból származnak le azok a tárgyak, 
 * amiket csak bizonyos számú alkalommal lehet használni. 
 * Felelőssége számon tartani, hogy hány alkalommal lehet még használatba venni az adott tárgyat.
 */
public abstract class NumberOfUsesItem extends Item {

    /**
     * Egy tárgy használata. A tárgyak a saját, egyedi módjukon kerülnek használatba, 
     * ezért ezt külön-külön valósítják meg a  leszármazottak.
     */
	public abstract void use();
}
