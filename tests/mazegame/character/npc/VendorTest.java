package mazegame.character.npc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.action.Move;
import mazegame.character.NpcTest;

public class VendorTest extends NpcTest {

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

}
