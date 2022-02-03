package mazegame.generation;

import mazegame.*;

/**
 * Class BinaryTree
 */
public class BinaryTree implements GenerationAlgorithm {

	private Maze maze;
	
	/**
	 * Algorithme de Génération : Arbre Binaire
	 * 
	 * @param maze Le labyrinthe qu'on veut modifié
	 */
	public void generation(Maze maze) {

		this.maze = maze;
		int mazeHeight = maze.getHeight();
		int mazeWidth = maze.getWidth();

		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {

				int random_int = (int) Math.floor(Math.random() * (2));

				Cell currentCell = maze.getCell(x, y);

				// Condition de suppresion des walls
				if ((random_int == 1 || y == mazeHeight - 1) && x < mazeWidth - 1) {
					this.carvePath(currentCell, this.getCellWithDirection(currentCell, Direction.E));
				} else if (y != mazeHeight - 1) {
					this.carvePath(currentCell, this.getCellWithDirection(currentCell, Direction.S));
				}
			}
		}
	}
	

	/**
	 * Détruit les murs entre deux cellule.
	 * @param currentCell La cellule actuelle.
	 * @param nextCell La cellule suivante.
	 */
	public void carvePath(Cell currentCell, Cell nextCell) {
		Direction directionNextCell = Direction.directionOf(currentCell, nextCell);
		currentCell.eraseWall(directionNextCell);
		Direction directionCurrentCell = Direction.directionOf(nextCell, currentCell);
		nextCell.eraseWall(directionCurrentCell);

		// uncomment for step by step
		// System.out.println(directionNextCell);

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
