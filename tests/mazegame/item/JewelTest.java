package mazegame.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class JewelTest extends ItemTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUp() {
		this.item = new Jewel();
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
		this.item.use();
		
		assertEquals("", this.outContent.toString());
	}
	
	@Test
	public void useWithUsebilityEqualsTrue() {
		Jewel jewel1 = new Jewel();
		Jewel jewel2 = new Jewel();
		Jewel jewel3 = new Jewel();
		Jewel jewel4 = new Jewel();
		Jewel jewel5 = new Jewel();
		Jewel jewel6 = new Jewel();

		jewel1.setValue(10);
		jewel1.switchSellability();
		jewel2.setValue(30);
		jewel2.switchSellability();
		jewel3.setValue(75);
		jewel3.switchSellability();
		jewel4.setValue(200);
		jewel4.switchSellability();
		jewel5.setValue(400);
		jewel5.switchSellability();
		jewel6.setValue(1000);
		jewel6.switchSellability();
		
		jewel1.use();
		assertEquals(this.outContent.toString(), "lol");
		assertTrue(this.outContent.toString().contains("Cette émeraude pèse "));
		
		this.setUpStreams();
		
		jewel2.use();
		assertTrue(this.outContent.toString().contains("Ce saphir pèse "));
		
		this.setUpStreams();
		
		jewel3.use();
		assertTrue(this.outContent.toString().contains("Ce rubis pèse " ));
		
		this.setUpStreams();
		
		jewel4.use();
		assertTrue(this.outContent.toString().contains("Cette améthyste pèse "));
		
		this.setUpStreams();
		
		jewel5.use();
		assertTrue(this.outContent.toString().contains("Cette pépite d'or pèse "));
		
		this.setUpStreams();
		
		jewel6.use();
		assertTrue(this.outContent.toString().contains("Ce sac de joyaux contient" ));
		
		
	}

}
