package mazegame;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private int width, height;

	private List<Cell> cellsList;

	/**
	 * Constructeur de l'objet Grid.
	 * 
	 * @param width  Largeur de la grille.
	 * @param heigth Hauteur de la grille.
	 */
	public Grid(int width, int heigth) {
		this.width = width;
		this.height = heigth;
		this.initGrid(true);
	}

	/**
	 * Constructeur de l'objet Grid.
	 * 
	 * @param width      Largeur de la grille.
	 * @param heigth     Hauteur de la grille.
	 * @param wallsExist true il les murs interne doivent être généré, false dans le
	 *                   cas contraire.
	 */
	public Grid(int width, int height, boolean wallsExist) {
		this.width = width;
		this.height = height;
		this.initGrid(wallsExist);
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
		int width = this.width;

		if (y == 0) {
			index = x;
		} else if (x == 0) {
			index = y * width;
		} else {
			index = y * width + x;
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
		List<Cell> neighborsCells = new ArrayList<Cell>(4);
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
	 * Crée une représentation de la grille sous la forme d'une liste de cellule.
	 * 
	 * @param wallsExist true si la grille crée doit contenir de mur interne, false
	 *                   dans le cas contraire.
	 */
	private void initGrid(boolean wallsExist) {

		this.cellsList = new ArrayList<>(this.height * this.width);

		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.cellsList.add(new Cell(x, y, wallsExist));
			}
		}

		if (!wallsExist) {
			for (int x = 0; x < this.width; x++) {
				this.getCell(x, 0).createWall(Direction.N);
				;
				this.getCell(x, this.height - 1).createWall(Direction.S);
			}
			for (int y = 0; y < this.height; y++) {
				this.getCell(0, y).createWall(Direction.O);
				;
				this.getCell(this.width - 1, y).createWall(Direction.E);
			}
		}

	}

}
