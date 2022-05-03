package mazegame.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.action.PlayerChoise;
import mazegame.character.player.Hero;

public class HeroTest {

	private Hero hero;
	
	@Before
	public void setUp() throws Exception {
		Map map = new Map(2, 2);
		this.hero = new Hero(0, 0, map);
	}

	@Test
	public void getActionTest() {
		assertTrue(this.hero.getAction() instanceof PlayerChoise);
	}

}
