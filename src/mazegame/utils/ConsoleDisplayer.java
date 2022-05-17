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
	@Override
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

		// Source : https://patorjk.com/software/taag/#p=display&f=Ghost&t=Fin%20de%20Jeu

		this.displayMsg("                       .-') _        _ .-') _     ('-.                    ('-.               \n"
				+ "                      ( OO ) )      ( (  OO) )  _(  OO)                 _(  OO)              \n"
				+ "   ,------.,-.-') ,--./ ,--,'        \\     .'_ (,------.           ,--.(,------. ,--. ,--.   \n"
				+ "('-| _.---'|  |OO)|   \\ |  |\\        ,`'--..._) |  .---'       .-')| ,| |  .---' |  | |  |   \n"
				+ "(OO|(_\\    |  |  \\|    \\|  | )       |  |  \\  ' |  |          ( OO |(_| |  |     |  | | .-') \n"
				+ "/  |  '--. |  |(_/|  .     |/        |  |   ' |(|  '--.       | `-'|  |(|  '--.  |  |_|( OO )\n"
				+ "\\_)|  .--',|  |_.'|  |\\    |         |  |   / : |  .--'       ,--. |  | |  .--'  |  | | `-' /\n"
				+ "  \\|  |_)(_|  |   |  | \\   |         |  '--'  / |  `---.      |  '-'  / |  `---.('  '-'(_.-' \n"
				+ "   `--'    `--'   `--'  `--'         `-------'  `------'       `-----'  `------'  `-----'    ");
		this.displayMsg("Merci d'avoir jouer :)");
		this.displayMsg("\nDéveloppé par : \n");
		this.displayMsg("\t - Timothé Vanoverberghe");
		this.displayMsg("\t - Woirhaye Jérémy");
		this.displayMsg("\t - Franck Beyaert");

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

	/**
	 * Affichage du début de partie
	 */
	@Override
	public void displayStartGame() {

		// Source : https://patorjk.com/software/taag/#p=display&f=Ghost&t=The%20MazeGame

		Game.DISPLAYER.displayMsg(" .-') _    ('-. .-.   ('-.         _   .-')      ('-.       .-') _   ('-.                ('-.     _   .-')       ('-.   \n"
				+ "(  OO) )  ( OO )  / _(  OO)       ( '.( OO )_   ( OO ).-.  (  OO) )_(  OO)              ( OO ).-.( '.( OO )_   _(  OO)  \n"
				+ "/     '._ ,--. ,--.(,------.       ,--.   ,--.) / . --. /,(_)----.(,------. ,----.      / . --. / ,--.   ,--.)(,------. \n"
				+ "|'--...__)|  | |  | |  .---'       |   `.'   |  | \\-.  \\ |       | |  .---''  .-./-')   | \\-.  \\  |   `.'   |  |  .---' \n"
				+ "'--.  .--'|   .|  | |  |           |         |.-'-'  |  |'--.   /  |  |    |  |_( O- ).-'-'  |  | |         |  |  |     \n"
				+ "   |  |   |       |(|  '--.        |  |'.'|  | \\| |_.'  |(_/   /  (|  '--. |  | .--, \\ \\| |_.'  | |  |'.'|  | (|  '--.  \n"
				+ "   |  |   |  .-.  | |  .--'        |  |   |  |  |  .-.  | /   /___ |  .--'(|  | '. (_/  |  .-.  | |  |   |  |  |  .--'  \n"
				+ "   |  |   |  | |  | |  `---.       |  |   |  |  |  | |  ||        ||  `---.|  '--'  |   |  | |  | |  |   |  |  |  `---. \n"
				+ "   `--'   `--' `--' `------'       `--'   `--'  `--' `--'`--------'`------' `------'    `--' `--' `--'   `--'  `------' ");

	}

	/**
	 * Affichage de l'indice
	 */
	@Override
	public void displayHint(String hintMessage) {
		int sizeMessage = hintMessage.length();

		for (int i = 0; i < sizeMessage + 3; i++) {
			 System.out.print("#");
		}
		System.out.print("#\n");

		this.displayMsg("# "+hintMessage+" #");
		for (int i = 0; i < sizeMessage + 3; i++) {
			System.out.print("#");
		}
		System.out.print("#\n");


	}

}
