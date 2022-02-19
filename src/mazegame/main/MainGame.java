package mazegame.main;

import java.util.Scanner;

import mazegame.Maze;
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

		GenerationAlgorithm algorithm;

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
			case "exit":
				algorithm = null;
				System.out.println("Bye bye");
				break;

			default:
				algorithm = null;
				System.out.println("Invalid");
				break;
			}

			if (algorithm != null) {
				Maze maze = new Maze(5, 5, algorithm);

				System.out.println(maze);
			}

		} while (!in.equals("exit"));
		System.out.println("Bye bye");

		// PerfectMaze verifyMaze = new PerfectMaze(maze);
		// verifyMaze.verify(0, 0);

	}

}
