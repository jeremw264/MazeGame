package mazegame;

import java.util.HashMap;

/**
 * Class Cell
 */
public class Cell {

	// Représente la position de la cellule.
	private final int x, y;

	// Représente les murs de la cellule.
	private HashMap<Direction, Boolean> walls;

	// Etat de visite de la cellule.
	private boolean visited;

	/**
	 * Constructeur de la classe Cell, les murs sont généré par défault.
	 * 
	 * @param y indice Horizontale de la case
	 * @param x indice Verticale de la case
	 */
	public Cell(int x, int y) {

		this.x = x;
		this.y = y;
		this.walls = new HashMap<>();
		this.walls.put(Direction.N, true);
		this.walls.put(Direction.S, true);
		this.walls.put(Direction.O, true);
		this.walls.put(Direction.E, true);

		this.visited = false;
	}

	/**
	 * Constructeur de la classe Cell.
	 * 
	 * @param y          indice Horizontale de la case
	 * @param x          indice Verticale de la case
	 * @param wallsExist true si les murs doivent etre généré à la création, false
	 *                   dans le cas contraire.
	 */
	public Cell(int x, int y, boolean wallsExist) {

		this.x = x;
		this.y = y;
		this.walls = new HashMap<>();
		this.walls.put(Direction.N, wallsExist);
		this.walls.put(Direction.S, wallsExist);
		this.walls.put(Direction.O, wallsExist);
		this.walls.put(Direction.E, wallsExist);

		this.visited = false;
	}

	/**
	 * Renvoie la position Y (verticale) de la cellule.
	 * 
	 * @return position Y (verticale) de la cellule.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Renvoie la position X (horizontale) de la cellule.
	 * 
	 * @return position X (horizontale) de la cellule.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Definit la cellule a visité.
	 */
	public void setVisited() {
		this.visited = true;
	}

	/**
	 * Renvoie si la cellule est visité ou non.
	 * 
	 * @return true si la celulle est visité, false dans le cas contraire.
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * Efface le mur de la case qui corresponds à la direction en paramètre.
	 * 
	 * @param orientation Direction, exemple: N, S, O, E
	 */
	public void eraseWall(Direction direction) {
		this.walls.replace(direction, false);
	}

	/**
	 * Crée le mur de la case qui corresponds à la direction en paramètre.
	 * 
	 * @param direction Direction
	 */
	public void createWall(Direction direction) {
		this.walls.replace(direction, true);
	}

	/**
	 * Renvoie si un mur existe ou non dans la direction passer en paramètre.
	 * 
	 * @param orientation Direction, exemple: N, S, O, E
	 * @return true si le mur existe, false dans le cas contraire
	 */
	public boolean wallExist(Direction direction) {
		return this.walls.get(direction);
	}

	/**
	 * Description de la cellule sous forme de chaîne de caractère.
	 */
	public String toString() {
		return "Case de coordonnée: x = " + this.x + " y = " + this.y;
	}

	/**
	 * Renvoie si la cellule courante est egal à l'objet en paramètre.
	 * 
	 * @param obj L'objet à comparer.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Cell) {
			Cell otherCell = (Cell) obj;
			return this.x == otherCell.getX() && this.y == otherCell.getY();
		}
		return false;
	}
}
