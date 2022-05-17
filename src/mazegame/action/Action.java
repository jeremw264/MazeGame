package mazegame.action;

import mazegame.State;
import mazegame.character.Character;

/**
 * Classe Action
 */
public abstract class Action {

	/**
	 * Exécute l'action.
	 *
	 * @param character Le personnage qui exécute l'action
	 */
	public abstract State run(Character character);
}
