package mazegame.main;

import mazegame.Direction;
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

		Maze maze = new Maze(5, 5, new RecursiveBacktracker(0,0));
		
		System.out.println(maze);
		
		
		//PerfectMaze verifyMaze = new PerfectMaze(maze);
		//verifyMaze.verify(0, 0);

		
	}

}
