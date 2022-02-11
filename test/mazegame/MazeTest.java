/**
 * 
 */
package mazegame;



import org.junit.Before;
import org.junit.Test;

import mazegame.generation.BinaryTree;

/**
 * Class de test pour la class Maze
 */
public class MazeTest {

	private Maze maze;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.maze = new Maze(5, 5, new BinaryTree());
	}

}
