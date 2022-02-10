package mazegame;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
	
	private Grid grid;

	@Before
	public void setUp() throws Exception {
		this.grid = new Grid(5, 5);
	}

	@Test
	public void getCellTest() {
		int indexMiddleRightCell = 24;
		
		Cell middleRightCell =  this.grid.getCell(4,4);
		
		int indexInList = this.grid.getListsOfCells().indexOf(middleRightCell);
		
		assertTrue(indexInList == indexMiddleRightCell);
		
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
	/*
	@Test
	public void cellHasNeighborsUnvisited() {
		Cell currentCell = new Cell(0, 0);
		Cell neighborXCell = new Cell(1, 0);
		this.grid.getCell(0, 1).setVisited();
		List<Cell> neighborsCells = this.grid.getUnvisitedNeighborsCells(currentCell);
		
		assertTrue(neighborsCells.contains(neighborXCell));
		assertTrue(neighborsCells.size() == 1);
	}
	
	@Test
	public void cellHasNotNeighborsUnvisited() {
		Cell currentCell = new Cell(0, 0);
		this.maze.getCell(1, 0).setVisited();
		this.maze.getCell(0, 1).setVisited();
		List<Cell> neighborsCells = this.grid.getUnvisitedNeighborsCells(currentCell);
		
		assertTrue(neighborsCells.size() == 0);
	}
	*/

}
