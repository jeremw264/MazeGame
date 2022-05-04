package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ScrollTest extends ItemTest{

	@Before
	public void setUp() {
		this.item = new Scroll("la case finale est en (0,0)");
	}
	
	@Override
	public void isUsableTest() {
		assertTrue(this.item.isUsable());
	}

	@Test
	public void toStringTest() {
		assertEquals("parchemin", this.item.toString());
	}

}
