package mazegame.utils;

import java.util.List;

import mazegame.Game;
import mazegame.Map;

/**
 * Classe Responsable des affichage en console.
 */
public class ConsoleDisplayer implements Displayer {

	/**
	 * Affiche un message en console
	 * 
	 * @param message
	 */
	public void displayMsg(String message) {
		System.out.println(message);
	}

	@Override
	public void displayError(String message) {
		System.err.println(message);

	}

	public void displayGameTitle() {
		String title = "                                                                     \n"
				+ ",--.   ,--.                       ,----.                             \n"
				+ "|   `.'   | ,--,--.,-----. ,---. '  .-./    ,--,--.,--,--,--. ,---.  \n"
				+ "|  |'.'|  |' ,-.  |`-.  / | .-. :|  | .---.' ,-.  ||        || .-. : \n"
				+ "|  |   |  |\\ '-'  | /  `-.\\   --.'  '--'  |\\ '-'  ||  |  |  |\\   --. \n"
				+ "`--'   `--' `--`--'`-----' `----' `------'  `--`--'`--`--`--' `----' \n"
				+ "                                                                     ";
		this.displayMsg(title);
	}

	@Override
	public void displayMap(Map map) {
		System.out.println(map);
	}

	@Override
	public void displayEndGame() {
		this.displayMsg("Fin de jeu ...\nMerci d'avoir jouer :)");

	}

	@Override
	public void displayChoise(String firstSentence, List<String> listOfChoises) {

		this.displayMsg(firstSentence);

		for (String choise : listOfChoises) {
			this.displayMsg("- " + choise);
		}

	}

	@Override
	public void displayHelp(List<String> keysList) {
		this.displayMsg("Voici les actions possibles : ");

		for (String choise : keysList) {
			this.displayMsg("- " + choise);
		}
		Game.DISPLAYER.displayMsg("--------------------------------------------------");

	}

}
