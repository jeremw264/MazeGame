package mazegame.generation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Maze;

public class PerfectMaze {

	public Maze maze;
	public List<Cell> cellsTreat;
	public Stack<Cell> stack;

	public PerfectMaze(Maze maze) {
		this.maze = maze;
		this.cellsTreat = new ArrayList<>();
		this.stack = new Stack<>();

	}

	public void verify(int seedX, int seedY) {
		Cell startingCell = this.maze.getCell(seedX, seedY);
		
		this.stack.push(startingCell);
		this.cellsTreat.add(startingCell);
		
		while (!this.stack.empty()) {
			
			Cell currentCell = this.stack.peek();
			
			if (this.getNextCell(currentCell) != null) {
				Cell nextCell = this.getNextCell(currentCell);
				this.stack.push(nextCell);
				this.cellsTreat.add(nextCell);
			}else {
				this.stack.pop();
			}
		}
		
		System.out.println(this.cellsTreat.size());

	}

	public Cell getNextCell(Cell currentCell) {
		List<Direction> accesibleDirections = this.getAccesibleDirections(currentCell);
		
		for (Direction direction : accesibleDirections) {
			Cell cellInDirection = this.getCellWithDirection(currentCell, direction); 
			if (!this.cellsTreat.contains(cellInDirection)) {
				return cellInDirection;
			}
		}
		
		return null;
	}

	/**
	 * Renvoie les directions accesible
	 * 
	 * @param cell La cellule courante
	 * @return Une liste qui contient les direction accessible
	 */
	public List<Direction> getAccesibleDirections(Cell cell) {
		List<Direction> accesibleDirections = new ArrayList<>(4);

		for (Direction direction : Direction.values()) {
			if (!cell.wallExist(direction)) {
				accesibleDirections.add(direction);
			}
		}

		return accesibleDirections;
	}

	public Cell getCellWithDirection(Cell cell, Direction direction) {

		int x = cell.getX();
		int y = cell.getY();

		if (direction == Direction.N)
			return this.maze.getCell(x, y - 1);

		if (direction == Direction.S)
			return this.maze.getCell(x, y + 1);

		if (direction == Direction.O)
			return this.maze.getCell(x - 1, y);

		return this.maze.getCell(x + 1, y);

	}

}
