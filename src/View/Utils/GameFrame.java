package View.Utils;

import View.Controller.Controller;
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
    
    public static JComponent instructions = new JComponent() {
    	@Override
		public void paint(Graphics g) {
    		super.paint(g);
    		Graphics2D g2D = (Graphics2D)g;
    		
    		// Mode textbox
    		g2D.setColor(new Color(221,221,221));
    		g2D.drawImage(ImageReader.loadImage("res/images/test/testroom.png"),1820/2 - 240 + 40, 800, 400, 200,null);
    		g2D.fillRect(1820/2 - 220 + 40,
    				815-4,
    				400 - 40,120);
    		
    		// key bindings
    		g2D.setFont(new Font("Monospaced", Font.BOLD, 18));

    		g2D.setColor(Color.black);
    		g2D.drawString("Kijelölő mozgatása",1820/2-95,826);
    		g2D.drawString("Kiválasztás",1820/2-56,846);
    		g2D.drawString("Tárgy eldobása",1820/2-74,866);
    		g2D.drawString("Szoba váltó mód",1820/2-80,886);
    		g2D.drawString("Inventory mód",1820/2-70,906);
    		g2D.drawString("Szobatárgy jelölő mód",1820/2-118,926);

    		g2D.setColor(Color.RED);
    		g2D.drawString("A/D",1820/2-174,826);
    		g2D.drawString("Space",1820/2-174,846);
    		g2D.drawString("Q",1820/2-174,866);
    		g2D.drawString("W",1820/2-174,886);
    		g2D.drawString("S",1820/2-174,906);
    		g2D.drawString("E",1820/2-174,926);

    		g2D.setColor(Color.BLUE);
    		g2D.drawString("⮜/⮞",1820/2+136,828);
    		g2D.drawString("Enter",1820/2+120,846);
    		g2D.drawString("Shift",1820/2+120,866);
    		g2D.drawString("⮝",1820/2+158,886);
    		g2D.drawString("⮟",1820/2+158,906);
    		g2D.drawString("Ctrl",1820/2+132,926);
    	};
    };
    
    
	public GameFrame(ViewLabyrinth vl, PlayerController red, PlayerController blue){
		super("cheat_sheet");
		this.vl=vl;
		this.red=red;
		this.blue=blue;
		this.addKeyListener(red);
		this.addKeyListener(blue);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1820,980));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container =  new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				red.paint(g);
				blue.paint(g);
				for(ViewRoom i : viewRooms)
					i.paint(g);
				for(ViewEnvironmentalFactors i : viewEnvs)
					i.paint(g);
				for(ViewItem i : viewItems)
					i.paint(g);
				for(ViewCharacter i : viewCharacters)
					i.paint(g);
				instructions.paint(g);
				vl.paint(g);
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