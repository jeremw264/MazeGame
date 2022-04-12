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
	public void displayStateGame(Game game) {

	}


	@Override
	public void displayHelp(List<String> commmandList) {

		this.displayMsg("Les actions disponible sont :");

		for (String string : commmandList) {
			this.displayMsg(" - " + string);
		}

		this.displayMsg("\n Signification des cases : ");
		this.displayMsg(" - P = vous (Le player) ");
		this.displayMsg(" - # = Les cases non visité\n");

	}

	@Override
	public void displayCommand(List<String> choiseList) {
		for (String string : choiseList) {
			this.displayMsg(" - " + string);
		}

	}

	@Override
	public void displayEndGame() {
		this.displayMsg("Fin de jeu ...\nMerci d'avoir jouer :)");

	}

}
