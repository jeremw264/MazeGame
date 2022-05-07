package mazegame.challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.Map;

public class WaitRoundTest {

	private Challenge challenge;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		Map map = new Map(5, 5);
		this.player = new Hero(0, 0, map);
		this.challenge = new WaitRound( 3);
	}

	@Test
	public void waitManyRounds() {
		assertFalse(this.challenge.isCheck(this.player));
		assertFalse(this.challenge.isCheck(this.player));
		assertFalse(this.challenge.isCheck(this.player));

		assertTrue(this.challenge.isCheck(this.player));
	}

}
