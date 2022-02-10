package mazegame.generation;

import org.junit.Before;

public class BinaryTreeTest extends GenerationAlgorithmTest {

	@Before
	public void setUp() {
		this.grid = new BinaryTree().generation(this.gridWidth, this.gridHeigth);
	}

}
