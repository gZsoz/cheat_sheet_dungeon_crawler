package View.Utils;

import View.Controller.PlayerController;
import View.ViewCharacter.ViewCharacter;
import View.ViewCharacter.ViewStudent;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import View.ViewItem.ViewItem;
import View.ViewMap.ViewLabyrinth;
import View.ViewMap.ViewRoom;

import javax.imageio.ImageIO;
import javax.swing.*;

import Character.Character;
import Items.Item;

import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Felelősség: Létrehoz egy fix méretű JPanelt és erre fog majd rajzolni a többi osztály a graphics segítségével.
 * Ősosztálya: javax.swing.JFrame
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public static JPanel container;
    
    public static ArrayList<ViewItem> viewItems = new ArrayList<>();
    public static ArrayList<ViewEnvironmentalFactors> viewEnvs = new ArrayList<>();
    public static ArrayList<ViewCharacter> viewCharacters = new ArrayList<>();
    public static ArrayList<ViewRoom> viewRooms = new ArrayList<>();
    public static ViewLabyrinth vl;
    public static PlayerController red, blue;
    
    
	public GameFrame(ViewLabyrinth vl, PlayerController red, PlayerController blue){
		super("cheat_sheet");
		this.vl=vl;
		this.red=red;
		this.blue=blue;
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1820,980));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container =  new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for(ViewRoom i : viewRooms)
					i.paint(g);
				for(ViewEnvironmentalFactors i : viewEnvs)
					i.paint(g);
				for(ViewItem i : viewItems)
					i.paint(g);
				for(ViewCharacter i : viewCharacters)
					i.paint(g);
				vl.paint(g);
				red.paint(g);
				blue.paint(g);
			};
		};
		container.setBackground(Color.black);
		LayoutManager overlay = new OverlayLayout(container);
		container.setLayout(overlay);
		container.add(red);
		container.add(blue);
		container.add(vl);
		container.add(red.getViewStudent());
		container.add(blue.getViewStudent());


		this.add(container, BorderLayout.CENTER);

		this.pack();
		this.setLocationRelativeTo(null);
	}
}