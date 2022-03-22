/**
 * 
 */
package mazegame;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.character.Character;
import mazegame.character.Pnj;
import mazegame.character.player.Hero;
import mazegame.generation.BinaryTree;

/**
 * Class de test pour la class Maze
 */
public class MazeTest {

	private Maze maze;
	private int mazeWidth , mazeHeight;
	private List<Pnj> pnjList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mazeWidth = 5;
		this.mazeHeight = 5;
		this.setPnjList();
		this.maze = new Maze(this.mazeWidth,this.mazeHeight, new BinaryTree(),this.pnjList,new Hero(0, 0));
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
	
	@Test
	public void setCharactersTest() {
		for (Pnj pnj: this.pnjList) {
			int x = pnj.getX();
			int y = pnj.getY();
			
			assertTrue(this.maze.getGrid().getCell(x, y).getCharacters().contains(pnj));
			assertEquals(pnj.getCurrentCell(), this.maze.getGrid().getCell(x, y));
		}
	}
	
	private void setPnjList() {
		this.pnjList = new LinkedList<Pnj>();
		
		
	}
	
	
}
