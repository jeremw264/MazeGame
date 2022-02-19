package mazegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DirectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void notValidDirection() {
		Cell cell1 = new Cell(0, 0);
		Cell cell2 = new Cell(1,1);
		assertNull(Direction.directionOf(cell1, cell2));
	}

}
