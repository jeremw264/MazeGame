package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.action.Action;
import mazegame.character.Character;

public class JewelTest extends ItemTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Override
	@Before
	public void setUp() {
		this.item = new Jewel();
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
	public void canSellTest() {
		assertTrue(this.item.canSell());
	}

	@Override
	public void switchSellabilityTest() {
		assertTrue(this.item.canSell());
		this.item.switchSellability();
		assertFalse(this.item.canSell());
		this.item.switchSellability();
		assertTrue(this.item.canSell());
	}

	@Test
	public void toStringTest() {
		Jewel jewel1 = new Jewel();
		Jewel jewel2 = new Jewel();
		Jewel jewel3 = new Jewel();
		Jewel jewel4 = new Jewel();
		Jewel jewel5 = new Jewel();
		Jewel jewel6 = new Jewel();

		jewel1.setValue(10);
		jewel2.setValue(30);
		jewel3.setValue(75);
		jewel4.setValue(200);
		jewel5.setValue(400);
		jewel6.setValue(1000);

		assertEquals(jewel1.toString(), "emeraude");
		assertEquals(jewel2.toString(), "saphir");
		assertEquals(jewel3.toString(), "rubis");
		assertEquals(jewel4.toString(), "amethyste");
		assertEquals(jewel5.toString(), "pepite d'or");
		assertEquals(jewel6.toString(), "sac de joyaux");
	}

	@Test
	public void useWithUsebilityEqualsFalse() {
		Map map = new Map(4, 5);
		Character character = new Character(0,0,map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		this.item.use(character);

		assertEquals("", this.outContent.toString());
	}

}
