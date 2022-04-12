package mazegame.challenge;

import mazegame.character.Player;

public abstract class Challenge {

	// Sauvegarde l'état du challenge si il a été validé
	private boolean finished;
	
	private Player player;

	public Challenge(Player player) {
		this.player = player;
		this.finished = false;
	}

	/**
	 * Renvoie si le challenge a déja été fini
	 * 
	 * @return True si le challenge à déja été fini, False sinon.
	 */
	public boolean isCheck() {
		if (this.finished) {
			return true;
		} else {
			this.finished = this.isFinish();
			return this.finished;
		}
	}

	/**
	 * Renvoie si le challenge est fini
	 * 
	 * @return True si le challenge est fini, False dans le cas contraire.
	 */
	public abstract boolean isFinish();
}
