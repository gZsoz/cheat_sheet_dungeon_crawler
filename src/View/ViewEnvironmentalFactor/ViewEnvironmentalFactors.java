package View.ViewEnvironmentalFactor;

import javax.imageio.ImageIO;

import View.Utils.Coordinates;
import View.Utils.View;

/**
 * Felelősség: A különböző környezeti változók grafikus osztályának az ősosztálya.
 */
public abstract class ViewEnvironmentalFactors implements View {
    /**
     * Környezeti változó képe, ami megjelenik.
     */
    private ImageIO image;
    /**
     * Környezeti változó szélessége.
     */
    private int width;
    /**
     * Környezeti változó magassága.
     */
    private int height;
    /**
     * Képernyőn megjelenítendő x és y koordináták.
     */
    private Coordinates coordinates;

    /**
     * Környezeti változó kirajzolása.
     */
    public abstract void paint();
}