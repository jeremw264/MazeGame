package mazegame.challenge;

import mazegame.character.Player;

public class CaseEightTree extends Challenge {

	public CaseEightTree(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFinish() {
		int finalX = 8;
		int finalY = 3;
			
		return (this.player.getCell().getX() == finalX) && (this.player.getCell().getY() == finalY);
	}

}
