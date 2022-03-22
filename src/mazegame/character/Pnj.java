package mazegame.character;

import mazegame.Cell;
import mazegame.utils.Discussion;

public abstract class Pnj extends Character {

	protected Pnj(int seedX, int seedY) {
		super(seedX, seedY);
	}

	/**
	 * Renvoie si le personnage peut bouger ou non.
	 * 
	 * @return True si le personnage peut bouger, False sinon.
	 */
	public abstract boolean canBeLeft();
	
	/**
	 * Deplace le personnage jusque la cellule passer en
	 * paramettre
	 * 
	 * @param nextCell  Cellule de destination
	 */
	public void moveTo(Cell nextCell) {
		Cell currentCell = this.getCurrentCell();
		currentCell.removeCharacter(this);
		nextCell.setCharacter(this);

	}
	
	/**
	 * Renvoie la prochaine cellule ou le personnage doit ce deplacer.
	 * 
	 * @return La prochaine cellule.
	 */
	public Cell computeNextCell() {
		return this.currentCell;
	}
	
	/**
	 * Renvoie si le personnage peut parler.
	 * 
	 * @return True si il peut parler, False dans le cas contraire.
	 */
	public abstract boolean canSpeak();
	
	/**
	 * Renvoie un objet discution.
	 * @return un objet discution.
	 */
	public abstract Discussion speak();
	
	
	/**
	 * Renvoie si le personnage peut vendre ou non.
	 * @return
	 */
	public abstract boolean canTrade();

}
