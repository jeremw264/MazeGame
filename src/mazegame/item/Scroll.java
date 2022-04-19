package mazegame.item;

/**
 * Classe Scroll.
 * 
 * Objet Parchemin.
 */
public class Scroll extends Item {

	/**
	 * Constructeur de l'objet Scroll.
	 */
	
	
	public Scroll(String hint) {
		this.value = 0;
		this.sellability = false;
		this.hint = hint;
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		return "Cet objet n'est pas vendable";
	}
}
