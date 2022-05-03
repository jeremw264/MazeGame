package mazegame.generation;

import org.junit.Before;

public class BinaryTreeTest extends GenerationAlgorithmTest {

	@Before
	public void setUp() {
		this.map = new BinaryTree().generation(this.gridWidth, this.gridHeigth);
	}

}
