package View.ViewEnvironmentalFactor;

import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.View;

import java.awt.*;

/**
 * A környezeti változók grafikus osztályának ős osztálya
 */
public abstract class ViewEnvironmentalFactors implements View {
    /**
     * A környezeti változó képe, ami megjelenik
     */
    Image image;

    /**
     * A környezeti változó képének mérete
     */
    Size size;

    /**
     * A képernyőn megjelenítendő x és y koordináták
     */
    Coordinates coordinates;

    /**
     * A környezeti változó kirajzolása
     */
    @Override
    public void paint() {
        // TODO document why this method is empty
    }
}
