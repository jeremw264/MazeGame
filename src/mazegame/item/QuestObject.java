package mazegame.item;

import mazegame.character.Character;

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
		super(0, false, false);
	}

	// Cette objet n'est pas utilisable
	public void use(Character character) {
		return ;
	}

	/**
	 * Renvoie une représentation de l'objet sous forme de chaine de caractère.
	 */

	public String toString() {
		return "objet de quete";
	}

}
