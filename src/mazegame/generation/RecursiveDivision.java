package mazegame.generation;

import java.util.Random;

import mazegame.Grid;

public class RecursiveDivision extends GenerationAlgorithm
{
	
	@Override
	public Grid generation(int width, int height) 
	{
		this.grid = new Grid(width, height, false);	
		return this.grid;
	}
	
	private void divide(int x, int y, int width, int height)
	{
		if (width <= 1 && height <= 1)
		{
			return;
		}
		else 
		{
			int c = (int)Math.random();
			if (c == 0) // mur horizontale
			{
				
			}
			else // mur verticale
			{
				//
			}
		}
	}
		
}


