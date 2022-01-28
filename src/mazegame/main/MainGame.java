package mazegame.main;

import mazegame.Maze;
import mazegame.generation.*;

/**
 * Class MainGame
 */
public class MainGame {

	/**
	 * Class d'execution du programme
	 * @param args
	 */
	public static void main(String[] args) {

		Maze maze = new Maze(5, 5, new RecursiveBacktracker());

		System.out.println(maze);

	}

}
