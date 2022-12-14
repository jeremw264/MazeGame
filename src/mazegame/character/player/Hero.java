package mazegame.character.player;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.PlayerChoice;
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
		return new PlayerChoice();
	}

	/**
	 * Demande à l'utilisateur dans quelle direction déplacer le joueur.
	 *
	 * @return La cellule où le joueur doit ce déplacer.
	 */
	@Override
	public Cell computeNextCell(Direction direction) {
		return this.getMap().getCellWithDirection(this.getCell(), direction);
	}

}
