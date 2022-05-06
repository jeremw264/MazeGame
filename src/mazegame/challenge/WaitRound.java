package mazegame.challenge;

import mazegame.character.Player;

public class WaitRound extends Challenge {

	private int nbOfRounds;
	private final int nbOfRoundsWaiting;

	public WaitRound(int nbOfRoundsWaiting) {
		this.nbOfRounds = 0;
		this.nbOfRoundsWaiting = nbOfRoundsWaiting;
	}

	@Override
	public boolean isFinish(Player player) {
		if (this.nbOfRounds < this.nbOfRoundsWaiting) {
			this.nbOfRounds++;
			return false;
		} else {
			return true;
		}
	}

}
