package mazegame.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Maze;
import mazegame.Wall;

public class Kruskal implements GenerationAlgorithm {

	private Maze maze;
	private List<Wall> wallsList;
	private List<HashSet<Cell>> cellListSets;

	public void generation(Maze maze) {
		this.maze = maze;
		this.init();
		
		for (Wall wall : this.wallsList) {
			Cell cell1 = wall.getCell1();
			Cell cell2 = wall.getCell2();
			this.mergeCellSet(cell1, cell2);
		}
	}
	
	public void init() {
		this.wallsList = this.initWallList();
		this.cellListSets = this.initCellSets();
		
		Collections.shuffle(this.wallsList);
	}

	public List<Wall> initWallList() {
		List<Wall> wallList = new ArrayList<Wall>();

		for (Cell cell : this.maze.getBoard()) {
			for (Cell neighbor : this.maze.getNeighborsCells(cell)) {
				wallList.add(new Wall(cell, neighbor));
			}
		}

		return wallList;
	}

	public List<HashSet<Cell>> initCellSets() {

		List<HashSet<Cell>> cellSets = new ArrayList<>();

		for (Cell cell : this.maze.getBoard()) {

			HashSet<Cell> set = new HashSet<Cell>();
			set.add(cell);

			cellSets.add(set);
		}
		
		return cellSets;
	}
	
	public void mergeCellSet(Cell cell1,Cell cell2) {
		
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
			
			//this.cellListSets.get(iCell1).add(cell2);
			this.cellListSets.remove(iCell2);
		}
	}
	
	public void carvePath(Cell cell1, Cell cell2) {
		Direction directionNextCell = Direction.directionOf(cell1, cell2);
		cell1.eraseWall(directionNextCell);
		Direction directionCurrentCell = Direction.directionOf(cell2, cell1);
		cell2.eraseWall(directionCurrentCell);


	}

}


