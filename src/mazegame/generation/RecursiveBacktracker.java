package mazegame.generation;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Maze;

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
	}

	/**
	 * Génération du labyrinthe avec l'algorithme Recursive Backtracker.
	 *
	 * @param maze Le labyrinthe qu'on veut modifié.
	 */
	public void generation(Maze maze) {

		Cell startCell = maze.getCell(this.seedX, this.seedY);
		startCell.setVisited();
		this.stack.push(startCell);

		while (!this.stack.empty()) {
			Cell currentCell = this.stack.peek();
			List<Cell> unvisitedNeightboursCells = maze.getUnvisitedNeighborsCells(currentCell);

			/*
			 * uncomment for see step by step and uncomment in method carvePath
			 */
			// System.out.println(currentCell);
			// System.out.println(maze);

			if (!unvisitedNeightboursCells.isEmpty()) {
				Cell nextCell = this.getRandomNeighBor(currentCell, unvisitedNeightboursCells); //
				this.carvePath(currentCell, nextCell);
				nextCell.setVisited();
				this.stack.push(nextCell);
			} else {
				this.stack.pop();
			}
		}
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
