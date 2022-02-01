package mazegame.generation;

import mazegame.*;

public class RecursiveDivision implements GenerationAlgorithm 
{
	private final int width;
	private final int height;
	private Maze maze;

	public RecursiveDivision(int width, int height) 
	{
		this.width = width;
		this.height = height;

	}

	public void generation(Maze maze) {
		this.maze = maze;
		this.eraseAllWall();
	}

	public void eraseAllWall() 
	{
		for (Cell cell : this.maze.getBoard()) 
		{
			for (Direction cellDirection : Direction.values()) 
			{
				cell.eraseWall(cellDirection);
			}
		}
	}
	
	
}
