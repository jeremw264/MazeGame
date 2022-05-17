package mazegame.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Map;

/**
 * Classe RecursiveBacktracker.
 *
 * Algorithme de génération.
 *
 */
public class RecursiveBacktracker extends GenerationAlgorithm {

	// Point de départ verticale.
	private final int seedX;
	// Point de départ horizontale.
	private final int seedY;

	private Stack<Cell> stack = new Stack<>();

	/**
	 * Constructeur de l'objet RecursiveBacktracker.
	 *
	 * @param seedX Point de départ en X.
	 * @param seedY Point de départ en Y.
	 */
	public RecursiveBacktracker(int seedX, int seedY) {
		this.seedX = seedX;
		this.seedY = seedY;
		this.cellsTreat = new ArrayList<>();
	}

	/**
	 * Renvoie un carte (Map) générer avec l'algorithme RecursiveBacktracker.
	 *
	 * @param width  La largeur de la carte à générer.
	 * @param height La hauteur de la carte à générer.
	 * @return La carte généré.
	 */
	@Override
	public Map generation(int width, int heigth) {

		this.map = new Map(width, heigth);

		Cell startCell = this.map.getCell(this.seedX, this.seedY);
		this.addToTreatment(startCell);
		this.stack.push(startCell);

		while (!this.stack.empty()) {
			Cell currentCell = this.stack.peek();
			List<Cell> unvisitedNeightboursCells = this.getUntreatedNeighborsCells(currentCell);

			if (!unvisitedNeightboursCells.isEmpty()) {
				Cell nextCell = this.getRandomNeighBor(currentCell, unvisitedNeightboursCells); //
				this.carvePath(currentCell, nextCell);
				this.addToTreatment(nextCell);
				this.stack.push(nextCell);
			} else {
				this.stack.pop();
			}
		}

		return this.map;
	}

	/**
	 * Renvoie une cellule voisine aléatoire de la cellule en paramètre.
	 *
	 * @param currentCell               La cellule dont on veut récuperer une
	 *                                  voisine.
	 * @param unvisitedNeightboursCells Liste des cellule voisine.
	 * @return une cellule voisine aléatoire
	 */
	private Cell getRandomNeighBor(Cell currentCell, List<Cell> unvisitedNeightboursCells) {
		Collections.shuffle(unvisitedNeightboursCells);
		return unvisitedNeightboursCells.get(0);

	}

}
