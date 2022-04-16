package mazegame.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.action.Action;

public class NpcTest {

	private Npc npc;
	private Map map;
	private final int x = 0;
	private final int y = 1;
	
	@Before
	public void setUp() {
		this.map = new Map(1, 2);
		this.npc = new Npc(this.x,this.y,this.map) {
			
			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void npcConstructorTest() {
		assertEquals(this.npc.getX(), this.x);
		assertEquals(this.npc.getY(), this.y);
		assertSame(this.npc.getMap(), this.map);
	}

}
