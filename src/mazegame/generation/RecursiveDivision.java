package mazegame.generation;

import java.util.Random;

import mazegame.Map;

public class RecursiveDivision extends GenerationAlgorithm
{
	
	@Override
	public Map generation(int width, int height) 
	{
		this.map = new Map(width, height, false);	
		return this.map;
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


