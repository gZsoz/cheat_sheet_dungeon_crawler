package View.Utils;

import java.awt.Graphics;

/**
 * Felelősség: Létrehoz egy fix méretű JPanelt és erre fog majd rajzolni a többi osztály a graphics segítségével.
 * Ősosztálya: javax.swing.JFrame
 */
@SuppressWarnings("serial")
public class GameFrame extends javax.swing.JFrame {
	/**
	 * A grafika, amire mindent kirajzolunk.
	 */
	public Graphics graphics;
}