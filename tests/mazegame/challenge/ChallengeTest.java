package mazegame.challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Hint;
import mazegame.Map;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.item.Item;

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

			@Override
			public boolean isPossible(List<Character> characters, List<Item> items) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Hint getHint() {
				// TODO Auto-generated method stub
				return new Hint();
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
