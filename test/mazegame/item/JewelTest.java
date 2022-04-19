package mazegame.item;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JewelTest extends ItemTest {

	@Before
	public void setUp() {
		this.item = new Jewel();
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

		assertTrue(jewel1.toString().contains("Cette émeraude pèse "));
		assertTrue(jewel2.toString().contains("Ce saphir pèse "));
		assertTrue(jewel3.toString().contains("Ce rubis pèse "));
		assertTrue(jewel4.toString().contains("Cette améthyste pèse "));
		assertTrue(jewel5.toString().contains("Cette pépite d'or pèse "));
		assertTrue(jewel6.toString().contains("Ce sac de joyaux "));

	}

}
