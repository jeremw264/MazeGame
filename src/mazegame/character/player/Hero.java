package mazegame.character.player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
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

	/**
	 * Demande à l'utilisateur dans quelle direction déplacer le joueur.
	 * 
	 * @return La cellule où le joueur doit ce déplacer.
	 */
	@Override
	public Cell computeNextCell() {

		String choise;

		java.util.Map<String, Direction> directionChoiseMap = new HashMap<>();

		for (Direction direction : this.getAccessibleDirections()) {
			directionChoiseMap.put(direction.toString(), direction);
		}

		List<String> keysList = new LinkedList<String>(directionChoiseMap.keySet());

		do {

			Game.DISPLAYER.displayChoise("Dans quelle direction voulez-vous aller", keysList);

			choise = Game.INPUT.getString();

			if (!directionChoiseMap.containsKey(choise)) {
				Game.DISPLAYER.displayError("Choix non valide\n");
			}

		} while (!directionChoiseMap.containsKey(choise));

		return this.getMap().getCellWithDirection(this.getCell(), directionChoiseMap.get(choise));
	}

}
