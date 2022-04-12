package mazegame.challenge;

import mazegame.character.Player;

public class WaitRound extends Challenge {

	private int nbOfRounds;
	private static final int nbOfRoundsWaiting = 5;
	
	public WaitRound(Player player) {
		super(player);
		this.nbOfRounds = 0;
	}

	@Override
	public boolean isFinish() {
		if (this.nbOfRounds < WaitRound.nbOfRoundsWaiting) {
			this.nbOfRounds++;
			return false;
		} else {
			return true;	
		}
	}

}
