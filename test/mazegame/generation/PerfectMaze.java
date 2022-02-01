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

	public PerfectMaze(Maze maze) {
		this.maze = maze;
	}

	public void verify(int seedX, int seedY) {
		List<Cell> cellsTreat = new ArrayList<>();
		Stack<Cell> stack = new Stack<>();
		Cell startingCell = this.maze.getCell(seedX, seedY);
		stack.push(startingCell);
		cellsTreat.add(startingCell);
		while (!stack.empty()) {
			Cell currentCell = stack.peek();
			Cell nextCell;
			List<Direction> accesDirections = this.getAccesibleDirections(currentCell);
			
			int i = 0;
			// gerer le cas de generation tableau vide 
			Direction direction = accesDirections.get(0);
			while (cellsTreat.contains(this.getCellWithDirection(currentCell,direction)) && i < accesDirections.size()-1) {
				i++;
				direction = accesDirections.get(i);
			}
			
			nextCell = this.getCellWithDirection(currentCell, direction);
			if (!cellsTreat.contains(nextCell)) {
				cellsTreat.add(nextCell);
				stack.push(nextCell);
			} else {
				stack.pop();
			}
			System.out.println(currentCell);
			System.out.println(direction);
			System.out.println(maze);

		}
		System.err.println(cellsTreat.size());
	}

	public void accessiblePath() {

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

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
