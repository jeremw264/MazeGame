package mazegame.generation;

import mazegame.*;

/**
 * Class BinaryTree
 */
public class BinaryTree implements GenerationAlgorithm {
	
	/**
	 * Algorithme de Génération : Arbre Binaire
	 */
	public void generation(Maze maze) {

		int mazeHeight = maze.getHeigth();
		int mazeWidth = maze.getWidth();

		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {

				int random_int = (int) Math.floor(Math.random() * (2));

				Cell currentCell = maze.getCell(x, y);

				// Condition de suppresion des walls
				if ((random_int == 1 || y == mazeHeight - 1) && x < mazeWidth - 1) {
					currentCell.eraseWall("E");
				} else if (y != mazeHeight - 1) {
					currentCell.eraseWall("S");
				}
			}
		}
	}
}
