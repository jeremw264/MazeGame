package mazegame.generation;

import java.util.Random;


import mazegame.*;

public class RecursiveDivision implements GenerationAlgorithm 
{
	private static final int HORIZONTAL = 1;
	private static final int VERTICAL = 2;
	
	private static final int S = 1;
	private static final int E = 2;
	
	private Random rand = new Random();
	private Maze maze;
	
	//divise l'entièreté de la grille
	private void divide(int [][] grid, int x, int y, int width,int height, int orientation)
	{
		//Permet de checker si le lab est parvenue à se divisé jusqu'a la fin
		if(width < 2 || height < 2)
		{
		return;
		}
		
		// Flag pour interroger l'orientation
		boolean horizontal = orientation == HORIZONTAL;
		
		// Ou le mur sera dessiné
		int wx = x + (horizontal ? 0 : rand.nextInt(width - 2));
		int wy = y + (horizontal? rand.nextInt(height -2) : 0);
		
		// ou le passage à travers le mur sera 
		int px = wx + (horizontal ? rand.nextInt(width) : 0);
		int py = wy + (horizontal ? 0 : rand.nextInt(height));
		
		// Dans quel direction le mur sera dessiné
		int dx = horizontal ? 1 : 0;
		int dy = horizontal ? 0 : 1;
		
		// La longueur du mur
		int length = horizontal ? width : height;
		
		// Quel direction est perpendiculer au mur
		int dir = horizontal ? S : E;
		
		// Permet de dessiner le mur
		for (int i = 0; i < length; i++)
		{
			if(wx != px || wy != py)
			{
				grid[wy][wx] |= dir;
			}
			wx += dx;
			wy += dy;
		}
		
		// Détermine les bornes et recurse
		int nx = x;
		int ny = y;
		int w = horizontal ? width : wx - x +1;
		int h = horizontal ? wy - y + 1 : height;
		divide(grid, nx, ny, w, h, chooseOrientation(w, h));
		
		nx = horizontal ? x : wx + 1;
	    ny = horizontal ? wy + 1 : y;
	    w = horizontal ? width : x + width - wx - 1;
	    h = horizontal ? y + height - wy - 1 : height;
	    divide(grid, nx, ny, w, h, chooseOrientation(w, h));
	}
	
	// Simple division pour décidé dans quel sens la direction donné doit être coupé
	private int chooseOrientation(int w, int h) 
	{
	    if(w < h) 
	    {
	        return HORIZONTAL;
	    } 
	    else if (h < w) 
	    {
	        return VERTICAL;
	    } 
	    else 
	    {
	        return rand.nextInt(2) + 1;
	    }
	}
	
	
	public void generation(Maze maze) 
	{
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
