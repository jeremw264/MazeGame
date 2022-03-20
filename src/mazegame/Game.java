package mazegame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.RecursiveBacktracker;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.Displayer;
import mazegame.character.Character;
import mazegame.character.player.Hero;

public class Game {
	
	public static final Displayer DISPLAYER = new ConsoleDisplayer();
	private Maze maze;
	private Character hero;
	private List<Character> characters;
	private boolean interaction;
	
	public Game(int width,int height) {
		
		this.initCharacter();
		
		GenerationAlgorithm algorithm = new RecursiveBacktracker(0,0);
		this.maze = new Maze(width, height, algorithm,this.characters);
		this.interaction = false;
		
	}
	
	/**
	 * Démarrage du jeu
	 */
	public void run() {
		
		Game.DISPLAYER.displayGameTitle();
		
		
		int i = 0;
		
		/**
		 * Ecrire une methode dans maze qui determine la case final du labyrinthe
		 * 
		 * Condition d'arret Si un instance du Hero n'est pas dans la case final le jeu continue
		 * 
		 * Decomposer avec:
		 * 	Maze qui calcule la case final a l'initialisation du labyrinthe
		 * 	Game qui a une methode pour determiner la fin pour pouvoir akouter des conditions
		 */
		
		// Ici il faudra placer la condition d'arret du jeu
		while (i < 100) {
			
			this.interaction = false;
			
			Game.DISPLAYER.displayMaze(this.maze);
			Game.DISPLAYER.displayStateGame(this);
			
			while (!interaction) {
				this.menu();
			}
			
			i++;
			
			// Regarder les posibilité du hero et les listerer
			
			// gerer l'interation utilisateur
			/**
			 *  Si il veut bouger afficher direction et attendre choix pour bouger FIN
			 *  Si il veut interagir avec un personnage faire parler et attendre la recompense si il y en a
			 *  Si il veut rammaser un objet le mettre dans l'inventaire du Hero FIN
			 *  Si il veut utiliser un objet Lister les objet , l'utiliser FIN
			 *  Si il veut voir l'inventaire afficher inventaire FIN
			 *  
			 */
			
			// Faire deplacer tous les personnage sauf le hero.
		}
		
	}
	
	private void menu() {
		
		List<String> commmandList = Arrays.asList("aide","bouger");
		
		Scanner userInput = new Scanner(System.in);
		
		Game.DISPLAYER.displayMsg("Votre choix : ");
		String choise = userInput.nextLine().toLowerCase();
		
		if (!commmandList.contains(choise)) {
			Game.DISPLAYER.displayError("Ce choix n'existe pas !! ");
		} else {
			switch (choise) {
			case "aide":
				
				Game.DISPLAYER.displayHelp(commmandList);
				break;
			case "bouger":				
				this.movePlayer();
				break;
				
			default:
				break;
			}
		}
		
		userInput.close();
		
	}

	private void movePlayer() {
		
		
		List<Direction> possibleDirections = this.hero.getAccesibleDirections();
		List<String> commmandList = new LinkedList<String>();
		
		String exitCommandString = "none";
		
		commmandList.add(exitCommandString);
		
		for (Direction direction : possibleDirections) {
			commmandList.add(direction.toString().toLowerCase());
		}

		Scanner userInput = new Scanner(System.in);		
		String choise = "";
		
		do {
			Game.DISPLAYER.displayMsg("Ou voulez-vous aller ?");
			Game.DISPLAYER.displayCommand(commmandList);
			choise = userInput.nextLine().toLowerCase();
			
			if (!commmandList.contains(choise) && !exitCommandString.equals(choise)) {
				Game.DISPLAYER.displayError("Ce choix n'existe pas !! ");
			}
		} while (!commmandList.contains(choise) && !exitCommandString.equals(choise));
		
		if (!choise.equals(exitCommandString)) {
			Direction direction;
			
			if (choise.equals(Direction.N.toString().toLowerCase())) direction = Direction.N;
			else if (choise.equals(Direction.S.toString().toLowerCase())) direction = Direction.S;
			else if (choise.equals(Direction.O.toString().toLowerCase())) direction = Direction.O;
			else direction = Direction.E;
			
			Cell nextCell = this.maze.getGrid().getCellWithDirection(this.hero.getCurrentCell(), direction);
			
			this.hero.moveTo(nextCell);
			
			this.interaction = true;
		}
		
		userInput.close();
		
	}

	/**
	 * Renvoie le Hero donc le joueur.
	 * 
	 * @return Le hero que controle le joueur.
	 */
	public Character getHero() {
		return this.hero;
	}
	
	
	
	/**
	 * Initialise tous les personnages utile au jeu
	 */
	private void initCharacter() {
		
		this.characters = new LinkedList<>();

		
		Character playerHero = new Hero(0, 0);
		
		this.hero = playerHero;
		
		this.characters.add(playerHero);
		
	}
	
	

}
