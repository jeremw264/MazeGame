package mazegame.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Grid;
import mazegame.Maze;

/**
 * Permet de vérifier si un labyrinthe est parfait (toute les cases sont
 * accessible)
 * 
 * @author jeremy
 *
 */
public class PerfectMaze {

	public Grid grid;
	public List<Cell> cellsTreat;
	public Stack<Cell> stack;

	/**
	 * Constructeur de l'objet PerfectMaze
	 * 
	 * @param maze le labyrinthe aprés génération
	 */
	public PerfectMaze(Maze maze) {
		this.grid = maze.getGrid();
		this.cellsTreat = new ArrayList<>();
		this.stack = new Stack<>();

	}

	/**
	 * Vérifie si le labyrinthe est parfait
	 * 
	 * @param seedX Point de départ horizontale
	 * @param seedY Point de départ verticale
	 */
	public boolean verify(int seedX, int seedY) {
		Cell startingCell = this.grid.getCell(seedX, seedY);

		this.stack.push(startingCell);
		this.cellsTreat.add(startingCell);

		while (!this.stack.empty()) {

			Cell currentCell = this.stack.peek();

			if (this.getNextCell(currentCell) != null) {
				Cell nextCell = this.getNextCell(currentCell);
				this.stack.push(nextCell);
				this.cellsTreat.add(nextCell);
			} else {
				this.stack.pop();
			}
		}

		return this.cellsTreat.size() == (this.grid.getHeight() * this.grid.getWidth());

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
			return this.grid.getCell(x, y - 1);

		if (direction == Direction.S)
			return this.grid.getCell(x, y + 1);

		if (direction == Direction.O)
			return this.grid.getCell(x - 1, y);

		return this.grid.getCell(x + 1, y);

	}

}
