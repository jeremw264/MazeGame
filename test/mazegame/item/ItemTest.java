package mazegame.item;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	protected Item item;

	@Before
	public void setUp() throws Exception {
		this.item = new Item() {
		};
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
	public void equalsTest() {
		Item item1 = new Item() {
		};

		Item item2 = new Item() {
		};

		int value = 5;

		item1.setValue(value);
		item2.setValue(value);

		assertTrue(item1.equals(item2));
	}

	@Test
	public void notEqualsTest() {
		Item item1 = new Item() {
		};

		Item item2 = new Item() {
		};

		item1.switchSellability();
		item1.setValue(5);
		item2.setValue(10);

		assertFalse(item1.equals(item2));
		
		item2.setValue(5);
		
		assertFalse(item1.equals(item2));
	}
	
	@Test
	public void notEqualsWithOtherObject() {
		Item item1 = new Item() {
		};


		item1.setValue(5);

		assertFalse(item1.equals(new LinkedList<>()));
	}

}
