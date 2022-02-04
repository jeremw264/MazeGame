package mazegame.generation;

import java.util.Random;


import mazegame.*;

public class RecursiveDivision extends GenerationAlgorithm 
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
		
		// RQ jerem: impossible car width et heigth sont fixé (constante final) non modifiable
		if(width < 2 || height < 2)
		return;
			
	}
	
	public void createWall()
	{
	
		/*
		 * Pour crée un mur je te conseille de passé en paramètre de la fonction x,y, et la direction (Direction class enum)
		 */
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
