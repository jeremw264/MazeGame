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

	/**
	 * Vérifie si le labyrinthe est parfait
	 * 
	 * @param seedX Point de départ horizontale
	 * @param seedY	Point de départ verticale
	 */
	public void verify(int seedX, int seedY) {
		Cell startingCell = this.maze.getCell(seedX, seedY);

		this.stack.push(startingCell);
		this.cellsTreat.add(startingCell);

		while (!this.stack.empty()) {

			Cell currentCell = this.stack.peek();

			// if the maze is not perfect the algo don't visited each cell (see in get
			// nextCell method)

			System.out.println(this.getAccesibleDirections(currentCell));

			if (this.getNextCell(currentCell) != null) {
				Cell nextCell = this.getNextCell(currentCell);
				this.stack.push(nextCell);
				this.cellsTreat.add(nextCell);
				System.out.println(nextCell);
			} else {
				System.err.println("Back");
				this.stack.pop();
			}
		}

		System.out.println("Nombre de cellule parcouru : " + this.cellsTreat.size());
		// Prédicat a vérifié pour validé le test unitaire
		System.out.println(
				"Perfect Maze = " + (this.cellsTreat.size() == (this.maze.getHeight() * this.maze.getWidth())));

	}

	/**
	 * Renvoie la prochaine cellule a traité
	 * 
	 * @param currentCell la cellule courante
	 * @return la prochaine cellule a traité
	 */
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

	/**
	 * Renvoie la cellule voisine de la cellule courante en fonction de la direction
	 * 
	 * @param cell      la cellule courante
	 * @param direction la direction de la cellule voisine par rapport a la cellule
	 *                  courante
	 * @return La cellule voisine
	 */
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
