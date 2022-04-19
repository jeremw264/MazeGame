package mazegame.character.npc;

import org.junit.Before;

import mazegame.character.NpcTest;

public class VendorTest extends NpcTest {

	@Before
	public void setUp() {
		this.npc = new Vendor(this.x, this.y, this.map);
	}

}
