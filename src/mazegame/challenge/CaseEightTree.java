package mazegame.challenge;

import mazegame.character.Player;

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

}
