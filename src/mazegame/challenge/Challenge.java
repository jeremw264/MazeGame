package mazegame.challenge;

import java.util.List;

import mazegame.Hint;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

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

	/**
	 * Renvoie si le challenge est réalisable.
	 * @param characters Liste des personnages dans le jeu.
	 * @param items Liste des objets dans le jeu.
	 * @return true si le challenge est réalisable, false sinon.
	 */
	public abstract boolean isPossible(List<Character> characters,List<Item> items);
	
	public abstract Hint getHint();
}
