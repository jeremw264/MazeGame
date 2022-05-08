package mazegame.utils;

import java.util.List;

import mazegame.Map;

/**
 * Interface pour les affichages.
 */
public interface Displayer {

	/**
	 * Affiche un message.
	 * 
	 * @param message Le message à afficher.
	 */
	public void displayMsg(String message);

	/**
	 * Affiche un message d'erreur
	 * 
	 * @param message Le message à afficher.
	 */
	public void displayError(String message);

	/**
	 * Affiche la carte.
	 * 
	 * @param map La carte à afficher
	 */
	public void displayMap(Map map);

	/**
	 * Affiche une liste de choix.
	 * 
	 * @param firstSentece La premiere phrase à afficher.
	 * @param listOfChoises La liste des choix possible.
	 */
	public void displayChoise(String firstSentece, List<String> listOfChoises);

	/**
	 * Affiche la fin du jeu.
	 */
	public void displayEndGame();
	
	/**
	 * Affiche du contenu au début du jeu.
	 */
	public void displayStartGame();

	/**
	 * Affiche l'aide d'utilisation.
	 * 
	 * @param commandsList La liste des commandes possible.
	 */
	public void displayHelp(List<String> commandsList);
	
	public void displayHint(String hintMessage);
}
