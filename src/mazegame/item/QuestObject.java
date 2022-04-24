package mazegame.item;

import mazegame.Game;

/**
 * Classe QuestObject.
 * 
 * Objet l'objet de la quÃªte.
 */
public class QuestObject extends Item {

	/**
	 * Constructeur de l'objet QuestObject.
	 */
	public QuestObject() {
		this.value = 0;
	}
	
	
	//Methode use quand meme affichée au cas ou on veuille passer l'objet en usable
	public void use() {
		Game.DISPLAYER.displayMsg("Cet objet n'est pas vendable, Il vous sera utile dans votre quête");
	}
	
	
	/**
	 * Renvoie une reprÃ©sentation de l'objet sous forme de chaine de caractÃ¨re.
	 */
	
	public String toString() {
		return "objet de quête";
	}
}
