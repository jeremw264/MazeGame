package mazegame.character;

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
		// TODO Auto-generated constructor stub
	}

}
