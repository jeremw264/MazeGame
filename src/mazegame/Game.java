package mazegame;

import java.util.LinkedList;
import java.util.List;

import mazegame.action.LookAround;
import mazegame.challenge.CaseEightTree;
import mazegame.challenge.Challenge;
import mazegame.challenge.WaitRound;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.character.npc.Imp;
import mazegame.character.npc.Samaritan;
import mazegame.character.npc.Sphinx;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.generation.GenerationAlgorithm;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.ConsoleInput;
import mazegame.utils.Displayer;
import mazegame.utils.Input;

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
	public Game(int width, int height, GenerationAlgorithm algorithm) {
		this.map = algorithm.generation(width, height);

		this.initCharacter();
		this.initQuest();

	}

	public Game(Map map, List<Character> characters, Player player, Quest quest) {
		this.map = map;
		this.quest = quest;
		this.player = player;
		this.listOfCharacters = characters;
		
		for (Character character : characters) {
			System.out.println(character);
			System.out.println(character.getCell());
			System.out.println("");
		}
		
		System.err.println(characters.size());
	}

	private void initCharacter() {
		this.listOfCharacters = new LinkedList<Character>();
		Player player = new Hero(0, 0, this.map);
		Npc imp = new Imp(0, 0, this.map);
		Npc samaritan = new Samaritan(0, 0, this.map);
		Npc sphinx = new Sphinx(0, 0, this.map);
		Npc vendor = new Vendor(0, 0, this.map);

		listOfCharacters.add(player);
		listOfCharacters.add(imp);
		listOfCharacters.add(samaritan);
		listOfCharacters.add(sphinx);
		listOfCharacters.add(vendor);

		this.player = player;
	}

	private void initQuest() {
		List<Challenge> listOfChallenges = new LinkedList<Challenge>();
		/*
		 * Ajouter les Challenges
		 */
		listOfChallenges.add(new WaitRound(this.player, 3));
		// listOfChallenges.add(new FinalCase(player));
		listOfChallenges.add(new CaseEightTree(player));

		this.quest = new Quest(listOfChallenges);

	}

	public void run() {

		boolean gameState = true;

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

	private void closeGame() {
		Game.INPUT.closeInput();
	}

}
