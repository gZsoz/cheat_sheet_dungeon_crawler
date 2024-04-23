package Character;

import Items.Item;
import ProtoUtil.ProtoUtil;

/**
 * A Student osztály felelős a hallgatók inventory-jában lévő aktiválható tárgyak aktiválásáért.
 */
public class Student extends Character {
	
	/**
	 * A hallgató érinthetetlensége.
	 */
	 private boolean invincible;
	 
	 /**
	  * Érinthetetlenség lekérdezése.
	  * @return A hallgató érinthetetlenségi állapota.
	  */
	 public boolean getInvincible() {
		 ProtoUtil.printLog("getInvincible");
		 return invincible;
	 }
	 
	 /**
	  * Érinthetetlenség beállítása.
	  * @param state Ha érinthetetlen a karakter, akkor igaz, egyébként hamis.
	  */
	 public void setInvincible(boolean state) {
		 ProtoUtil.printLog("setInvincible");
		 invincible = state;
	 }
	 
	 /**
	  * A paraméterül kapott tárgy hatásának megkezdése.
	  * @param i A tárgy.
	  */
	 public void activate(Item i) {
		 ProtoUtil.printLog("activate");
		 i.use();
	 }
	 
	 /**
	  * Ugyanaz mint a Character-nek.
	  */
	 @Override
	 public void update() {
		 super.update();
	 }
}