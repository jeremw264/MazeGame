package mazegame.generation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrimTest extends GenerationAlgorithmTest {

	@Before
	public void setUp() {
		GenerationAlgorithm algorithm = new Prim();
		this.map = algorithm.generation(this.gridWidth, this.gridHeigth);
	}

}
