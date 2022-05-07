package mazegame.action;

import java.util.LinkedList;
import java.util.List;

import mazegame.Direction;
import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Player;

/**
 * Classe LookAround
 * 
 * Action pour regarder
 */
public class LookAround extends Action {

	/**
	 * Affiche une description de ce que le joueur peut voir.
	 */
	@Override
	public State run(Character character) {
		
		if (!(character instanceof Player)) {
			Game.DISPLAYER.displayError("Cette action doit être executé par un Player");
			return State.Exit;
		}
		
		Game.DISPLAYER.displayMap(character.getMap());

		final int currentX = character.getX();
		final int currentY = character.getY();
		
		List<String> directionNameList = new LinkedList<String>();

		for (Direction direction : character.getAccessibleDirections()) {
			directionNameList.add(direction.toString());
		}
		
		System.err.println(character.getCoins());

		Game.DISPLAYER.displayMsg("Vous êtes sur la case ("+currentX+","+currentY+")");
		Game.DISPLAYER.displayMsg("Vous avez "+character.getCoins()+" or sur vous.");
		Game.DISPLAYER.displayChoise("Les directions accessible sont : ",directionNameList);

		return State.Ok;
		
	}

}
