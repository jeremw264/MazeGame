package mazegame.character.npc;

import org.junit.Before;

import mazegame.character.NpcTest;

public class SphinxTest extends NpcTest {

	@Before
	public void setUp() {
		this.npc = new Sphinx(this.x, this.y, this.map);
	}

}
