package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.utils.UserInteration;

/**
 * Action de déplacement.
 */
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
		List<String> keysList = new LinkedList<>(directionChoiseMap.keySet());

		final Map<String, Object> responceMap = UserInteration.getChoise("Dans quelle direction voulez-vous aller",
				keysList, true);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		final Cell nextCell = player.computeNextCell(directionChoiseMap.get(responceMap.get("choice")));

		player.move(nextCell);
		
		Game.DISPLAYER.displayMsg("Tu es sur la "+player.getCell());

		return State.Ok;
	}

	/**
	 * Renvoie une java.util.Map avec en clé les description string des direction et
	 * en valeur les directions.
	 *
	 * @param directions Liste des directions.
	 * @return La java.util.map
	 */
	private Map<String, Direction> extractMap(List<Direction> directions) {
		Map<String, Direction> directionChoiseMap = new HashMap<>();

		for (Direction direction : directions) {
			directionChoiseMap.put(direction.toString().toLowerCase(), direction);
		}

		return directionChoiseMap;
	}

}
