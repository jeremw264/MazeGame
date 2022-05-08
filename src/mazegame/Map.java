package mazegame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe qui represente la carte du jeu.
 */
public class Map {

	// Largueur de la carte.
	private int width;

	// Hauteur de la carte.
	private int height;

	// Liste des cases qui compose la carte.
	private List<Cell> cellsList;

	/**
	 * Constructeur de l'objet Grid.
	 *
	 * @param width  Largeur de la carte.
	 * @param heigth Hauteur de la carte.
	 */
	public Map(int width, int heigth) {
		this.width = width;
		this.height = heigth;
		this.initMap(true);
	}

	/**
	 * Constructeur de l'objet Grid.
	 *
	 * @param width      Largeur de la carte.
	 * @param heigth     Hauteur de la carte.
	 * @param wallsExist true il les murs interne doivent être généré, false dans le
	 *                   cas contraire.
	 */
	public Map(int width, int height, boolean wallsExist) {
		this.width = width;
		this.height = height;
		this.initMap(wallsExist);
	}

	/**
	 * Renvoie la largeur de la carte.
	 *
	 * @return la largeur de la carte.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Renvoie la hauteur de la carte.
	 *
	 * @return la hauteur de la carte.
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
	 * @return la case qui correspont au coordonne, null si elle n'est pas dans la
	 *         carte.
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
	 *
	 * @return La cellule voisine dans la direction choisie, null si la cellule
	 *         n'existe pas
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
	 *                    voisines.
	 *
	 * @return Une liste des cellules voisines.
	 */
	public List<Cell> getNeighborsCells(Cell currentCell) {
		List<Cell> neighborsCells = new LinkedList<>();
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
	@Override
	public String toString() {

		StringBuilder mazeStringBuilder = new StringBuilder();

		// La premiere ligne

		for (int x = 0; x < this.width; x++) {
			mazeStringBuilder.append("+---");
		}

		mazeStringBuilder.append("+\n");

		// Les autres ligne

		for (int y = 0; y < this.height; y++) {

			mazeStringBuilder.append("|");

			for (int x = 0; x < this.width; x++) {
				Cell cell = this.getCell(x, y);
				String symString = " ";
				if (cell.containsPlayer()) {
					symString = "P";
				} else if (!cell.isVisited()) {
					symString = "#";
				}

				if (cell.wallExist(Direction.E)) {

					mazeStringBuilder.append(" " + symString + " |");

				} else {
					mazeStringBuilder.append(" " + symString + "  ");
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
	 * Crée une représentation de la carte sous la forme d'une liste de cellule.
	 *
	 * @param wallsExist true si la carte crée doit contenir de mur interne, false
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
