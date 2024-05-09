package View.ViewMap;

import Map.Labyrinth;
import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.Utils.View;

import java.awt.*;
import java.util.ArrayList;

/**
 * A labirintus grafikus osztálya
 */
public class ViewLabyrinth implements View, Subscriber {
    /**
     * A modellbeli, reprezentálandó labirintus
     */
    Labyrinth labyrinth;

    /**
     * A labirintus hátterének megjelenítendő képe
     */
    Image background;

    /**
     * A labirintus háttérképének mérete
     */
    Size size;

    /**
     * A képernyőn megjelenítendő x és y koordináták.
     */
    Coordinates coordinates;

    /**
     * A labirintusban megjelenítendő szobák
     */
    ArrayList<ViewRoom> roomsInLabyrinth;

    /**
     * A szomszédos szobák közti összeköttetés koordinátái
     */
    ArrayList<ArrayList<Coordinates>> routesBetweenRooms;

    @Override
    public void propertyChanged(String property) {
        // TODO document why this method is empty
    }

    /**
     *  A labirintus háttere és a szobák közti utak kirajzolása
     */
    @Override
    public void paint() {
        // TODO document why this method is empty
    }
}