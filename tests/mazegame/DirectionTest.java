package mazegame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void notValidDirection() {
		Cell cell1 = new Cell(0, 0);
		Cell cell2 = new Cell(1,1);
		assertNull(Direction.directionOf(cell1, cell2));
	}

	@Test
	public void toStringTest() {
		assertEquals("Sud", Direction.S.toString());
		assertEquals("Nord", Direction.N.toString());
		assertEquals("Est", Direction.E.toString());
		assertEquals("Ouest", Direction.O.toString());
	}

}
