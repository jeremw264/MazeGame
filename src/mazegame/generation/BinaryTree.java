package mazegame.generation;

import mazegame.*;

/**
 * Class BinaryTree
 */
public class BinaryTree extends GenerationAlgorithm {

	public Map generation(int width, int heigth) {

		this.map = new Map(width, heigth);

		int gridHeight = map.getHeight();
		int gridWidth = map.getWidth();

		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {

				int random_int = (int) Math.floor(Math.random() * (2));

				Cell currentCell = this.map.getCell(x, y);

				// Condition de suppresion des walls
				if ((random_int == 1 || y == gridHeight - 1) && x < gridWidth - 1) {
					this.carvePath(currentCell, map.getCellWithDirection(currentCell, Direction.E));
				} else if (y != gridHeight - 1) {
					this.carvePath(currentCell, map.getCellWithDirection(currentCell, Direction.S));
				}
			}
		}

		return this.map;
	}
}
