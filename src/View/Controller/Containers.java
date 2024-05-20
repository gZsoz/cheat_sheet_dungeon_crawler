package View.Controller;

import Map.Room;
import View.ViewItem.ViewItem;
import View.ViewMap.ViewRoom;

import java.util.HashMap;

import Items.Item;
import View.ViewCharacter.*;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import Character.Character;
import EnvironmentalFactor.EnvironmentalFactors;

/**
 * Felelősség: A modellbeli objektumok összekötése a View-beli objektumokkal
 */
public class Containers {

    public static HashMap<Item, ViewItem> items= new HashMap<>();
    public static HashMap<Character, ViewCharacter> characters = new HashMap<>();
    public static HashMap<Room, ViewRoom> rooms= new HashMap<>();
    public static HashMap<EnvironmentalFactors, ViewEnvironmentalFactors> envs= new HashMap<>();

}