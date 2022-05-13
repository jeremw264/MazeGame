package mazegame;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.character.Npc;
import mazegame.character.npc.Imp;
import mazegame.character.npc.Samaritan;
import mazegame.character.npc.Vendor;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.utils.ConsoleDisplayer;

public class HintTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

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
	public void itemHintTest() {
		
		Item item = new GoldCoin();
		Map map = new Map(5, 5);
		Npc npc = new Samaritan(0, 0, map);
		
		Hint itemHint = new Hint(item,5);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Tu dois ramasser 5 piece"));
	}
	
	@Test
	public void itemHintTestWithImp() {
		
		Item item = new GoldCoin();
		Map map = new Map(5, 5);
		Npc npc = new Imp(0, 0, map);
		
		Hint itemHint = new Hint(item,5);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Tu dois ramasser 50 bout de calamar"));
	}

	@Test
	public void cellHintTest() {
		Cell cell = new Cell(4, 4);
		Map map = new Map(5, 5);
		Npc npc = new Samaritan(0, 0, map);
		
		Hint itemHint = new Hint(cell);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("L'endroit ou tu dois aller est en " + cell));
	}
	
	@Test
	public void cellHintTestWithImp() {
		Cell cell = new Cell(4, 4);
		Map map = new Map(5, 5);
		Npc npc = new Imp(0, 0, map);
		
		Hint itemHint = new Hint(cell);
		
		itemHint.displayHint(npc);
		
		assertFalse(this.outContent.toString().contains("L'endroit ou tu dois aller est en " + cell));
	}
	
	@Test
	public void npcHintTest() {
		
		Map map = new Map(5, 5);
		Npc vendorNpc = new Vendor(0, 0, map);
		Npc npc = new Samaritan(0, 0, map);
		
		Hint itemHint = new Hint(vendorNpc);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Vous devez trouver un " + vendorNpc));
	}
	
	@Test
	public void npcHintTestWithImp() {
		
		Map map = new Map(5, 5);
		Npc vendorNpc = new Vendor(0, 0, map);
		Npc npc = new Imp(0, 0, map);
		
		Hint itemHint = new Hint(vendorNpc);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Tu dois te trouvéé héhé"));
	}
	
	@Test
	public void waitingHintTest() {
		Item item = new GoldCoin();
		Map map = new Map(5, 5);
		Npc npc = new Samaritan(0, 0, map);
		
		Hint itemHint = new Hint(5);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Vous devez attendre 5 tours de jeu"));
	}
	
	@Test
	public void waitingHintTestWithImp() {
		
		Map map = new Map(5, 5);
		Npc vendorNpc = new Vendor(0, 0, map);
		Npc npc = new Imp(0, 0, map);
		
		Hint itemHint = new Hint(5);
		
		itemHint.displayHint(npc);
		
		assertTrue(this.outContent.toString().contains("Tu dois ne plus bouger sinon tu meurs"));
	}
	

}
