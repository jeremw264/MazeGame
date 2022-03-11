package mazegame.generation;


import org.junit.Before;

public class PrimTest extends GenerationAlgorithmTest{

	@Before
	public void setUp() {
		GenerationAlgorithm algorithm = new Prim();
		this.grid = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

	

}
