package mazegame.item;

import mazegame.Game;

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
		this.value = (int) Math.floor(Math.random() * 1000);
		this.sellability = true;
	}

	
	//Methode use quand meme affiche au cas ou on veuille passer l'objet en usable
	public void use() {
		if (!usability) {
			return;
		}
		if (this.value <= 20) {
			Game.DISPLAYER.displayMsg("Cette émeraude pèse " + this.value / 4 + " et vaut " + this.value);
		} else if (this.value <= 50) {
			Game.DISPLAYER.displayMsg("Ce saphir pèse " + this.value / 4 + " et vaut " + this.value);
		} else if (this.value <= 100) {
			Game.DISPLAYER.displayMsg("Ce rubis pèse " + this.value / 4 + " et vaut " + this.value);
		} else if (this.value <= 250) {
			Game.DISPLAYER.displayMsg("Cette améthyste pèse " + this.value / 4 + " et vaut " + this.value);
		} else if (this.value <= 500) {
			Game.DISPLAYER.displayMsg("Cette pépite d'or pèse " + this.value + " et vaut donc " + this.value);
		} else {
			Game.DISPLAYER.displayMsg("Ce sac de joyaux contient" + Math.floor(this.value / 15) + " et vaut " + this.value);
		}
	}
	
	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		if (this.value <= 20) {
			return "emeraude";
		} else if (this.value <= 50) {
			return "saphir";
		} else if (this.value <= 100) {
			return "rubis";
		} else if (this.value <= 250) {
			return "amethyste";
		} else if (this.value <= 500) {
			return "pepite d'or";
		} else {
			return "sac de joyaux";
		}
	}

}
