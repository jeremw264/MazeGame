package mazegame;

import java.util.ArrayList;
import java.util.List;

import mazegame.generation.GenerationAlgorithm;

public class Maze {
	private final int width, heigth;

	private List<Cell> board;

	/**
	 * Constructeur de la classe Maze
	 * 
	 * @param heigth Hauteur du labyrinthe
	 * @param width  Largueur du labyrinthe
	 */
	public Maze(int heigth, int width, GenerationAlgorithm genAlgo) {
		this.heigth = heigth;
		this.width = width;
		this.board = new ArrayList<Cell>(this.heigth * this.width);
		this.fillMaze();
		genAlgo.generation(this);

	}

	/**
	 * Renvoie la case qui corresponds au coordonnée passé en paramètre.
	 * 
	 * @param y Coordonne horizontale
	 * @param x Coordonne verticale
	 * @return la case qui correspont au coordonne
	 */
	public Cell getCell(int x, int y) {

		int index = 0;

		if (y == 0) {
			index = x;
		} else if (x == 0) {
			index = y * width;
		} else {
			index = y * width + x;
		}

		return this.board.get(index);
	}

	/**
	 * Renvoie la largueur du labyrinthe.
	 * 
	 * @return la largueur du labyrinthe.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Renvoie la hauteur du labyrinthe.
	 * 
	 * @return la hauteur du labyrinthe.
	 */
	public int getHeigth() {
		return this.heigth;
	}

	/**
	 * Renvoie la liste des cellules du labyrinthe.
	 * 
	 * @return la liste des cellules du labyrinthe.
	 */
	public List<Cell> getBoard() {
		return this.board;
	}

	/**
	 * Renvoie une liste des cellules voisines de la cellule en paramètre.
	 * 
	 * @param currentCell La cellule courante de la quelle on veux obtenir les
	 *                    voisines
	 * @return une liste des cellules voisines.
	 */
	public List<Cell> getNeighborsCells(Cell currentCell) {
		List<Cell> neighborsCells = new ArrayList<Cell>(4);
		int x = currentCell.getX();
		int y = currentCell.getY();

		if (this.board.contains(new Cell(x + 1, y))) {
			neighborsCells.add(this.getCell(x + 1, y));
		}
		if (this.board.contains(new Cell(x - 1, y))) {
			neighborsCells.add(this.getCell(x - 1, y));
		}
		if (this.board.contains(new Cell(x, y + 1))) {
			neighborsCells.add(this.getCell(x, y + 1));
		}
		if (this.board.contains(new Cell(x, y - 1))) {
			neighborsCells.add(this.getCell(x, y - 1));
		}

		return neighborsCells;
	}

	/**
	 * Renvoie une liste des cellules voisines non visité de la cellule en paramètre.
	 * 
	 * @param currentCell La cellule courante de la quelle on veux obtenir les
	 *                    voisines
	 * @return liste des cellules voisines non visité.
	 */
	public List<Cell> getUnvisitedNeighborsCells(Cell currentCell) {

		List<Cell> unvisitedNeighborsCells = new ArrayList<Cell>(4);

		List<Cell> neighborsCells = this.getNeighborsCells(currentCell);

		for (Cell neighborCell : neighborsCells) {
			if (!neighborCell.isVisited()) {
				unvisitedNeighborsCells.add(neighborCell);
			}
		}

		return unvisitedNeighborsCells;
	}

	/**
	 * Renvoie la représentation du labyrinte en chaine de caractère.
	 * 
	 * @return la représentation du labyrinte en chaine de caractère.
	 */
	public String toString() {
		String mazeString = "";

		// First Line

		for (int x = 0; x < this.width; x++) {
			mazeString += "+---";
		}

		mazeString += "+\n";

		// Other Line

		for (int y = 0; y < this.heigth; y++) {

			mazeString += "|";

			for (int x = 0; x < this.width; x++) {
				if (this.getCell(x, y).wallExist("E")) {
					mazeString += "   |";
				} else {
					mazeString += "    ";
				}
			}

			mazeString += "\n+";

			for (int x = 0; x < this.width; x++) {
				if (this.getCell(x, y).wallExist("S")) {
					mazeString += "---+";
				} else {
					mazeString += "   +";
				}
			}

			mazeString += "\n";

		}

		return mazeString;
	}

	/**
	 * Remplie le labyrinthe de case
	 */

	private void fillMaze() {

		for (int i = 0; i < this.heigth * this.width; i++) {

			int x = i % this.width;
			int y = i / this.heigth;

			this.board.add(new Cell(x, y));

		}
	}
}
