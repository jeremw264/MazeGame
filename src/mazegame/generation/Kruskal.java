package mazegame.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import mazegame.Cell;
import mazegame.Map;

/**
 * Classe Kruskal.
 *
 * Algorithme de génération.
 *
 */
public class Kruskal extends GenerationAlgorithm {

	// Représente la liste des murs.
	private List<List<Cell>> wallsList;

	// Représente les ensemble de cellules.
	private List<HashSet<Cell>> cellListSets;

	/**
	 * Renvoie un carte (Map) générer avec l'algorithme de kruskal.
	 *
	 * @param width  La largeur de la carte à générer.
	 * @param height La hauteur de la carte à générer.
	 * @return La carte généré.
	 */
	@Override
	public Map generation(int width, int heigth) {
		this.map = new Map(width, heigth);

		this.wallsList = this.initWallList();
		this.cellListSets = this.initCellSets();

		Collections.shuffle(this.wallsList);

		for (List<Cell> wall : this.wallsList) {
			Cell cell1 = wall.get(0);
			Cell cell2 = wall.get(1);
			this.mergeCellSet(cell1, cell2);
		}

		return this.map;
	}

	/**
	 * Renvoie une liste de tout les murs existant dans le labyrinthe
	 *
	 * @return une liste de tout les murs existant dans le labyrinthe
	 */
	private List<List<Cell>> initWallList() {
		List<List<Cell>> wallList = new ArrayList<>();

		for (Cell cell : this.map.getListsOfCells()) {
			for (Cell neighbor : this.map.getNeighborsCells(cell)) {
				List<Cell> wall = new ArrayList<>(2);
				wall.add(cell);
				wall.add(neighbor);
				wallList.add(wall);
			}
		}

		return wallList;
	}

	/**
	 * Renvoie une liste où chaque cellule est contenu dans un set
	 *
	 * @return une liste où chaque cellule est contenu dans un set
	 */
	private List<HashSet<Cell>> initCellSets() {

		List<HashSet<Cell>> cellSets = new ArrayList<>();

		for (Cell cell : this.map.getListsOfCells()) {

			HashSet<Cell> set = new HashSet<>();
			set.add(cell);
			cellSets.add(set);
		}

		return cellSets;
	}

	/**
	 * Fusionne les ensembles de cellule si les deux cellule en paramètre sont dans
	 * des ensembles disjoint
	 *
	 * @param cell1
	 * @param cell2
	 */
	private void mergeCellSet(Cell cell1, Cell cell2) {

		int iCell1 = 0;
		int iCell2 = 0;

		for (HashSet<Cell> hashSet : this.cellListSets) {
			if (hashSet.contains(cell1)) {
				iCell1 = this.cellListSets.indexOf(hashSet);
			}

			if (hashSet.contains(cell2)) {
				iCell2 = this.cellListSets.indexOf(hashSet);
			}
		}

		if (iCell1 != iCell2) {
			this.carvePath(cell1, cell2);

			for (Cell cell : this.cellListSets.get(iCell2)) {
				this.cellListSets.get(iCell1).add(cell);
			}

			this.cellListSets.remove(iCell2);
		}
	}

}
