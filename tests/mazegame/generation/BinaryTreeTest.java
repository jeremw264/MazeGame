package mazegame.generation;

import org.junit.Before;

public class BinaryTreeTest extends GenerationAlgorithmTest {

	@Override
	@Before
	public void setUp() {
		this.map = new BinaryTree().generation(this.gridWidth, this.gridHeigth);
	}

}
