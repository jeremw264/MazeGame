package mazegame.item;

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
		this.sellability = false;
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */
	public String toString() {
		return "Cet objet n'est pas vendable";
	}
}
