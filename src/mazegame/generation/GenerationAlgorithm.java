package mazegame.generation;

import java.util.ArrayList;
import java.util.List;

import mazegame.*;

abstract public class GenerationAlgorithm {

	protected Map map;
	
	protected List<Cell> cellsTreat;
	
	/**
	 * Méthode de génération de grille de labyrinthe.
	 * 
	 * @param width largueur du labyrinthe.
	 * @param heigth hauteur du labyrinthe.
	 * @return grille qui représente le labyrinthe.
	 */
	public abstract Map generation(int width,int heigth);
	
	protected void addToTreatment(Cell cell) {
		this.cellsTreat.add(cell);
	}
	
	protected boolean isTreated(Cell cell) {
		return this.cellsTreat.contains(cell);
	}
	
	/**
	 * Renvoie une liste des cellules voisines non visité de la cellule en
	 * paramètre.
	 * 
	 * @param currentCell La cellule courante de la quelle on veux obtenir les
	 *                    voisines
	 * @return liste des cellules voisines non visité.
	 */
	public List<Cell> getUnvisitedNeighborsCells(Cell currentCell) {

		List<Cell> unvisitedNeighborsCells = new ArrayList<Cell>(4);

		List<Cell> neighborsCells = this.map.getNeighborsCells(currentCell);

		for (Cell neighborCell : neighborsCells) {
			if (!this.isTreated(neighborCell)) {
				unvisitedNeighborsCells.add(neighborCell);
			}
		}

		return unvisitedNeighborsCells;
	}
	
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
