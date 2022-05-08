package mazegame.challenge;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.GoldCoin;
import mazegame.item.Item;

public class GetGoldCoinsTest {

	private GetGoldCoins challenge;

	@Before
	public void setUp() throws Exception {
		this.challenge = new GetGoldCoins(5);
	}

	@Test
	public void thePlayerHasNoGoldCoin() {
		Map map = new Map(5, 5);
		Player player = new Player(0, 0, map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		assertFalse(this.challenge.isCheck(player));
	}

	@Test
	public void thePlayerWithFiveGoldCoin() {
		Map map = new Map(5, 5);
		Player player = new Player(0, 0, map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		player.changeCoins(5);

		assertTrue(this.challenge.isCheck(player));
	}

	@Test
	public void thePlayerWithFiveGoldCoinAndLostGoldCoin() {
		Map map = new Map(5, 5);
		Player player = new Player(0, 0, map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		player.changeCoins(5);

		assertTrue(this.challenge.isCheck(player));

		player.changeCoins(-3);

		assertTrue(this.challenge.isCheck(player));
	}

	@Test
	public void thePlayerWithFiveGoldCoinProgressively() {
		Map map = new Map(5, 5);
		Player player = new Player(0, 0, map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		player.changeCoins(3);

		assertFalse(this.challenge.isCheck(player));

		player.changeCoins(-2);

		assertFalse(this.challenge.isCheck(player));

		player.changeCoins(2);
		

		assertEquals(3, player.getCoins());
		assertTrue(this.challenge.isCheck(player));
	}

	@Test
	public void getGoldCoinsIsPossible() {
		List<Character> characters = new LinkedList<Character>();
		List<Item> items = new LinkedList<Item>();

		for (int i = 0; i < 5; i++) {
			items.add(new GoldCoin());

		}

		assertTrue(this.challenge.isPossible(characters, items));
	}

	@Test
	public void getGoldCoinsIsNotPossible() {
		List<Character> characters = new LinkedList<Character>();
		List<Item> items = new LinkedList<Item>();

		assertFalse(this.challenge.isPossible(characters, items));

		for (int i = 0; i < 4; i++) {
			items.add(new GoldCoin());
			assertFalse(this.challenge.isPossible(characters, items));

		}

	}

}
