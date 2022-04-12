package mazegame.action;

import java.util.LinkedList;
import java.util.List;

import mazegame.Direction;
import mazegame.Game;
import mazegame.character.Character;

public class LookAround extends Action {

	@Override
	public void run(Character character) {

		final int currentX = character.getX();
		final int currentY = character.getY();
		
		List<String> directionNameList = new LinkedList<String>();

		for (Direction direction : character.getAccessibleDirections()) {
			directionNameList.add(direction.toString());
		}

		Game.DISPLAYER.displayMsg("Vous êtes sur la case ("+currentX+","+currentY+")");
		Game.DISPLAYER.displayChoise("Les directions accessible sont : ",directionNameList);

	}

}
