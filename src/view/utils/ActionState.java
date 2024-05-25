package view.utils;

/**
 * Azt tárolja, hogy éppen mik közül választ a felhasználó.
 */
public enum ActionState {
	
	/**
	 * Nem választ éppen, ez a default.
	 */
	Empty,
	
	/**
	 * Az inventory-ban választ éppen tárgyat, amit használni szeretne.
	 */
	InInventory,
	
	/**
	 * A szobák közül választ éppen, hogy melyikbe menjen.
	 */
	RoomPicker,
	
	/**
	 * A szobában lévő tárgyak közül választ éppen, hogy melyiket szedje fel.
	 */
	ItemPicker
}