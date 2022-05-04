package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScrollTest extends ItemTest{

	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() {
		this.item = new Scroll("la case finale est en (0,0)");
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(this.outContent));
		System.setErr(new PrintStream(this.errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(this.originalOut);
		System.setErr(this.originalErr);
	}
	
	@Override
	public void isUsableTest() {
		assertTrue(this.item.isUsable());
	}
	
	@Test
	public void useTest() {
		this.item.use();
		assertEquals(this.outContent.toString(), "la case finale est en (0,0)\n");
	}

	@Test
	public void toStringTest() {
		assertEquals("parchemin", this.item.toString());
	}

}
