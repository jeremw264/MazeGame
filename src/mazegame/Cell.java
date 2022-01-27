package mazegame;

import java.util.HashMap;

/**
 * Class Cell
 */
public class Cell {

	private final int x, y;
	private HashMap<String, Boolean> walls;

	/**
	 * Constructeur de la classe Cell
	 * 
	 * @param y indice Horizontale de la case
	 * @param x indice Verticale de la case
	 */
	public Cell(int x, int y) {

		this.x = x;
		this.y = y;
		this.walls = new HashMap<>();
		this.walls.put("N", true);
		this.walls.put("S", true);
		this.walls.put("O", true);
		this.walls.put("E", true);

	}

	/**
	 * Renvoie la position Y (verticale) de la cellule.
	 * @return position Y (verticale) de la cellule.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Renvoie la position X (horizontale) de la cellule.
	 * @return position X (horizontale) de la cellule.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Efface le mur de la case qui corresponds à la direction en paramètre.
	 * 
	 * @param orientation Direction, exemple: N, S, O, E
	 */
	public void eraseWall(String orientation) {
		this.walls.replace(orientation, false);
	}

	/**
	 * Renvoie si un mur existe ou non dans la direction passer en paramètre.
	 * 
	 * @param orientation Direction, exemple: N, S, O, E
	 * @return true si le mur existe, false dans le cas contraire
	 */
	public boolean wallExist(String orientation) {
		if (this.walls.containsKey(orientation)) {
			return this.walls.get(orientation);
		} else {
			return true;
		}
	}

	/**
	 * Description de la cellule sous forme de chaîne de caractère.
	 */
	public String toString() {
		return "Case de coordonnée: x = " + this.x + " y = " + this.y;
	}

	/**
	 * Renvoie si la cellule courante est egal à l'objet en paramètre.
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
