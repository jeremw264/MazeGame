package mazegame.item;

import mazegame.Hint;
import mazegame.character.Character;

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

	/**
	 * Cet objet n'est pas utilisable
	 */
	@Override
	public void use(Character character) {
		this.hint.displayHint(character);
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	@Override
	public String toString() {
		return "parchemin";
	}
}
