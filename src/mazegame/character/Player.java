package mazegame.character;

import mazegame.Cell;

public abstract class Player extends Character {

	protected Player(int seedX, int seedY) {
		super(seedX, seedY);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Deplace le personnage jusque la cellule passer en
	 * paramettre
	 * 
	 * @param nextCell  Cellule de destination
	 */
	public void moveTo(Cell nextCell) {
		Cell currentCell = this.getCurrentCell();
		currentCell.removeCharacter(this);
		
		this.x = nextCell.getX();
		this.y = nextCell.getY();
		
		this.setCurrentCell(nextCell);
		nextCell.setCharacter(this);
		
		nextCell.setVisited();

	}
	
	@Override
	public boolean canBeLeft() {
		// TODO Auto-generated method stub
		return false;
	}

}
