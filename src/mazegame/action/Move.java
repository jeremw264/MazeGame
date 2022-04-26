package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;

public class Move extends Action {

	@Override
	public State run(Character character) {
		if (character instanceof Npc) {
			Npc npc = (Npc)character;
			npc.move();
			
			return State.Ok;
		}
		
		Player player = (Player)character;
		
		String choise;

		java.util.Map<String, Direction> directionChoiseMap = new HashMap<>();

		for (Direction direction : character.getAccessibleDirections()) {
			directionChoiseMap.put(direction.toString().toLowerCase(), direction);
		}

		List<String> keysList = new LinkedList<String>();
		keysList.add("None");
		keysList.addAll(directionChoiseMap.keySet());

		do {

			Game.DISPLAYER.displayChoise("Dans quelle direction voulez-vous aller", keysList);

			choise = Game.INPUT.getString().toLowerCase();

			if (choise.equals("none")) {
				return State.Cancel;
			}
			if (!directionChoiseMap.containsKey(choise)) {
				Game.DISPLAYER.displayError("Choix non valide\n");
			}

		} while (!directionChoiseMap.containsKey(choise));

		Cell nextCell = player.computeNextCell(directionChoiseMap.get(choise));
		
		player.move(nextCell);
		
		return State.Ok;
	}

}
