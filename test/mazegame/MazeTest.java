/**
 * 
 */
package mazegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
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
	public void getCellTest() {
		int indexMiddleRightCell = 24;
		
		Cell middleRightCell =  maze.getCell(4,4);
		
		int indexInList = maze.getBoard().indexOf(middleRightCell);
		
		assertTrue(indexInList == indexMiddleRightCell);
		
	}

}
