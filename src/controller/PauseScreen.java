package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

import javax.swing.JComponent;

import View.Utils.GameFrame;
import View.Utils.ImageReader;

public class PauseScreen extends JComponent implements KeyListener{

	/**
	 * Aktív-e a KeyListener.
	 */
	private boolean isActive = true;
	
	/**
	 * Meg van-e állítva a játék.
	 */
	private boolean isPaused = false;
	
	/**
	 * A játék megállításának képe
	 */
	private Image pauseScreen = ImageReader.loadImage(ImageReader.path+"pause_screen.png");
	
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Kirajzolja a pauseScreen-t.
	 * @param e melyik billentyű lett lenyomva
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(!isActive)
			return;
		if(isPaused) {
			isPaused = false;
			GameFrame.resume();
		}else {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				isPaused = true;
				GameFrame.pause();
			}
		}
	}

	/**
	 * Inaktívvá teszi a pauseScreen-t
	 */
	public void gameEnded() {
		isActive=false;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * Kirajzolja a pauseScreen-t.
	 */
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if(isActive)
	    	g.drawImage(pauseScreen, 0, 0, null);
	}

}
