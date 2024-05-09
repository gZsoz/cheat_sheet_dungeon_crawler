package View.Utils;

import View.ViewCharacter.ViewStudent;
import View.ViewMap.ViewLabyrinth;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

/**
 * Felelősség: Létrehoz egy fix méretű JPanelt és erre fog majd rajzolni a többi osztály a graphics segítségével.
 * Ősosztálya: javax.swing.JFrame
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Runnable {
	private ViewLabyrinth viewLabyrinth;

	@Override
	public void paint(Graphics g){
		viewLabyrinth.paint(g);
	}

	public GameFrame(ViewLabyrinth vl){
		super();
		viewLabyrinth = vl;
	}

	@Override
	public void run() {
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1820,980));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.black);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
    }
}