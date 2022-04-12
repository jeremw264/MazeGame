package mazegame.challenge;

import mazegame.character.Player;

public class WaitThreeRounds extends Challenge {

	private int nbOfRound;

	public WaitThreeRounds(Player player) {
		super(player);
		this.nbOfRound = 0;
	}

	@Override
	public boolean isFinish() {
		if (this.nbOfRound != 3) {
			this.nbOfRound++;
			return false;
		} else {

			return true;

		}
	}

}
