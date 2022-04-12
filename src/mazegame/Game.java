package mazegame;

import java.util.LinkedList;
import java.util.List;

import mazegame.challenge.Challenge;
import mazegame.challenge.WaitRound;
import mazegame.challenge.WaitThreeRounds;
import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.generation.Kruskal;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.ConsoleInput;
import mazegame.utils.Displayer;
import mazegame.utils.Input;
import mazegame.character.Character;

public class Game {

	public static final Displayer DISPLAYER = new ConsoleDisplayer();
	public static final Input INPUT = new ConsoleInput();

	private Map map;
	private List<Character> listOfCharacters;
	private Quest quest;
	
	private Player player;

	public Game(int width, int height) {
		this.map = new Kruskal().generation(width, height);
		
		this.initCharacter();
		this.initQuest();
		
	}

	private void initCharacter() {
		this.listOfCharacters = new LinkedList<Character>();
		Player player = new Hero(0, 0);
		
		
		
		listOfCharacters.add(player);
		
		this.player = player;
	}

	private void initQuest() {
		List<Challenge> listOfChallenges = new LinkedList<Challenge>();
		/*
		 * Ajouter les Challenges
		 */
		listOfChallenges.add(new WaitThreeRounds(this.player));
		listOfChallenges.add(new WaitRound(this.player));

		this.quest = new Quest(listOfChallenges);

	}

	public void run() {
		while (!this.quest.isComplete()) {
			Game.DISPLAYER.displayMap(this.map);

			for (Character character : listOfCharacters) {
				/*Action */
			}
		}
	}

}
