package mazegame.character.npc;

import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;
import mazegame.character.Npc;

public class Samaritan extends Npc {

	public Samaritan(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Action getAction() {
		return new DoNothing();
	}

}