package mazegame;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private int width, height;

	private List<Cell> cellsList;

	public Grid(int width, int heigth) {
		this.width = width;
		this.height = heigth;
		this.initGrid(true);
	}

	public Grid(int width, int height, boolean wallsExist) {
		this.width = width;
		this.height = height;
		this.initGrid(wallsExist);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public List<Cell> getListsOfCells () {
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

		return this.cellsList.get(index);
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
	 * Crée une représentation de la grille sous la forme d'une liste de cellule
	 * 
	 * @param wallsExist
	 */
	private void initGrid(boolean wallsExist) {

		this.cellsList = new ArrayList<>(this.height * this.width);

		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.cellsList.add(new Cell(x, y, wallsExist));
			}
		}
		

	}

}
