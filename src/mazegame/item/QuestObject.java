package mazegame.item;

import mazegame.Game;

/**
 * Classe QuestObject.
 * 
 * Objet l'objet de la quête.
 */
public class QuestObject extends Item {

	/**
	 * Constructeur de l'objet QuestObject.
	 */
	public QuestObject() {
		this.value = 0;
	}
	
	
	//Methode use quand meme affiche au cas ou on veuille passer l'objet en usable
	public void use() {
		Game.DISPLAYER.displayMsg("Cet objet n'est pas vendable, Il vous sera utile dans votre quete");
	}
	
	
	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	
	public String toString() {
		return "objet de quete";
	}
}
