package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.character.Character;

public class ItemTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	protected Item item;

	@Before
	public void setUp() throws Exception {
		this.item = new Item(0, false, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};
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

	@Test
	public void canSellTest() {
		assertFalse(this.item.canSell());
	}

	@Test
	public void switchSellabilityTest() {
		assertFalse(this.item.canSell());
		this.item.switchSellability();
		assertTrue(this.item.canSell());
		this.item.switchSellability();
		assertFalse(this.item.canSell());

	}

	@Test
	public void isUsableTest() {
		assertFalse(this.item.isUsable());
	}

	@Test
	public void setValueTest() {
		int value = 5;
		this.item.setValue(value);

		assertEquals(this.item.value, value);
	}

	@Test
	public void getValueTest() {
		int value = 5;
		this.item.setValue(value);

		assertEquals(this.item.getValue(), value);
	}

	@Test
	public void useTest() {
		assertEquals("", this.outContent.toString());
	}

	@Test
	public void equalsTest() {
		Item item1 = new Item(5, false, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};

		Item item2 = new Item(5, false, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};

		assertTrue(item1.equals(item2));
	}

	@Test
	public void notEqualsTest() {
		Item item1 = new Item(5, false, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};

		Item item2 = new Item(10, true, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};

		assertFalse(item1.equals(item2));

		item2.setValue(5);

		assertFalse(item1.equals(item2));
	}

	@Test
	public void notEqualsWithOtherObject() {
		Item item1 = new Item(0, false, false) {

			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub

			}
		};

		item1.setValue(5);

		assertFalse(item1.equals(new LinkedList<>()));
	}

}
