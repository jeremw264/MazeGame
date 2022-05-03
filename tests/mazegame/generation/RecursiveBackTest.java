package mazegame.generation;

import org.junit.Before;

public class RecursiveBackTest extends GenerationAlgorithmTest {

	@Before
	public void setUp() {
		GenerationAlgorithm algorithm = new RecursiveBacktracker(0, 0);
		this.map = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

}
