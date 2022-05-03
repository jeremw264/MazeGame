package mazegame.character.npc;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import mazegame.action.Move;
import mazegame.character.NpcTest;

public class VendorTest extends NpcTest {

	@Before
	public void setUp() {
		this.npc = new Vendor(this.x, this.y, this.map);
	}
	
	@Override
	public void getActionTest() {
		assertTrue(this.npc.getAction() instanceof Move);

	}

}
