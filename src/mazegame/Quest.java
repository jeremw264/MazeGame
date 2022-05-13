package mazegame;

import java.util.List;

import mazegame.challenge.Challenge;
import mazegame.character.Player;

/**
 * Classe Quest
 */
public class Quest {

	// La liste des défis afin de finir la quête.
	private List<Challenge> listOfChallenges;

	private Player player;

	/**
	 * Constructeur de l'objet Quest
	 *
	 * @param listOfChallenges La liste des défis afin de finir la quête.
	 */
	public Quest(Player player,List<Challenge> listOfChallenges) {
		this.player = player;
		this.listOfChallenges = listOfChallenges;
	}

	/**
	 * Renvoie true si la quête est terminée, False sinon
	 *
	 * @return true si la quête est terminée, False sinon
	 */
	public boolean isComplete() {

		boolean isFinish = true;

		for (Challenge challenge : listOfChallenges) {
			if (!challenge.isCheck(this.player)) {
				isFinish = false;
			}
		}

		return isFinish;
	}

}
