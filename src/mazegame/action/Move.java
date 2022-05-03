package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.utils.UserInteration;

public class Move extends Action {

	@Override
	public State run(Character character) {
		if (character instanceof Npc) {
			Npc npc = (Npc) character;
			npc.move();

			return State.Ok;
		}

		Player player = (Player) character;
		Map<String, Direction> directionChoiseMap = this.extractMap(character.getAccessibleDirections());
		List<String> keysList = new LinkedList<String>(directionChoiseMap.keySet());
		
		final Map<String, Object> responceMap = UserInteration.getChoise("Dans quelle direction voulez-vous aller", keysList,true);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		final Cell nextCell = player.computeNextCell(directionChoiseMap.get((String) responceMap.get("choice")));

		player.move(nextCell);

		return State.Ok;
	}

	private Map<String, Direction> extractMap(List<Direction> directions) {
		Map<String, Direction> directionChoiseMap = new HashMap<>();

		for (Direction direction : directions) {
			directionChoiseMap.put(direction.toString().toLowerCase(), direction);
		}

		return directionChoiseMap;
	}

}
