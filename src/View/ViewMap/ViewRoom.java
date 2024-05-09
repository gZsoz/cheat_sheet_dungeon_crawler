package View.ViewMap;

import Map.Room;
import View.Utils.*;
import View.ViewCharacter.ViewCharacter;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import View.ViewItem.ViewItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * A szoba grafikus osztálya
 */
public class ViewRoom implements View, Subscriber {
    /**
     * A modellbeli szoba, amit reprezentál
     */
    Room room;

    /**
     * A szoba képe, ami megjelenik
     */
    Image image;

    /**
     * A szoba képének mérete
     */
    Size size;

    /**
     * A képernyőn megjelenítendő x és y koordináták.
     */
    Coordinates coordinates;

    /**
     * A szobában megjelenítendő tárgyak
     */
    ArrayList<ViewItem> itemsInRoom;

    /**
     * A szobában megjelenítendő karakterek
     */
    ArrayList<ViewCharacter> charactersInRoom;

    /**
     * A szobában megjelenítendő környezeti tényezők
     */
    ArrayList<ViewEnvironmentalFactors> environmentalFactorsInRoom;

    /**
     * Kijelölt-e az adott szoba (szoba váltásnál) és ha igen milyen színnel
     */
    SelectionColor selected;

    @Override
    public void propertyChanged(String property) {
        // TODO document why this method is empty
    }

    /**
     * A szoba és benne lévő tárgyak, környezeti változók és karakterek kirajzolása
     */
    @Override
    public void paint() {
        // TODO document why this method is empty
    }
}
