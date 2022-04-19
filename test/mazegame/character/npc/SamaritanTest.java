package mazegame.character.npc;

import org.junit.Before;

import mazegame.Map;
import mazegame.character.NpcTest;

public class SamaritanTest extends NpcTest {

	@Before
	public void setUp() {
		this.npc = new Samaritan(this.x, this.y, this.map);
	}

}
