package mazegame;

public enum Direction {
	N,S,O,E,Unknown;
	
	public static Direction directionOf(Cell currentCell,Cell otherCell) {
		if (currentCell.getX() == otherCell.getX()) {
			if (otherCell.getY() < currentCell.getY()) {
				return Direction.N;
			} else {
				return Direction.S;
			}
		} else if (currentCell.getY() == otherCell.getY()) {
			if (otherCell.getX() < currentCell.getX()) {
				return Direction.O;
			} else {
				return Direction.E;
			}
		} else {
			return Direction.Unknown;
		}
	}
}
