package mazegame.character;

import java.util.ArrayList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;

abstract public class Character {

	public int x,y;
	public Cell currentCell;
	
	public Character(int seedX,int seedY) {
		this.x = seedX;
		this.y = seedY; 
	}

	public Cell getCurrentCell() {
		return this.currentCell;
	}
	
	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}	

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	abstract public boolean canBeLeft();
	
	public List<Direction> getAccesibleDirections() {
		List<Direction> accesibleDirections = new ArrayList<>(4);

		for (Direction direction : Direction.values()) {
			if (!this.currentCell.wallExist(direction)) {
				accesibleDirections.add(direction);
			}
		}

		return accesibleDirections;
	}
}
