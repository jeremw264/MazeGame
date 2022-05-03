package mazegame.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;

public class NpcTest {

	protected Npc npc;
	protected Map map = new Map(1, 2);;
	protected final int x = 0;
	protected final int y = 1;

	@Before
	public void setUp() {
		this.npc = new Npc(this.x, this.y, this.map, null) {

			@Override
			public Cell computeNextCell() {
				// TODO Auto-generated method stub
				return new Cell(x, y);
			}
		};
	}

	@Test
	public void npcConstructorTest() {
		assertEquals(this.npc.getX(), this.x);
		assertEquals(this.npc.getY(), this.y);
		assertSame(this.npc.getMap(), this.map);
	}

	@Test
	public void getActionTest() {
		assertTrue(this.npc.getAction() instanceof DoNothing);
	}

}
