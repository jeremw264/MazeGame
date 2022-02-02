package mazegame.generation;

import java.util.Iterator;
import java.util.Random;

import com.sun.tools.javac.code.Type.ForAll;

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
	
	

	public void generation(Maze maze) 
	{
		this.maze = maze;
		this.eraseAllWall();
		
		// Termine la division récursive techniquement....
		if(width < 2 || height < 2)
		return;
			
	}
	
	public void createWall()
	{
		
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
