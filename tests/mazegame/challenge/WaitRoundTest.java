package mazegame.challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.item.Item;

public class WaitRoundTest {

	private Challenge challenge;
	private Player player;

	@Before
	public void setUp() throws Exception {
		Map map = new Map(5, 5);
		this.player = new Hero(0, 0, map);
		this.challenge = new WaitRound(3);
	}

	@Test
	public void waitManyRounds() {
		assertFalse(this.challenge.isCheck(this.player));
		assertFalse(this.challenge.isCheck(this.player));
		assertFalse(this.challenge.isCheck(this.player));

		assertTrue(this.challenge.isCheck(this.player));
	}

	@Test
	public void waitRoundIsPossible() {
		List<Character> characters = new LinkedList<>();
		List<Item> items = new LinkedList<>();
		assertTrue(this.challenge.isPossible(characters, items));
	}

}
