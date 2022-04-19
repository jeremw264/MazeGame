package mazegame.action;

import mazegame.character.Character;

public class Move extends Action {

	@Override
	public void run(Character character) {
		character.move();
	}

}
