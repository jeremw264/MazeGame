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
	
	/**
	 * Ajoute la cellule au cellule traité .
	 * @param cell Cellule traité. 
	 */
	protected void addToTreatment(Cell cell) {
		this.cellsTreat.add(cell);
	}
	
	/**
	 * Renvoie si la cellule en paramètre est traiter ou non.
	 * @param cell La cellule dont on veut connaitre l'etat de traitement
	 * @return True si la cellule est traité, False dans le cas contraire.
	 */
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
	protected List<Cell> getUntreatedNeighborsCells(Cell currentCell) {

		List<Cell> untreatedNeighborsCells = new ArrayList<Cell>(4);

		List<Cell> neighborsCells = this.map.getNeighborsCells(currentCell);

		for (Cell neighborCell : neighborsCells) {
			if (!this.isTreated(neighborCell)) {
				untreatedNeighborsCells.add(neighborCell);
			}
		}

		return untreatedNeighborsCells;
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
