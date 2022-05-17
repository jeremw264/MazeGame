package mazegame.action;

import mazegame.State;
import mazegame.character.Character;

/**
 * Classe DoNothing
 *
 * Action pour ne rien faire. :)
 */
public class DoNothing extends Action {

	/**
	 * Ne fait rien faire au personnage.
	 */
	@Override
	public State run(Character character) {
		return State.Ok;

	}

}
