package mazegame;

/**
 *
 * Classe Direction
 *
 */
public enum Direction {
	/*
	 * N : Nord, S : Sud, O : Ouest, E : Est
	 */
	N("Nord"), S("Sud"), O("Ouest"), E("Est");

	// Description de la direction
	private final String directionDescription;

	/**
	 * Constructeur de l'objet Direction
	 *
	 * @param stringValue Chaîne de caractère qui décrit la direction.
	 */
	private Direction(String stringValue) {
		this.directionDescription = stringValue;
	}

	/**
	 * Renvoie la direction d'une cellule par rapport à une autre.
	 *
	 * @param currentCell La cellule courante.
	 * @param otherCell   La cellule dont on veut connaitre la direction.
	 * @return La direction le l'autre cellule
	 */
	public static Direction directionOf(Cell currentCell, Cell otherCell) {
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
		}
		return null;

	}

	/**
	 * Renvoie une chaîne de caractères qui décrit la direction.
	 */
	@Override
	public String toString() {
		return this.directionDescription;
	}
}
