package mazegame.generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KruskalTest extends GenerationAlgorithmTest{

	@Before
	public void setUp()  {
		GenerationAlgorithm algorithm = new Kruskal();
		this.grid = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

}
