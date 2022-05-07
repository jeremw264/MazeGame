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

	/**
	 * Objet qui est responsable des affichage du jeu.
	 */
	public static final Displayer DISPLAYER = new ConsoleDisplayer();
	
	/**
	 * Objet qui est responsable des entrées utilisateur du jeu.
	 */
	public static final Input INPUT = new ConsoleInput();

	// La carte du jeu.
	private Map map;
	
	// La liste des personnages présent dans le jeu.
	private List<Character> listOfCharacters;
	
	// La quête à accomplir dans jeu. 
	private Quest quest;

	// Le joueur que controle l'utilisateur.
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

	/**
	 * Constructeur de l'objet Game
	 * 
	 * @param map La carte du jeu.
	 * @param characters La liste des joueur dans le jeu.
	 * @param player Le joueur que controle l'utilisateur.
	 * @param quest La quête a accomplir pour finir le jeu.
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
	 * Initialise la liste des personnages.
	 */
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
	
	/**
	 * Initialise la quête du jeu.
	 */
	private void initQuest() {
		List<Challenge> listOfChallenges = new LinkedList<Challenge>();
		/*
		 * Ajouter les Challenges
		 */
		listOfChallenges.add(new WaitRound(3));
		// listOfChallenges.add(new FinalCase(player));
		listOfChallenges.add(new CaseEightTree());

		this.quest = new Quest(this.player, listOfChallenges);

	}

	/**
	 * Démare le jeu.
	 */
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

	/**
	 * Instruction a executer à la fin du jeu.
	 */
	private void closeGame() {
		Game.INPUT.closeInput();
	}

}
