package mazegame;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	private Map map;
	private int width = 5;
	private int height = 5;

	@Before
	public void setUp() throws Exception {
		this.map = new Map(this.width, this.height);
	}

	@Test
	public void createMapWithWall() {
		this.borderIsNotOpen(true);
		for (int y = 1; y < this.height - 2; y++) {
			for (int x = 1; x < this.width - 2; x++) {
				for (Direction direction : Direction.values()) {
					assertTrue(this.map.getCell(x, y).wallExist(direction));
				}
			}

		}
	}

	@Test
	public void createMapWithoutWall() {
		this.map = new Map(this.width, this.height, false);
		this.borderIsNotOpen(false);
		for (int y = 1; y < this.height - 2; y++) {
			for (int x = 1; x < this.width - 2; x++) {
				for (Direction direction : Direction.values()) {
					assertFalse(this.map.getCell(x, y).wallExist(direction));
				}
			}

		}

	}

	public void borderIsNotOpen(boolean containsWall) {

		int l = Math.max(this.width, this.height)-1;
			
		this.cornersIsNotOpen(containsWall);
		
		for (int i = 1; i < l; i++) {
			for (Direction direction : Direction.values()) {
				if (i < this.height - 1) {
					Cell leftCell = this.map.getCell(0, i);
					Cell rightCell = this.map.getCell(this.width-1, i);
					if (direction == Direction.O) {
						assertTrue(leftCell.wallExist(direction));
						assertEquals(rightCell.wallExist(direction),containsWall);
					}
					else if (direction == Direction.E) {
						assertEquals(leftCell.wallExist(direction),containsWall);
						assertTrue(rightCell.wallExist(direction));
					}
					else {
						assertEquals(rightCell.wallExist(direction),containsWall);
						assertEquals(leftCell.wallExist(direction),containsWall);
					}
				}
				if (i < this.width - 1) {
					Cell topCell = this.map.getCell(i,0);
					Cell bottomCell = this.map.getCell(i,this.width-1);
					if (direction == Direction.N) {
						assertTrue(topCell.wallExist(direction));
						assertEquals(bottomCell.wallExist(direction),containsWall);
					}
					else if (direction == Direction.S) {
						assertEquals(topCell.wallExist(direction),containsWall);
						assertTrue(bottomCell.wallExist(direction));
					}
					else {
						assertEquals(topCell.wallExist(direction),containsWall);
						assertEquals(bottomCell.wallExist(direction),containsWall);
					}
				}
			}
		}
	}
	
	public void cornersIsNotOpen(boolean containsWall) {
		Cell c1 = this.map.getCell(0, 0);
		Cell c2 = this.map.getCell(this.width - 1, 0);
		Cell c3 = this.map.getCell(0, this.height - 1);
		Cell c4 = this.map.getCell(this.width - 1, this.height - 1);
		
		assertTrue(c1.wallExist(Direction.O));
		assertTrue(c1.wallExist(Direction.N));
		assertTrue(c2.wallExist(Direction.N));
		assertTrue(c2.wallExist(Direction.E));
		assertTrue(c3.wallExist(Direction.O));
		assertTrue(c3.wallExist(Direction.S));
		assertTrue(c4.wallExist(Direction.S));
		assertTrue(c4.wallExist(Direction.E));
		
		assertEquals(c1.wallExist(Direction.S), containsWall);
		assertEquals(c1.wallExist(Direction.E), containsWall);
		assertEquals(c2.wallExist(Direction.O), containsWall);
		assertEquals(c2.wallExist(Direction.S), containsWall);
		assertEquals(c3.wallExist(Direction.N), containsWall);
		assertEquals(c3.wallExist(Direction.E), containsWall);
		assertEquals(c4.wallExist(Direction.O), containsWall);
		assertEquals(c4.wallExist(Direction.N), containsWall);
	}

	@Test
	public void getWidthTest() {
		int width = 10;
		this.map = new Map(width, this.height);
		assertEquals(this.map.getWidth(), width);
	}

	@Test
	public void getHeightTest() {
		int height = 7;
		this.map = new Map(this.width, height);
		assertEquals(this.map.getHeight(), height);
	}

	@Test
	public void correctSizeForListOfCells() {
		int normalSize = this.height * this.width;
		List<Cell> cellsList = this.map.getListsOfCells();
		assertEquals(normalSize, cellsList.size());
	}

	@Test
	public void getCellTest() {
		int indexMiddleRightCell = 24;

		Cell cell = this.map.getCell(4, 4);

		int indexInList = this.map.getListsOfCells().indexOf(cell);
		assertSame(cell, this.map.getListsOfCells().get(indexMiddleRightCell));

		assertEquals(indexInList,indexMiddleRightCell);

	}

	@Test
	public void getCellOutOfBound() {
		Cell cellOut = this.map.getCell(this.width + 1, this.height);
		assertNull(cellOut);
	}

	@Test
	public void getCellWithDirection() {
		Cell currentCell = this.map.getCell(2, 2);
		
		Cell nextCellN = this.map.getCell(2, 1);
		Cell nextCellS = this.map.getCell(2, 3);
		Cell nextCellO = this.map.getCell(1, 2);
		Cell nextCellE = this.map.getCell(3, 2);
		
		Cell directionCellN = this.map.getCellWithDirection(currentCell, Direction.N);
		Cell directionCellS = this.map.getCellWithDirection(currentCell, Direction.S);
		Cell directionCellO = this.map.getCellWithDirection(currentCell, Direction.O);
		Cell directionCellE = this.map.getCellWithDirection(currentCell, Direction.E);

		assertSame(nextCellN, directionCellN);
		assertSame(nextCellS, directionCellS);
		assertSame(nextCellO, directionCellO);
		assertSame(nextCellE, directionCellE);
		
	}

	@Test
	public void cellHasNeighbors() {
		Cell currentCell = new Cell(0, 0);
		Cell neighborXCell = new Cell(1, 0);
		Cell neighborYCell = new Cell(0, 1);
		List<Cell> neighborsCells = this.map.getNeighborsCells(currentCell);

		assertTrue(neighborsCells.contains(neighborXCell));
		assertTrue(neighborsCells.contains(neighborYCell));
		assertEquals(neighborsCells.size() ,2);
	}
	
	@Test
	public void toStringSizeIsCorrect() {
		String mapString = this.map.toString();
		int targetSize = (this.map.getWidth() * 4 +1) * (this.map.getHeight()*2+1) + (2*this.map.getHeight()+1);
		assertEquals(mapString.length(), targetSize);
	}


}
