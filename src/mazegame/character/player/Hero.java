package mazegame.character.player;

import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.PlayerChoise;
import mazegame.character.Player;

public class Hero extends Player {

	public Hero(int x, int y, Map map) {
		super(x, y, map);

	}

	@Override
	public Action getAction() {
		return new PlayerChoise();
	}

}
