package mazegame.character.npc;

import mazegame.Cell;
import mazegame.Map;
import mazegame.character.Npc;

/**
 * Classe Sphinx
 */
public class Sphinx extends Npc {

	/**
	 * Constructeur de l'objet Sphinx.
	 *
	 * @param x            Position verticale de départ.
	 * @param y            Position horizontale de départ.
	 * @param map          La carte sur laquelle le sphinx ce déplace.
	 * @param DataFileName
	 */
	public Sphinx(int x, int y, Map map) {
		super(x, y, map, "Sphinx");

		// TODO Auto-generated constructor stub
	}

	/**
	 * Renvoie la prochaine cellule où le personnage doit ce déplacer.
	 *
	 * @return La prochaine cellule où le personnage doit ce déplacer.
	 */
	@Override
	public Cell computeNextCell() {
		return new Cell(getX(), getY());
	}

	/**
	 * Renvoie une représentation du npc en chaîne de caractères
	 */
	@Override
	public String toString() {
		return "sphinx";
	}
}