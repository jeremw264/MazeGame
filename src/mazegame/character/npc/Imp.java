package mazegame.character.npc;

import mazegame.Cell;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;
import mazegame.action.Move;
import mazegame.character.Npc;

/**
 * Classe Imp
 * 
 * Personnage Imposteur
 */
public class Imp extends Npc {

	/**
	 * Constructeur de l'objet Imp.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle l'imposteur ce déplace.
	 */
	public Imp(int x, int y, Map map) {
		super(x, y, map);
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
	
	@Override
	public Action getAction() {
		return new Move();
	}

}