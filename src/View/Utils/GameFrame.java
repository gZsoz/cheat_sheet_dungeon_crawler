package View.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import Controller.PlayerController;
import View.ViewCharacter.ViewCharacter;
import View.ViewEnvironmentalFactor.ViewEnvironmentalFactors;
import View.ViewItem.ViewItem;
import View.ViewMap.ViewLabyrinth;
import View.ViewMap.ViewRoom;

/**
 * Felelősség: Létrehoz egy fix méretű JPanelt és erre fog majd rajzolni a többi osztály a Graphics segítségével.
 * A javax.swing.JFrame osztályból származik le.
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	/**
	 * A panel, amire minden komponenst helyezünk.
	 */
	public static JPanel mainPanel;
	
	/**
	 * A labirintus nézete.
	 */
	public static ViewLabyrinth vl;
	
	/**
	 * A tárgyak nézetei.
	 */
	public static ArrayList<ViewItem> viewItems = new ArrayList<>();
	
	/**
	 * A környezeti tényezők nézetei.
	 */
	public static ArrayList<ViewEnvironmentalFactors> viewEnvs = new ArrayList<>();
	
	/**
	 * A karakterek nézetei.
	 */
	public static ArrayList<ViewCharacter> viewCharacters = new ArrayList<>();
	
	/**
	 * A szobák nézetei.
	 */
	public static ArrayList<ViewRoom> viewRooms = new ArrayList<>();
	
	/**
	 * A két játékos vezérlői.
	 */
	public static PlayerController red, blue;
	
	/**
	 * Az instrukciókat kiíró komponens képe.
	 */
	private static Image instructionsBackground = ImageReader.loadImage(ImageReader.path+"Backgrounds/inventory_background.png");
	
	/**
	 * A játékbeli instrukciókat kiíró komponens.
	 */
	public static JComponent instructions = new JComponent() {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2D = (Graphics2D)g;
			
			// mode textbox
			g2D.setColor(new Color(221,221,221));
			g2D.drawImage(instructionsBackground, 1820/2 - 240 + 40, 800, 400, 200,null);
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
	
	/**
	 * Konstruktor a játékablak létrehozásához.
	 * @param vl labirintus nézete
	 * @param red a piros játékos vezérlője
	 * @param blue a kék játékos vezérlője
	 */
	public GameFrame(ViewLabyrinth vl, PlayerController red, PlayerController blue){
		super("A logarléc");
		GameFrame.vl=vl;
		GameFrame.red=red;
		GameFrame.blue=blue;
		this.addKeyListener(red);
		this.addKeyListener(blue);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1820,980));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ImageReader.loadImage(ImageReader.path+"Items/"+"sliderule.png"));
	
		mainPanel =  new JPanel() {
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
		mainPanel.setBackground(Color.black);
		LayoutManager overlay = new OverlayLayout(mainPanel);
		mainPanel.setLayout(overlay);
		mainPanel.add(red);
		mainPanel.add(blue);
		mainPanel.add(vl);
		mainPanel.add(red.getViewStudent());
		mainPanel.add(blue.getViewStudent());
	
		this.add(mainPanel, BorderLayout.CENTER);
	
		this.pack();
		this.setLocationRelativeTo(null);
	}
}