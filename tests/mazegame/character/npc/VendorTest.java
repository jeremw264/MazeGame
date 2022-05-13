package mazegame.character.npc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.action.Action;
import mazegame.action.Move;
import mazegame.character.Character;
import mazegame.character.NpcTest;
import mazegame.character.Player;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.item.Jewel;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.UserInteraction;

public class VendorTest extends NpcTest {

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

	@Override
	@Before
	public void setUp() {
		this.map.getCell(0, 0).eraseWall(Direction.E);
		this.map.getCell(1, 0).eraseWall(Direction.O);
		this.npc = new Vendor(this.x, this.y, this.map);
	}

	@Override
	public void getActionTest() {
		assertTrue(this.npc.getAction() instanceof Move);

	}

	@Test
	public void computeNextCellTest() {
		List<Direction> accessibleDirections = this.npc.getAccessibleDirections();
		List<Cell> possibleCells = new LinkedList<>();
		boolean isCell = false;
		for (Direction direction : accessibleDirections) {
			possibleCells.add(this.npc.getMap().getCellWithDirection(this.npc.getCell(), direction));
		}

		Cell nextCell = this.npc.computeNextCell();

		for (Cell cell : possibleCells) {
			if (cell.equals(nextCell)) {
				isCell = true;
			}
		}

		assertTrue(isCell);

	}

	@Test
	public void toStringTest() {
		assertEquals(this.npc.toString(), "vendor");
	}

	@Test
	public void buyOrSellTest() {

		Player player = new Player(this.x, this.y, this.map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		System.setIn(new ByteArrayInputStream((UserInteraction.RETURNWORD_STRING+System.lineSeparator()).getBytes()));
		this.npc.talk();

		assertTrue(this.outContent.toString().contains("vendre"));
		assertTrue(this.outContent.toString().contains("acheter"));
	}

	//@Test
	// Désactivation car pb pour fair un double set in et ne reconnais pas acheter
	public void itemToBuy() {

		Player player = new Player(this.x, this.y, this.map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		Item item = new Item(0, true, false) {
			
			@Override
			public void use(Character character) {
				
			}
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "myItem";
			}
		};

		this.npc.addInv(item);

		String cString = UserInteraction.RETURNWORD_STRING;
			
		System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes()));
		System.setIn(new ByteArrayInputStream("vendre".getBytes()));
		System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes()));
		System.setIn(new ByteArrayInputStream("retour".getBytes()));



		this.npc.talk();
		assertEquals("", this.errContent.toString());
		assertEquals(this.outContent, " ");
		assertTrue(this.outContent.toString().contains("acheter"));

		
		assertTrue(this.outContent.toString().contains(item.toString()));
		
		
		fail();

	}

}
