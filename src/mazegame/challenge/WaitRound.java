package mazegame.challenge;

import java.util.List;

import mazegame.Hint;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

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

	@Override
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return true;
	}

	@Override
	public Hint getHint() {
		// TODO Auto-generated method stub
		return null;
	}

}
