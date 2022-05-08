package mazegame.item;

import mazegame.Hint;

/**
 * Classe Scroll.
 * 
 * Objet Parchemin.
 */
public class Scroll extends Item {

	private Hint hint;

	/**
	 * Constructeur de l'objet Scroll.
	 */
	public Scroll(Hint hint) {
		super(0, false, true);
		this.hint = hint;
	}

	public String use() {
		return this.hint.getGoodHint();
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		return "parchemin";
	}
}
