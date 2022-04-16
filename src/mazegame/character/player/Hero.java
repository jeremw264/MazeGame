package mazegame.character.player;

import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.PlayerChoise;
import mazegame.character.Player;

/**
 * Classe Hero
 */
public class Hero extends Player {

	/**
	 * Constructeur de l'objet Hero.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le héro ce déplace.
	 */
	public Hero(int x, int y, Map map) {
		super(x, y, map);

	}

	/**
	 * Renvoie une action du personnage.
	 * 
	 * @return Une action du personnage.
	 */
	@Override
	public Action getAction() {
		return new PlayerChoise();
	}

}
