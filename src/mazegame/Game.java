package mazegame;

import java.util.LinkedList;
import java.util.List;

import mazegame.action.LookAround;
import mazegame.challenge.Challenge;
import mazegame.challenge.FinalCase;
import mazegame.challenge.WaitRound;
import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.generation.Kruskal;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.ConsoleInput;
import mazegame.utils.Displayer;
import mazegame.utils.Input;
import mazegame.character.Character;

/**
 * Classe Game qui va représenter le jeu.
 */
public class Game {

	public static final Displayer DISPLAYER = new ConsoleDisplayer();
	public static final Input INPUT = new ConsoleInput();

	private Map map;
	private List<Character> listOfCharacters;
	private Quest quest;

	private Player player;

	/**
	 * Constructeur de l'objet Game
	 * 
	 * @param width  La largueur du jeu.
	 * @param height La hauteur du jeu.
	 */
	public Game(int width, int height) {
		this.map = new Kruskal().generation(width, height);

		this.initCharacter();
		this.initQuest();

	}

	private void initCharacter() {
		this.listOfCharacters = new LinkedList<Character>();
		Player player = new Hero(0, 0, this.map);

		listOfCharacters.add(player);

		this.player = player;
	}

	private void initQuest() {
		List<Challenge> listOfChallenges = new LinkedList<Challenge>();
		/*
		 * Ajouter les Challenges
		 */
		listOfChallenges.add(new WaitRound(this.player, 1));
		listOfChallenges.add(new FinalCase(player));

		this.quest = new Quest(listOfChallenges);

	}

	public void run() {

		new LookAround().run(this.player);

		while (!this.quest.isComplete()) {

			Game.DISPLAYER.displayMsg("--------------------------------------------------");

			for (Character character : listOfCharacters) {
				character.getAction().run(character);
			}
		}

		Game.DISPLAYER.displayEndGame();
		this.closeGame();
	}

	private void closeGame() {
		Game.INPUT.closeInput();
	}

}
