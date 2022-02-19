/**
 * 
 */
package mazegame;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mazegame.generation.BinaryTree;

/**
 * Class de test pour la class Maze
 */
public class MazeTest {

	private Maze maze;
	private int mazeWidth , mazeHeight;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mazeWidth = 5;
		this.mazeHeight = 5;
		this.maze = new Maze(this.mazeWidth,this.mazeHeight, new BinaryTree());
	}
	
	@Test
	public void getWidthTest() {
		assertEquals(this.maze.getWidth(), this.mazeWidth);
	}
	
	@Test
	public void getHeightTest() {
		assertEquals(this.maze.getHeight(), this.mazeHeight);
	}
	
	@Test
	public void toStringSizeIsCorrect() {
		String mazeString = this.maze.toString();
		int targetSize = (this.mazeWidth * 4 +1) * (this.mazeHeight*2+1) + (2*this.mazeHeight+1);
		assertEquals(mazeString.length(), targetSize);
	}

}
