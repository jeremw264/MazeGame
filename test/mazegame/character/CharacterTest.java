package mazegame.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.action.Action;
import mazegame.action.DoNothing;
import mazegame.Map;

public class CharacterTest {

	private Character character;
	private Map map;

	@Before
	public void setUp() {
		this.map = new Map(5, 5);
		this.character = new Character(2, 3, this.map) {

			@Override
			public Action getAction() {
				return new DoNothing();
			}
		};
	}

	@Test
	public void getXTest() {
		assertEquals(this.character.getX(), 2);
	}

	@Test
	public void getYTest() {
		assertEquals(this.character.getY(), 3);
	}

	@Test
	public void getMapTest() {
		assertSame(this.map, this.character.getMap());
	}

}
