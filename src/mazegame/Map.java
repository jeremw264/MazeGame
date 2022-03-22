package mazegame;

import java.util.ArrayList;
import java.util.List;

public class Map {

	private int width;
	private int height;

	private List<Cell> cellsList;

	/**
	 * Constructeur de l'objet Grid.
	 * 
	 * @param width  Largeur de la grille.
	 * @param heigth Hauteur de la grille.
	 */
	public Map(int width, int heigth) {
		this.width = width;
		this.height = heigth;
		this.initMap(true);
	}

	/**
	 * Constructeur de l'objet Grid.
	 * 
	 * @param width      Largeur de la grille.
	 * @param heigth     Hauteur de la grille.
	 * @param wallsExist true il les murs interne doivent être généré, false dans le
	 *                   cas contraire.
	 */
	public Map(int width, int height, boolean wallsExist) {
		this.width = width;
		this.height = height;
		this.initMap(wallsExist);
	}

	/**
	 * Renvoie la largeur de la grille.
	 * 
	 * @return la largeur de la grille.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Renvoie la hauteur de la grille.
	 * 
	 * @return la hauteur de la grille.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Renvoie la liste des cellules.
	 * 
	 * @return la liste des cellules.
	 */
	public List<Cell> getListsOfCells() {
		return this.cellsList;
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
			index = y * this.width;
		} else {
			index = y * this.width + x;
		}

		if (0 <= index && index < this.cellsList.size()) {
			return this.cellsList.get(index);
		} else {
			return null;
		}

	}

	/**
	 * Renvoie la cellule voisine de la cellule courante en fonction de la direction
	 * 
	 * @param cell      la cellule courante
	 * @param direction la direction de la cellule voisine par rapport a la cellule
	 *                  courante
	 * @return La cellule voisine
	 */
	public Cell getCellWithDirection(Cell cell, Direction direction) {

		int x = cell.getX();
		int y = cell.getY();

		if (direction == Direction.N)
			return this.getCell(x, y - 1);

		if (direction == Direction.S)
			return this.getCell(x, y + 1);

		if (direction == Direction.O)
			return this.getCell(x - 1, y);

		return this.getCell(x + 1, y);

	}

	/**
	 * Renvoie une liste des cellules voisines de la cellule en paramètre.
	 * 
	 * @param currentCell La cellule courante de la quelle on veux obtenir les
	 *                    voisines
	 * @return une liste des cellules voisines.
	 */
	public List<Cell> getNeighborsCells(Cell currentCell) {
		List<Cell> neighborsCells = new ArrayList<>(4);
		int x = currentCell.getX();
		int y = currentCell.getY();

		if (this.cellsList.contains(new Cell(x + 1, y))) {
			neighborsCells.add(this.getCell(x + 1, y));
		}
		if (this.cellsList.contains(new Cell(x - 1, y))) {
			neighborsCells.add(this.getCell(x - 1, y));
		}
		if (this.cellsList.contains(new Cell(x, y + 1))) {
			neighborsCells.add(this.getCell(x, y + 1));
		}
		if (this.cellsList.contains(new Cell(x, y - 1))) {
			neighborsCells.add(this.getCell(x, y - 1));
		}

		return neighborsCells;
	}
	
	/**
	 * Renvoie la représentation du labyrinte en chaine de caractère.
	 * 
	 * @return la représentation du labyrinte en chaine de caractère.
	 */
	public String toString() {

		StringBuilder mazeStringBuilder = new StringBuilder();

		// First Line

		for (int x = 0; x < this.width; x++) {
			mazeStringBuilder.append("+---");
		}

		mazeStringBuilder.append("+\n");

		// Other Line

		for (int y = 0; y < this.height; y++) {

			mazeStringBuilder.append("|");

			for (int x = 0; x < this.width; x++) {
				Cell cell = this.getCell(x, y);
				if (cell.wallExist(Direction.E)) {

					if (cell.isVisited()) {
						mazeStringBuilder.append("   |");
					} else {
						mazeStringBuilder.append(" # |");
					}
				} else {
					if (cell.isVisited()) {
						mazeStringBuilder.append("    ");
					} else {
						mazeStringBuilder.append(" #  ");
					}
				}
			}

			mazeStringBuilder.append("\n+");

			for (int x = 0; x < this.width; x++) {
				if (this.getCell(x, y).wallExist(Direction.S)) {
					mazeStringBuilder.append("---+");
				} else {
					mazeStringBuilder.append("   +");
				}
			}

			mazeStringBuilder.append("\n");

		}

		return mazeStringBuilder.toString();
	}

	/**
	 * Crée une représentation de la grille sous la forme d'une liste de cellule.
	 * 
	 * @param wallsExist true si la grille crée doit contenir de mur interne, false
	 *                   dans le cas contraire.
	 */
	private void initMap(boolean wallsExist) {

		this.cellsList = new ArrayList<>(this.height * this.width);

		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.cellsList.add(new Cell(x, y, wallsExist));
			}
		}

		if (!wallsExist) {
			for (int x = 0; x < this.width; x++) {
				this.getCell(x, 0).createWall(Direction.N);
				this.getCell(x, this.height - 1).createWall(Direction.S);
			}
			for (int y = 0; y < this.height; y++) {
				this.getCell(0, y).createWall(Direction.O);
				this.getCell(this.width - 1, y).createWall(Direction.E);
			}
		}

	}

}
