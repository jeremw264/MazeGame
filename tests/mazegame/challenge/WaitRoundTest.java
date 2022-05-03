package mazegame.challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.Map;

public class WaitRoundTest {

	private Challenge challenge;
	
	@Before
	public void setUp() throws Exception {
		Map map = new Map(5, 5);
		Player player = new Hero(0, 0, map);
		this.challenge = new WaitRound(player, 3);
	}

	@Test
	public void waitManyRounds() {
		assertFalse(this.challenge.isCheck());
		assertFalse(this.challenge.isCheck());
		assertFalse(this.challenge.isCheck());

		assertTrue(this.challenge.isCheck());
	}

}
