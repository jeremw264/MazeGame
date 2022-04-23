package mazegame.action;

import mazegame.character.Character;

public class Move extends Action {

	@Override
	public boolean run(Character character) {
		character.move();
		
		return true;
	}

}
