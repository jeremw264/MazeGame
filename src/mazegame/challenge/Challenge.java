package mazegame.challenge;

import mazegame.character.Player;

/**
 * Classe Challenge.
 */
public abstract class Challenge {

	// Sauvegarde l'état du challenge si il a été validé.
	private boolean finished;

	/**
	 * Constructeur de l'objet Challenge
	 * 
	 * @param player Le joueur courant.
	 */
	public Challenge() {
		this.finished = false;
	}

	/**
	 * Renvoie si le challenge a déja été fini
	 * 
	 * @return True si le challenge à déja été fini, False sinon.
	 */
	public boolean isCheck(Player player) {
		if (this.finished) {
			return true;
		} else {
			this.finished = this.isFinish(player);
			return this.finished;
		}
	}

	/**
	 * Renvoie si le challenge est fini
	 * 
	 * @return True si le challenge est fini, False dans le cas contraire.
	 */
	public abstract boolean isFinish(Player player);
}
