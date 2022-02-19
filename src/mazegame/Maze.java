package mazegame;

import mazegame.generation.GenerationAlgorithm;

public class Maze {
	private final int width, height;

	private Grid grid;

	/**
	 * Constructeur de la classe Maze
	 * 
	 * @param height Hauteur du labyrinthe
	 * @param width  Largueur du labyrinthe
	 */
	public Maze(int width, int height, GenerationAlgorithm genAlgo) {
		this.width = width;
		this.height = height;
		this.grid = genAlgo.generation(width,height);
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
	public int getHeight() {
		return this.height;
	}

	/**
	 * Renvoie la liste des cellules du labyrinthe.
	 * 
	 * @return la liste des cellules du labyrinthe.
	 */
	public Grid getGrid() {
		return this.grid;
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

		for (int y = 0; y < this.height; y++) {

			mazeString += "|";

			for (int x = 0; x < this.width; x++) {
				Cell cell = this.grid.getCell(x, y);
				if (cell.wallExist(Direction.E)) {
					if (cell.isVisited()) {
						mazeString += "   |";
					}else {
						mazeString += " # |";
					}
				} else {
					if (cell.isVisited()) {
						mazeString += "    ";
					}else {
						mazeString += " #  ";
					}
				}
			}

			mazeString += "\n+";

			for (int x = 0; x < this.width; x++) {
				if (this.grid.getCell(x, y).wallExist(Direction.S)) {
					mazeString += "---+";
				} else {
					mazeString += "   +";
				}
			}

			mazeString += "\n";

		}

		return mazeString;
	}
}
