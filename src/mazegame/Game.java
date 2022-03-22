package mazegame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.RecursiveBacktracker;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.ConsoleInput;
import mazegame.utils.Displayer;
import mazegame.utils.Input;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.character.Pnj;
import mazegame.character.player.Hero;
import mazegame.character.pnj.Imp;

public class Game {
	
	public static final Displayer DISPLAYER = new ConsoleDisplayer();
	public static final Input INPUT = new ConsoleInput();
	private Maze maze;
	private Player player;
	private List<Pnj> pnjList;
	private boolean interaction;
	private boolean endGame;
	
	public Game(int width,int height) {
		
		this.initCharacter();
		
		GenerationAlgorithm algorithm = new RecursiveBacktracker(0,0);
		this.maze = new Maze(width, height, algorithm,this.pnjList,this.player);
		this.interaction = false;
		this.endGame = false;
		
		
	}
	
	/**
	 * Démarrage du jeu
	 */
	public void run() {
		
		Game.DISPLAYER.displayGameTitle();
		
		
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
		while (!this.endGame) {
			
			this.interaction = false;
			
			Game.DISPLAYER.displayMaze(this.maze);
			Game.DISPLAYER.displayStateGame(this);
			
			while (!interaction) {
				this.menu();
			}
			
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
			
			//TODO voir si on le fait quand la partie est fini
			// Deplace tous les pnj
			for (Pnj pnj : this.pnjList) {
				Cell nextCell = pnj.computeNextCell();
				pnj.moveTo(nextCell);
			}
			
		}
		
	}
	
	private void menu() {
		
		List<String> commmandList = Arrays.asList("aide","bouger","ramasser","interroger","quitter","test");
				
		Game.DISPLAYER.displayMsg("Votre choix : ");
		String choise = Game.INPUT.getString().toLowerCase();
		
		
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
				
			case "ramasser":
				//TODO: à faire
				this.getItem();
				break;
			case "interroger":
				this.talk();
				break;
				
			case "quitter":
				this.interaction = true;
				this.endGame = true;
				Game.DISPLAYER.displayEndGame();
				break;
				
			default:
				break;
			}
		}
		
		
	}

	private void talk() {
		// TODO: discuter avec le personnage selectionner
		this.interaction = true;
		
	}

	private void movePlayer() {
		
		
		List<Direction> possibleDirections = this.player.getAccesibleDirections();
		List<String> commmandList = new LinkedList<String>();
		
		String exitCommandString = "none";
		
		commmandList.add(exitCommandString);
		
		for (Direction direction : possibleDirections) {
			commmandList.add(direction.toString().toLowerCase());
		}

		String choise = "";
		
		do {
			Game.DISPLAYER.displayMsg("Ou voulez-vous aller ?");
			Game.DISPLAYER.displayCommand(commmandList);
			choise = Game.INPUT.getString().toLowerCase();
			
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
			
			Cell nextCell = this.maze.getGrid().getCellWithDirection(this.player.getCurrentCell(), direction);
			
			this.player.moveTo(nextCell);
			
			this.interaction = true;
		}
		
		
		
	}
	
	private void getItem() {
		
		//TODO: systeme de ramassage d'objet
		this.interaction = true;

	}

	/**
	 * Renvoie le Hero donc le joueur.
	 * 
	 * @return Le hero que controle le joueur.
	 */
	public Character getPlayer() {
		return this.player;
	}
	
	
	
	/**
	 * Initialise tous les personnages utile au jeu
	 */
	private void initCharacter() {
		
		this.pnjList = new LinkedList<>();

		
		Player playerHero = new Hero(0, 0);
		
		Pnj imp1 = new Imp(3, 3);
		
		this.pnjList.add(imp1);
		
		this.player = playerHero;		
	}
	
	

}
