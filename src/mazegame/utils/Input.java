package mazegame.utils;

/**
 * Interface Input.
 *
 * Interface à implémnter pour géré les entrées utilisateur.
 */
public interface Input {

	/**
	 * Renvoie la saisie sous forme de chaine de caractère.
	 */
	public String getString();

	/**
	 * Ferme l'entrée.
	 */
	public void closeInput();
}
