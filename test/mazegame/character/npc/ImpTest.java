package mazegame.character.npc;

import org.junit.Before;

import mazegame.character.NpcTest;

public class ImpTest extends NpcTest {
	
	@Before
	public void setUp() {
		this.npc = new Imp(this.x,this.y,this.map);
	}
}
