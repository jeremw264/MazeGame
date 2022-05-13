package mazegame;

import java.util.List;

import mazegame.action.LookAround;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.ConsoleInput;
import mazegame.utils.Displayer;
import mazegame.utils.Input;

/**
 * Classe Game qui va représenter le jeu.
 */
public class Game {

	/**
	 * Objet qui est responsable des affichages du jeu.
	 */
	public static final Displayer DISPLAYER = new ConsoleDisplayer();

	/**
	 * Objet qui est responsable des entrées utilisateur du jeu.
	 */
	public static final Input INPUT = new ConsoleInput();

	// La carte du jeu.
	private Map map;

	// La liste des personnages présents dans le jeu.
	private List<Character> listOfCharacters;

	// La quête à accomplir dans jeu.
	private Quest quest;

	// Le joueur que contrôle l'utilisateur.
	private Player player;

	/**
	 * Constructeur de l'objet Game
	 *
	 * @param map        La carte du jeu.
	 * @param characters La liste des joueurs dans le jeu.
	 * @param player     Le joueur que contrôle l'utilisateur.
	 * @param quest      La quête a accomplir pour finir le jeu.
	 *
	 * @see Ce contructeur est utilisé par le GameBuilder.
	 */
	public Game(Map map, List<Character> characters, Player player, Quest quest) {
		this.map = map;
		this.quest = quest;
		this.player = player;
		this.listOfCharacters = characters;
	}

	/**
	 * Démarre le jeu.
	 */
	public void run() {

		boolean gameState = true;

		Game.DISPLAYER.displayStartGame();

		new LookAround().run(this.player);

		while (gameState && !this.quest.isComplete()) {

			for (Character character : listOfCharacters) {
				State status = character.getAction().run(character);

				if (!(status == State.Ok)) {
					gameState = false;
					break;
				}
			}
		}

		Game.DISPLAYER.displayEndGame();
		this.closeGame();
	}

	/**
	 * Instruction a exécuter à la fin du jeu.
	 */
	private void closeGame() {
		Game.INPUT.closeInput();
	}

}
