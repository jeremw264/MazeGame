package mazegame.item;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ScrollTest extends ItemTest{

	@Before
	public void setUp() {
		this.item = new Scroll();
	}

	@Test
	public void toStringTest() {
		assertEquals("Cet objet n'est pas vendable", this.item.toString());
	}
	

}
