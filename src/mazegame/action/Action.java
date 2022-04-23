package mazegame.action;

import mazegame.character.Character;

/**
 * Classe Action
 */
public abstract class Action {

	/**
	 * Execute l'action.
	 * 
	 * @param character Le personnage qui execute l'action
	 */
	public abstract boolean run(Character character);
}
