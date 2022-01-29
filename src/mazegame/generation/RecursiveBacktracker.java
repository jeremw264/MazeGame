package mazegame.generation;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Maze;

public class RecursiveBacktracker implements GenerationAlgorithm {
	
	private final int seedX = 0;
	private final int seedY = 0;
	private Stack<Cell> stack = new Stack<Cell>();
	
	public void generation(Maze maze) {
		
		Cell startCell = maze.getCell(this.seedX, this.seedY);
		startCell.setVisited();
		this.stack.push(startCell);
		
		while (!this.stack.empty()) {
			Cell currentCell = this.stack.peek();
			List<Cell> unvisitedNeightboursCells = maze.getUnvisitedNeighborsCells(currentCell);
			
			/*
			 * uncomment for see step by step and uncomment in method carvePath
			 */
			//System.out.println(currentCell);
			//System.out.println(maze);
			
			if (!unvisitedNeightboursCells.isEmpty()) {
				Cell nextCell = this.getRandomNeighBor(currentCell,unvisitedNeightboursCells); // 
				this.carvePath(currentCell,nextCell);
				nextCell.setVisited();
				this.stack.push(nextCell);
			}else {
				this.stack.pop();
			}
		}
	}
	
	public Cell getRandomNeighBor(Cell currentCell,List<Cell> unvisitedNeightboursCells) {
		Collections.shuffle(unvisitedNeightboursCells);
		return unvisitedNeightboursCells.get(0);

	}
	
	public void carvePath(Cell currentCell,Cell nextCell) {
		Direction directionNextCell = Direction.directionOf(currentCell, nextCell);
		currentCell.eraseWall(directionNextCell);
		Direction directionCurrentCell = Direction.directionOf(nextCell, currentCell);
		nextCell.eraseWall(directionCurrentCell);
		
		// uncomment for step by step
		// System.out.println(directionNextCell);
		
		
	}


}
