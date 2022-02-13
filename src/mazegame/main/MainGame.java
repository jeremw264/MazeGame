package mazegame.main;

import java.util.List;
import java.util.Scanner;

import mazegame.Direction;
import mazegame.Maze;
import mazegame.character.Hero;
import mazegame.generation.*;

/**
 * Class MainGame
 */
public class MainGame {

	
	/**
	 * Class d'execution du programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Maze maze;

		GenerationAlgorithm algorithm = null;

		Scanner input = new Scanner(System.in);
		String in = "";
		do {

			System.out.println("Quel algorithme de génération voulez vous utiliser ");
			System.out.println("\t 1 : Arbre Binaire");
			System.out.println("\t 2 : Recursive BackTracker");
			System.out.println("\t 3 : Kruskal");

			in = input.nextLine();

			switch (in) {
			case "1":
				System.out.println("Arbre Binaire");
				algorithm = new BinaryTree();
				break;
			case "2":
				System.out.println("Recursive BackTracker");
				algorithm = new RecursiveBacktracker(0, 0);
				break;
			case "3":
				System.out.println("Kruskal");
				algorithm = new Kruskal();
				break;

			default:
				algorithm = null;
				System.out.println("Invalid");
				break;
			}


		} while (algorithm == null);
		
		maze = new Maze(5, 5, algorithm);

		
		Hero hero = new Hero(0, 0);
		maze.setHero(hero);
		
		do {
			System.out.println(maze);
			
			List<Direction> directionCells = maze.getHero().getAccesibleDirections();

			
			System.out.println("Quelle direction ?");
			
			for (int i = 0; i < directionCells.size(); i++) {
				System.out.println(i + " : "+directionCells.get(i));
				
			}
			
			int choise = input.nextInt();
			
			maze.moveHero(directionCells.get(choise));
			
			
		} while (true);

		// PerfectMaze verifyMaze = new PerfectMaze(maze);
		// verifyMaze.verify(0, 0);

	}

}
