package mazegame.challenge;

import mazegame.character.Player;

public class WaitRound extends Challenge {

	private int nbOfRounds;
	private final int nbOfRoundsWaiting;

	public WaitRound(Player player, int nbOfRoundsWaiting) {
		super(player);
		this.nbOfRounds = 0;
		this.nbOfRoundsWaiting = nbOfRoundsWaiting;
	}

	@Override
	public boolean isFinish() {
		if (this.nbOfRounds < this.nbOfRoundsWaiting) {
			this.nbOfRounds++;
			return false;
		} else {
			return true;
		}
	}

}
