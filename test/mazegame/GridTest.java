package mazegame;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GridTest {

	private Grid grid;
	private int width = 5;
	private int height = 5;

	@Before
	public void setUp() throws Exception {
		this.grid = new Grid(this.width, this.height);
	}

	@Test
	public void createGridWithWall() {
		this.borderIsNotOpen(true);
		for (int y = 1; y < this.height - 2; y++) {
			for (int x = 1; x < this.width - 2; x++) {
				for (Direction direction : Direction.values()) {
					assertTrue(this.grid.getCell(x, y).wallExist(direction));
				}
			}

		}
	}

	@Test
	public void createGridWithoutWall() {
		this.grid = new Grid(this.width, this.height, false);
		this.borderIsNotOpen(false);
		for (int y = 1; y < this.height - 2; y++) {
			for (int x = 1; x < this.width - 2; x++) {
				for (Direction direction : Direction.values()) {
					assertFalse(this.grid.getCell(x, y).wallExist(direction));
				}
			}

		}

	}

	public void borderIsNotOpen(boolean containsWall) {
		for (int x = 0; x < this.width; x++) {
			Cell topCell = this.grid.getCell(x, 0);
			assertTrue(topCell.wallExist(Direction.N));
			for (Direction direction : Direction.values()) {
				if (direction != Direction.N && x != 0 && x != this.width) {
					assertEquals(topCell.wallExist(direction), containsWall);
				} else if (direction != Direction.N && x == 0 && direction != Direction.O) {
					assertEquals(topCell.wallExist(direction), containsWall);
				} else if (direction != Direction.N && x == this.width - 1 && direction != Direction.E) {

				}
			}

			Cell bottomCell = this.grid.getCell(x, this.height - 1);
			assertTrue(bottomCell.wallExist(Direction.S));
			for (Direction direction : Direction.values()) {
				if (direction != Direction.S && x != 0 && x != this.width) {
					assertEquals(topCell.wallExist(direction), containsWall);
				} else if (direction != Direction.S && x == 0 && direction != Direction.O) {
					assertEquals(topCell.wallExist(direction), containsWall);
				} else if (direction != Direction.S && x == this.width - 1 && direction != Direction.E) {

				}
			}
		}
		for (int y = 0; y < this.height; y++) {
			Cell leftCell = this.grid.getCell(0, y);
			assertTrue(leftCell.wallExist(Direction.O));
			for (Direction direction : Direction.values()) {
				if (direction != Direction.O && y != 0 && y != this.width) {
					assertEquals(leftCell.wallExist(direction), containsWall);
				} else if (direction != Direction.O && y == 0 && direction != Direction.N) {
					assertEquals(leftCell.wallExist(direction), containsWall);
				} else if (direction != Direction.O && y == this.width - 1 && direction != Direction.S) {

				}
			}
			Cell rightCell = this.grid.getCell(this.width - 1, y);
			assertTrue(rightCell.wallExist(Direction.E));
			for (Direction direction : Direction.values()) {
				if (direction != Direction.E && y != 0 && y != this.width) {
					assertEquals(leftCell.wallExist(direction), containsWall);
				} else if (direction != Direction.E && y == 0 && direction != Direction.N) {
					assertEquals(leftCell.wallExist(direction), containsWall);
				} else if (direction != Direction.E && y == this.width - 1 && direction != Direction.S) {

				}
			}
		}
	}

	@Test
	public void getWidthTest() {
		int width = 10;
		this.grid = new Grid(width, this.height);
		assertEquals(this.grid.getWidth(), width);
	}

	@Test
	public void getHeightTest() {
		int height = 7;
		this.grid = new Grid(this.width, height);
		assertEquals(this.grid.getHeight(), height);
	}

	@Test
	public void correctSizeForListOfCells() {
		int normalSize = this.height * this.width;
		List<Cell> cellsList = this.grid.getListsOfCells();
		assertEquals(normalSize, cellsList.size());
	}

	@Test
	public void getCellTest() {
		int indexMiddleRightCell = 24;
		
		Cell cell = this.grid.getCell(4, 4);

		int indexInList = this.grid.getListsOfCells().indexOf(cell);
		assertSame(cell, this.grid.getListsOfCells().get(indexMiddleRightCell));
		
		assertTrue(indexInList == indexMiddleRightCell);

	}
	
	@Test
	public void getCellOutOfBound() {
		Cell cellOut = this.grid.getCell(this.width + 1, this.height);
		assertNull(cellOut);
	}
	
	@Ignore
	@Test //(expected=CellNotInGridException.class)
	public void getCellOutOfBoundException() {
		Cell cellOut = this.grid.getCell(this.width + 1, this.height);
	}
	
	@Test
	public void getCellWithDirection() {
		Cell currentCell = this.grid.getCell(2, 2);
		Cell nextCell = this.grid.getCell(2, 1);
		Cell directionCell = this.grid.getCellWithDirection(currentCell, Direction.N);
		assertSame(nextCell, directionCell);
		assertEquals(currentCell.getY()-1, directionCell.getY());
	}

	@Test
	public void getCellWithDirectionOutOfBoundException() {
		Cell currentCell = this.grid.getCell(0,0);
		Cell directionCell = this.grid.getCellWithDirection(currentCell, Direction.N);
		assertNull(directionCell);
	}
	
	@Test
	public void cellHasNeighbors() {
		Cell currentCell = new Cell(0, 0);
		Cell neighborXCell = new Cell(1, 0);
		Cell neighborYCell = new Cell(0, 1);
		List<Cell> neighborsCells = this.grid.getNeighborsCells(currentCell);

		assertTrue(neighborsCells.contains(neighborXCell));
		assertTrue(neighborsCells.contains(neighborYCell));
		assertTrue(neighborsCells.size() == 2);
	}

}
