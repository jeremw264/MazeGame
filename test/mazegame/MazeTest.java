/**
 * 
 */
package mazegame;



import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	/*

	*/
	
	

}
