package mazegame.generation;

import org.junit.Before;

public class PrimTest extends GenerationAlgorithmTest {

	@Override
	@Before
	public void setUp() {
		GenerationAlgorithm algorithm = new Prim();
		this.map = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

}
