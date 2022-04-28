package mazegame.character.npc;

import java.util.Collections;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
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
		super(x, y, map, "Imp");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Renvoie la prochaine cellule où le personnage doit ce déplacer.
	 * 
	 * @return La prochaine cellule où le personnage doit ce déplacer.
	 */
	@Override
	public Cell computeNextCell() {
		
		List<Direction> accessibleDirections = this.getAccessibleDirections();
		
		Collections.shuffle(accessibleDirections);
		
		return this.getMap().getCellWithDirection(this.getCell(), accessibleDirections.get(0));
	}

	/**
	 * Renvoie une action du personnage.
	 * 
	 * Par défault il ne peut que bouger.
	 * 
	 * @return Une action du personnage.
	 */
	@Override
	public Action getAction() {
		return new Move();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "imp";
	}

}