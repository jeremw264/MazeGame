package mazegame.item;

/**
 * Classe Jewel.
 *
 * Objet Joyau.
 */
public class Jewel extends Item {

	/**
	 * Constructeur de l'objet Jewel.
	 */
	public Jewel() {
		this.value = (int) Math.floor(Math.random() * 10000);
		this.sellability = true;
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		if (this.value <= 20) {
			return "Cette émeraude pèse " + this.value / 4 + " et vaut " + this.value;
		} else if (this.value <= 50) {
			return "Ce saphir pèse " + this.value / 4 + " et vaut " + this.value;
		} else if (this.value <= 100) {
			return "Ce rubis pèse " + this.value / 4 + " et vaut " + this.value;
		} else if (this.value <= 250) {
			return "Cette améthyste pèse " + this.value / 4 + " et vaut " + this.value;
		} else if (this.value <= 500) {
			return "Cette pépite d'or pèse " + this.value + " et vaut donc " + this.value;
		} else {
			return "Ce sac de joyaux " + this.value / 15 + " et vaut " + this.value;
		}
	}

}
