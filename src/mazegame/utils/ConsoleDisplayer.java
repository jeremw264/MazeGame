package mazegame.utils;

import java.util.List;

import mazegame.Game;
import mazegame.Map;

/**
 * Classe Responsable des affichage en console.
 */
public class ConsoleDisplayer implements Displayer {

	/**
	 * Affiche un message en console.
	 * 
	 * @param message Le message à afficher
	 */
	public void displayMsg(String message) {
		System.out.println(message);
	}

	/**
	 * Affiche un message d'erreur en console.
	 * 
	 * @param message Le message à afficher
	 */
	@Override
	public void displayError(String message) {
		System.err.println(message);

	}

	/**
	 * Affiche la carte en console.
	 * 
	 * @param map La carte à afficher
	 */
	@Override
	public void displayMap(Map map) {
		this.displayMsg(map.toString());
	}

	/**
	 * Affiche la fin du jeu en console.
	 */
	@Override
	public void displayEndGame() {
		this.displayMsg("Fin de jeu ...\nMerci d'avoir jouer :)");

	}

	/**
	 * Affiche une liste de choix en console.
	 * 
	 * @param firstSentece  La premiere phrase à afficher.
	 * @param listOfChoises La liste des choix possible.
	 */
	@Override
	public void displayChoise(String firstSentence, List<String> listOfChoises) {

		this.displayMsg(firstSentence);

		for (String choise : listOfChoises) {
			this.displayMsg("- " + choise);
		}

	}

	/**
	 * Affiche l'aide d'utilisation en console.
	 * 
	 * @param commandsList La liste des commandes possible.
	 */
	@Override
	public void displayHelp(List<String> keysList) {
		this.displayMsg("Voici les actions possibles : ");

		for (String choise : keysList) {
			this.displayMsg("- " + choise);
		}
		Game.DISPLAYER.displayMsg("--------------------------------------------------");

	}

}
