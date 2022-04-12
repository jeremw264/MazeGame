package mazegame;

import java.util.List;

import mazegame.challenge.Challenge;

public class Quest {
	private List<Challenge> listOfChallenges;

	public Quest(List<Challenge> listOfChallenges) {
		this.listOfChallenges = listOfChallenges;
	}

	/**
	 * Renvoie True si la quete est terminé, False sinon
	 * 
	 * @return True si la quete est terminé, False sinon
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
