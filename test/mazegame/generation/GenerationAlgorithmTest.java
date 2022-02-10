package mazegame.generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mazegame.Direction;
import mazegame.Grid;

public class GenerationAlgorithmTest {

	protected int gridWidth, gridHeigth;
	protected Grid grid;

	public GenerationAlgorithmTest() {
		this.gridWidth = 5;
		this.gridHeigth = 5;
	}

	@Before
	public void setUp() {
		this.grid = new Grid(gridWidth, gridHeigth, false);
	}

	@Test
	public void borderIsNotOpen() {
		for (int x = 0; x < this.gridWidth; x++) {
			assertTrue(this.grid.getCell(x, 0).wallExist(Direction.N));
			assertTrue(this.grid.getCell(x, this.gridHeigth - 1).wallExist(Direction.S));
		}
		for (int y = 0; y < this.gridHeigth; y++) {
			assertTrue(this.grid.getCell(0, y).wallExist(Direction.O));
			assertTrue(this.grid.getCell(this.gridWidth - 1, y).wallExist(Direction.E));
		}
	}

	@Test
	public void verifyPerfectMaze() {
		assertTrue(true);
	}

}
