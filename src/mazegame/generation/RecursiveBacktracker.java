package mazegame.generation;

import java.util.List;
import java.util.Stack;

import mazegame.Cell;
import mazegame.Maze;

public class RecursiveBacktracker implements GenerationAlgorithm {
	
	private final int seedX = 0;
	private final int seedY = 0;
	private Stack<Cell> stack = new Stack<Cell>();
	
	public void generation(Maze maze) {
		
		Cell startCell = maze.getCell(this.seedX, this.seedY);
		startCell.setVisited();
		this.stack.push(startCell);
		
		while (this.stack.empty()) {
			Cell currentCell = this.stack.pop();
			List<Cell> unvisitedNeightboursCells = maze.getUnvisitedNeighborsCells(currentCell);
			
			/**
			 * Si il y a une voisine non visité:
			 * 	on choisi une cellule au hasard (Collection.shuffle ?)
			 * 	on detruit le mur entre les 2
			 * 	on ajoute à la pile
			 * 
			 * sinon:
			 * 	on retire la premiere cell de la pile
			 */
		}
	
	}

}
