package mazegame.challenge;

import mazegame.character.Player;

public class CaseEightTree extends Challenge {

	@Override
	public boolean isFinish(Player player) {
		int finalX = 8;
		int finalY = 3;
		
		return (player.getCell().getX() == finalX) && (player.getCell().getY() == finalY);
	}

}
