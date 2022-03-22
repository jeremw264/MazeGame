package mazegame.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import mazegame.*;

public class RecursiveBacktracker extends GenerationAlgorithm {

	private final int seedX;
	private final int seedY;
	private Stack<Cell> stack = new Stack<Cell>();

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
	 * Génération du labyrinthe avec l'algorithme Recursive Backtracker.
	 *
	 * @param maze Le labyrinthe qu'on veut modifié.
	 */
	public Map generation(int width,int heigth) {
		
		this.map = new Map(width, heigth);

		Cell startCell = this.map.getCell(this.seedX, this.seedY);
		this.addToTreatment(startCell);
		this.stack.push(startCell);

		while (!this.stack.empty()) {
			Cell currentCell = this.stack.peek();
			List<Cell> unvisitedNeightboursCells = this.getUnvisitedNeighborsCells(currentCell);

			/*
			 * uncomment for see step by step and uncomment in method carvePath
			 */
			// System.out.println(currentCell);
			// System.out.println(maze);

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
	public Cell getRandomNeighBor(Cell currentCell, List<Cell> unvisitedNeightboursCells) {
		Collections.shuffle(unvisitedNeightboursCells);
		return unvisitedNeightboursCells.get(0);

	}

}
