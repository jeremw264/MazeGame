package mazegame.challenge;

import mazegame.character.Player;

/**
 * Challenge : Attendre le nombre de tours passé en paramètre dans le
 * constructeur.
 */
public class WaitRound extends Challenge {

	// Le nombre de tour attendu.
	private int nbOfRounds;
	
	// Le nombre de tour à attendre
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
