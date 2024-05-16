package View.Utils;

import View.Controller.PlayerController;
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
public class GameFrame extends JFrame {

	public static JPanel container;

	public GameFrame(ViewLabyrinth vl, PlayerController red, PlayerController blue){
		super("cheat_sheet");

		this.setResizable(false);
		this.setPreferredSize(new Dimension(1820,980));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container =  new JPanel();
		container.setBackground(Color.black);
		LayoutManager overlay = new OverlayLayout(container);
		container.setLayout(overlay);
		container.add(red);
		container.add(blue);
		container.add(vl);


		this.add(container, BorderLayout.CENTER);

		this.pack();
		this.setLocationRelativeTo(null);
	}
}