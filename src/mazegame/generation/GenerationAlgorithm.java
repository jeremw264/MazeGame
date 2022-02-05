package mazegame.generation;

import mazegame.*;

abstract public class GenerationAlgorithm {

	/**
	 * Méthode de génération de labyrinthe.
	 * 
	 * @param maze Le labyrinthe à modifié.
	 * 
	 *             {@literal Le labyrinthe passé en paramètre doit être rempli d'objet Cell et avoir tout les mur existant.}
	 */
	public abstract void generation(Maze maze);
	
	/**
	 * Détruit les murs entre deux cellule.
	 * 
	 * @param currentCell La cellule actuelle.
	 * @param nextCell    La cellule suivante.
	 */
	protected void carvePath(Cell cell1, Cell cell2) {
		Direction directionNextCell = Direction.directionOf(cell1, cell2);
		cell1.eraseWall(directionNextCell);
		Direction directionCurrentCell = Direction.directionOf(cell2, cell1);
		cell2.eraseWall(directionCurrentCell);

	}
	
	/**
	 * Créé les murs entre deux cellule.
	 * 
	 * @param currentCell La cellule actuelle.
	 * @param nextCell    La cellule suivante.
	 */
	protected void closePath(Cell cell1, Cell cell2) {
		Direction directionNextCell = Direction.directionOf(cell1, cell2);
		cell1.createWall(directionNextCell);
		Direction directionCurrentCell = Direction.directionOf(cell2, cell1);
		cell2.createWall(directionCurrentCell);
	}
}
