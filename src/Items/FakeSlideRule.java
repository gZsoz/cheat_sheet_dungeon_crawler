package Items;

import ProtoUtil.ProtoUtil;

/**
 * Az osztály egy hamis csúszkálószabályzót reprezentál, amely a SlideRuleból származik.
 */
public class FakeSlideRule extends SlideRule{

    /**
     * A FakeSlideRule osztály konstruktora.
     */
    public FakeSlideRule() {
        sticky=false;
        RemainingUses=0; // Nincs maradék használat
    }

    /**
     * A tárgy használatának metódusa.
     */
    @Override
    public void use() {
        ProtoUtil.printLog("use"); // Logolás
    }

}