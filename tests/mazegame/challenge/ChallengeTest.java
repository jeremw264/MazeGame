package mazegame.challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.character.Player;
import mazegame.character.player.Hero;

public class ChallengeTest {

	private Challenge challenge;

	private Player player;

	@Before
	public void setUp() throws Exception {
		Map map = new Map(5, 5);
		this.player = new Hero(0, 0, map);
		this.challenge = new Challenge() {
			
			private int i = 0;

			@Override
			public boolean isFinish(Player player) {
				this.i++;
				if (this.i == 2) {
					return true;
				}

				return false;
			}
		};
	}

	@Test
	public void isCheckTest() {
		assertFalse(this.challenge.isCheck(this.player));

		for (int i = 0; i < 5; i++) {
			assertTrue(this.challenge.isCheck(this.player));

		}
	}

}
