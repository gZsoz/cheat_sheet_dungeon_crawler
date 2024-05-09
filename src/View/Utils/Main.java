package View.Utils;

import View.ViewCharacter.ViewStudent;
import View.ViewMap.ViewLabyrinth;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        ViewLabyrinth viewLabyrinth = new ViewLabyrinth();
        SwingUtilities.invokeLater(new GameFrame(viewLabyrinth));



        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("update()");
            }
        });
        timer.start();
    }
}
