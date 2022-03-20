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
import mazegame.character.player.Hero;
import mazegame.generation.BinaryTree;

/**
 * Class de test pour la class Maze
 */
public class MazeTest {

	private Maze maze;
	private int mazeWidth , mazeHeight;
	private List<Character> characters;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mazeWidth = 5;
		this.mazeHeight = 5;
		this.setCharactersList();
		this.maze = new Maze(this.mazeWidth,this.mazeHeight, new BinaryTree(),this.characters);
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
		for (Character character : this.characters) {
			int x = character.getX();
			int y = character.getY();
			
			assertTrue(this.maze.getGrid().getCell(x, y).getCharacters().contains(character));
			assertEquals(character.getCurrentCell(), this.maze.getGrid().getCell(x, y));
		}
	}
	
	private void setCharactersList() {
		this.characters = new LinkedList<Character>();
		
		characters.add(new Hero(0, 0));
		
	}
	
	
}
