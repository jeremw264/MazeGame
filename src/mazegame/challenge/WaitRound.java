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
	/**
	 * Méthode renvoyant un booléen pour savoir si le challenge est complété
	 */
	public boolean isFinish(Player player) {
		if (this.nbOfRounds < this.nbOfRoundsWaiting) {
			this.nbOfRounds++;
			return false;
		} else {
			return true;
		}
	}

	@Override
	/**
	 * Méthode renvoyant un booléen pour savoir si le challenge est possible
	 */
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return true;
	}

	@Override
	/**
	 * Renvoies l'indice lié au challenge
	 */
	public Hint getHint() {
		return new Hint(this.nbOfRounds);
	}
	
	@Override
	/**
	 * Renvoies une représentation du challenge en chaîne de caractères
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "Défis : Attendre "+this.nbOfRoundsWaiting+" tours";
	}

}
