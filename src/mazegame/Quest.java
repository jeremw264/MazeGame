package mazegame;

import java.util.List;

import mazegame.challenge.Challenge;

public class Quest {
	private List<Challenge> listofChallenges;

	public Quest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Renvoie True si la quete est terminé, False sinon
	 * 
	 * @return True si la quete est terminé, False sinon
	 */
	public boolean isComplete() {
		for (Challenge challenge : listofChallenges) {
			if (!challenge.isFinish()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Initialise les challenges donc créé est ajoute des challenges à la liste des
	 * challenges
	 */
	private void initChallenge() {
		// Ajouter les challenge souhaité
	}
}
