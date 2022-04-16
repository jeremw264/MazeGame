package mazegame.generation;

import mazegame.*;

/**
 * Class BinaryTree
 * 
 * Algorithme de génération
 */
public class BinaryTree extends GenerationAlgorithm {

	/**
	 * Renvoie un carte (Map) générer avec l'algorithme par arbre binaire.
	 * 
	 * @param width  La largeur de la carte à générer.
	 * @param height La hauteur de la carte à générer.
	 * @return La carte généré.
	 */
	public Map generation(int width, int heigth) {

		this.map = new Map(width, heigth);

		int mapHeight = map.getHeight();
		int mapWidth = map.getWidth();

		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {

				int random_int = (int) Math.floor(Math.random() * (2));

				Cell currentCell = this.map.getCell(x, y);

				// Condition de suppresion des murs
				if ((random_int == 1 || y == mapHeight - 1) && x < mapWidth - 1) {
					this.carvePath(currentCell, map.getCellWithDirection(currentCell, Direction.E));
				} else if (y != mapHeight - 1) {
					this.carvePath(currentCell, map.getCellWithDirection(currentCell, Direction.S));
				}
			}
		}

		return this.map;
	}
}
