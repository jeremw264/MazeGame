package mazegame.character.npc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.character.NpcTest;

public class SphinxTest extends NpcTest {

	@Before
	public void setUp() {

		this.npc = new Sphinx(this.x, this.y, this.map);
	}

	// Comme le sphinx ne peut pas bouger si on le force il bouge sur sa case actuel
	@Test
	public void computeNextCellTest() {
		Cell nextCell = this.npc.getCell();
		Cell computeCell = this.npc.computeNextCell();

		assertEquals(nextCell, computeCell);
	}

	@Test
	public void toStringTest() {
		assertEquals(this.npc.toString(), "sphinx");
	}

}
