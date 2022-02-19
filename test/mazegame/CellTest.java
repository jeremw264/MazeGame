/**
 * 
 */
package mazegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jeremy
 *
 */
public class CellTest {
	
	private Cell cell;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.cell = new Cell(3, 5);
	}

	@Test
	public void getXTest() {
		assertEquals(3, this.cell.getX());
	}
	
	@Test
	public void getYTest() {
		assertEquals(5, this.cell.getY());
	}
	
	@Test
	public void wallExistOnCreationTest() {
		assertTrue(this.cell.wallExist(Direction.N));
		assertTrue(this.cell.wallExist(Direction.S));
		assertTrue(this.cell.wallExist(Direction.O));
		assertTrue(this.cell.wallExist(Direction.E));
	}
	
	@Test
	public void wallDontExistOnCreationTest() {
		this.cell = new Cell(3, 5,false);
		assertFalse(this.cell.wallExist(Direction.N));
		assertFalse(this.cell.wallExist(Direction.S));
		assertFalse(this.cell.wallExist(Direction.O));
		assertFalse(this.cell.wallExist(Direction.E));
	}
	
	@Test
	public void eraseWallTest() {
		assertTrue(this.cell.wallExist(Direction.N));
		
		this.cell.eraseWall(Direction.N);
		
		assertFalse(this.cell.wallExist(Direction.N));
	}
	
	@Test
	public void createWallTest() {
		
		Direction direction = Direction.N;
		
		this.cell.eraseWall(direction);		
		
		assertFalse(this.cell.wallExist(direction));
		
		this.cell.createWall(direction);
		
		assertTrue(this.cell.wallExist(direction));
	}
	
	
	@Test
	public void visited() {
		assertFalse(this.cell.isVisited());
		this.cell.setVisited();
		assertTrue(this.cell.isVisited());
	}
	
	@Test
	public void cellIsEqualsTest() {
		Cell cell1 = new Cell(0, 0);
		Cell cell2 = new Cell(0, 0);
		
		assertTrue(cell1.equals(cell2));
	}
	
	@Test
	public void cellIsNotEqualsTest() {
		Cell cell1 = new Cell(0, 0);
		Cell cell2 = new Cell(2, 5);
		
		assertFalse(cell1.equals(cell2));
	}
	
	@Test
	public void cellNotEqualsWithNotCell() {
		Cell cell1 = new Cell(2, 5);
		String obj = "k";
		
		assertFalse(cell1.equals(obj));
	}

}
