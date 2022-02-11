package mazegame.generation;

import mazegame.*;

/**
 * Class BinaryTree
 */
public class BinaryTree extends GenerationAlgorithm {
	
	/**
	 * Algorithme de Génération : Arbre Binaire
	 * 
	 * @param maze Le labyrinthe qu'on veut modifié
	 * @return 
	 */
	public Grid generation(int width,int heigth) {

		this.grid = new Grid(width, heigth);
		
		int gridHeight = grid.getHeight();
		int gridWidth = grid.getWidth();

		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {

				int random_int = (int) Math.floor(Math.random() * (2));

				Cell currentCell = this.grid.getCell(x, y);

				// Condition de suppresion des walls
				if ((random_int == 1 || y == gridHeight - 1) && x < gridWidth - 1) {
					this.carvePath(currentCell, grid.getCellWithDirection(currentCell, Direction.E));
				} else if (y != gridHeight - 1) {
					this.carvePath(currentCell, grid.getCellWithDirection(currentCell, Direction.S));
				}
			}
		}
		
		return this.grid;
	}
}
