package mazegame.action;

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
	public void run(Character character) {
		return;

	}

}
