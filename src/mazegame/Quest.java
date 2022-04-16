package mazegame;

import java.util.List;

import mazegame.challenge.Challenge;

/**
 * Classe Quest
 */
public class Quest {

	// La liste des défis afin de finir la quête.
	private List<Challenge> listOfChallenges;

	/**
	 * Constructeur de l'objet Quest
	 * 
	 * @param listOfChallenges La liste des défis afin de finir la quête.
	 */
	public Quest(List<Challenge> listOfChallenges) {
		this.listOfChallenges = listOfChallenges;
	}

	/**
	 * Renvoie true si la quete est terminé, False sinon
	 * 
	 * @return true si la quete est terminé, False sinon
	 */
	public boolean isComplete() {

		boolean isFinish = true;

		for (Challenge challenge : listOfChallenges) {
			if (!challenge.isCheck()) {
				isFinish = false;
			}
		}

		return isFinish;
	}

}
