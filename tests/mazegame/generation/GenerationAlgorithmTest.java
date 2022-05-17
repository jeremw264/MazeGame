package mazegame.generation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;

public class GenerationAlgorithmTest {

	protected int gridWidth, gridHeigth;
	protected Map map;

	public GenerationAlgorithmTest() {
		this.gridWidth = 5;
		this.gridHeigth = 5;
	}

	@Before
	public void setUp() {
		this.map = new Map(gridWidth, gridHeigth, false);
	}

	@Test
	public void borderIsNotOpen() {
		for (int x = 0; x < this.gridWidth; x++) {
			assertTrue(this.map.getCell(x, 0).wallExist(Direction.N));
			assertTrue(this.map.getCell(x, this.gridHeigth - 1).wallExist(Direction.S));
		}
		for (int y = 0; y < this.gridHeigth; y++) {
			assertTrue(this.map.getCell(0, y).wallExist(Direction.O));
			assertTrue(this.map.getCell(this.gridWidth - 1, y).wallExist(Direction.E));
		}
	}

	@Test
	public void closePathTest() {

		GenerationAlgorithm algorithm = new GenerationAlgorithm() {

			@Override
			public Map generation(int width, int heigth) {
				return new Map(width, heigth, false);
			}
		};

		this.map = algorithm.generation(2, 1);
		assertEquals(this.map.getWidth(), 2);
		assertEquals(this.map.getHeight(), 1);

		Cell cell1 = this.map.getCell(0, 0);
		Cell cell2 = this.map.getCell(1, 0);

		assertFalse(cell1.wallExist(Direction.E));
		assertFalse(cell2.wallExist(Direction.O));

		algorithm.closePath(cell1, cell2);

		assertTrue(cell1.wallExist(Direction.E));
		assertTrue(cell2.wallExist(Direction.O));
	}

	@Test
	public void verifyPerfectMaze() {
		PerfectMazeValidator validator = new PerfectMazeValidator(this.map);
		assertEquals(validator.verify(0, 0), this.gridHeigth * this.gridWidth);
	}
}
