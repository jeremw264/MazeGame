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
import mazegame.utils.UserInteraction;

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
		Map<String, Direction> directionChoiceMap = this.extractMap(character.getAccessibleDirections());
		List<String> keysList = new LinkedList<>(directionChoiceMap.keySet());

		final Map<String, Object> responseMap = UserInteraction.getChoise("Dans quelle direction voulez-vous aller",
				keysList, true);

		if (responseMap.get("STATE") != State.Ok) {
			return (State) responseMap.get("STATE");
		}

		final Cell nextCell = player.computeNextCell(directionChoiceMap.get(responseMap.get("choice")));

		player.move(nextCell);

		return State.Ok;
	}

	/**
	 * Renvoie une java.util.Map avec en clé les description string des directions et
	 * en valeur les directions.
	 *
	 * @param directions Liste des directions.
	 * @return La java.util.map
	 */
	private Map<String, Direction> extractMap(List<Direction> directions) {
		Map<String, Direction> directionChoiceMap = new HashMap<>();

		for (Direction direction : directions) {
			directionChoiceMap.put(direction.toString().toLowerCase(), direction);
		}

		return directionChoiceMap;
	}

}
