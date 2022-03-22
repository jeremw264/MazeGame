package mazegame.generation;


import org.junit.Before;

public class KruskalTest extends GenerationAlgorithmTest{

	@Before
	public void setUp()  {
		GenerationAlgorithm algorithm = new Kruskal();
		this.map = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

}
