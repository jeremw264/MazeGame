package mazegame.generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Direction;
import mazegame.Maze;

public class GenerationAlgorirthmTest {

	protected Maze maze;
	protected int mazeWidth, mazeHeigth;
	protected GenerationAlgorithm algorithm;

	public GenerationAlgorirthmTest(GenerationAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public GenerationAlgorirthmTest() {
		this.algorithm = new BinaryTree();
	}

	// See for before class or search initializationError

	@Before
	public void setUp() throws Exception {
		this.mazeWidth = 5;
		this.mazeHeigth = 5;
		this.maze = new Maze(this.mazeWidth, this.mazeHeigth, this.algorithm);
	}

	@Test
	public void borderIsNotOpen() {
		for (int x = 0; x < this.mazeWidth; x++) {
			assertTrue(this.maze.getCell(x, 0).wallExist(Direction.N));
			assertTrue(this.maze.getCell(x, this.mazeHeigth - 1).wallExist(Direction.S));
		}
		for (int y = 0; y < this.mazeHeigth; y++) {
			assertTrue(this.maze.getCell(0, y).wallExist(Direction.O));
			assertTrue(this.maze.getCell(this.mazeWidth - 1, y).wallExist(Direction.E));
		}
	}

	@Test
	public void verifyPerfectMaze() {
		assertTrue(false);
	}

}
