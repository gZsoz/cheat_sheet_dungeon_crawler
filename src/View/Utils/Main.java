package View.Utils;

import Map.Labyrinth;
import View.ViewMap.ViewLabyrinth;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Labyrinth labyrinth = new Labyrinth();
        labyrinth.generateRooms();
        ViewLabyrinth viewLabyrinth = new ViewLabyrinth(labyrinth);


        SwingUtilities.invokeLater(new GameFrame(viewLabyrinth));



        Timer timer = new Timer(1000, _ ->
                System.out.println("update()")
        );
        //timer.start();
    }
}
