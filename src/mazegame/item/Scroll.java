package mazegame.item;

import mazegame.Game;

/**
 * Classe Scroll.
 * 
 * Objet Parchemin.
 */
public class Scroll extends Item {

	private String hint;

	/**
	 * Constructeur de l'objet Scroll.
	 */
	public Scroll(String hint) {
		super(0, false, true);
		this.hint = hint;
	}

	public void use() {
		Game.DISPLAYER.displayMsg(this.hint);
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		return "parchemin";
	}
}
