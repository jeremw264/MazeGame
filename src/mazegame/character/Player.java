package mazegame.character;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;

/**
 * Classe Player
 */
public abstract class Player extends Character {

	/**
	 * Constructeur de l'objet Player.
	 *
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le joueur ce déplace.
	 */
	public Player(int x, int y, Map map) {
		super(x, y, map);
		this.getCell().setVisited();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Renvoie la prochaine cellule où le personnage doit ce déplacer.
	 *
	 * @param Direction La direction de la prochaine cellule.
	 *
	 * @return La prochaine cellule où le personnage doit ce déplacer.
	 */
	abstract public Cell computeNextCell(Direction direction);

	/**
	 * Déplace le joueur sur la carte.
	 */
	public void move(Cell nextCell) {
		this.currentCell.removeCharacter(this);
		nextCell.setVisited();
		this.setCell(nextCell);
		this.x = this.currentCell.getX();
		this.y = this.currentCell.getY();

		this.currentCell.setCharacter(this);
	}
}
