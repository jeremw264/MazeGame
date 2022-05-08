package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Hint;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.character.Character;

public class ScrollTest extends ItemTest{


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Override
	@Before
	public void setUp() {
		this.item = new Scroll(new Hint(new Cell(0, 0)));
	}

	@Override
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(this.outContent));
		System.setErr(new PrintStream(this.errContent));
	}

	@Override
	@After
	public void restoreStreams() {
		System.setOut(this.originalOut);
		System.setErr(this.originalErr);
	}

	@Override
	public void isUsableTest() {
		assertTrue(this.item.isUsable());
	}

	@Override
	@Test
	public void useTest() {
		Map map = new Map(4, 5);
		Character character = new Character(0,0,map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		this.item.use(character);
		assertTrue(this.outContent.toString().contains("L'endroit ou tu dois aller est en "));
	}

	@Test
	public void toStringTest() {
		assertEquals("parchemin", this.item.toString());
	}

}
