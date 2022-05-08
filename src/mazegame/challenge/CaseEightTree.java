package mazegame.challenge;

import java.util.List;

import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

/**
 * Challenge: Acceder à la case (8,3)
 */
public class CaseEightTree extends Challenge {

	@Override
	public boolean isFinish(Player player) {
		int finalX = 8;
		int finalY = 3;

		return (player.getCell().getX() == finalX) && (player.getCell().getY() == finalY);
	}

	@Override
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return characters.get(0).getMap().getCell(8, 3) != null	;
	}

}
