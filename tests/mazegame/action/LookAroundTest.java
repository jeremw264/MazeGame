package mazegame.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.npc.Samaritan;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.item.Item;
import mazegame.item.Jewel;

public class LookAroundTest extends ActionTest {

	@Override
	@Before
	public void setUp() {
		this.action = new LookAround();
	}

	@Test
	public void characterIsNotPlayer() {
		Map map = new Map(2, 2);
		Character npcCharacter = new Npc(0, 0, map, null) {

			@Override
			public Cell computeNextCell() {
				// TODO Auto-generated method stub
				return new Cell(getX(), getY());
			}
		};

		State state = this.action.run(npcCharacter);

		assertEquals(state, State.Exit);

		assertEquals("Cette action doit être executé par un Player\n", this.errContent.toString());
	}

	@Test
	public void characterIsPlayer() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		this.action.run(playerCharacter);

		assertTrue(this.outContent.toString().contains("Vous êtes sur la case"));
		assertTrue(this.outContent.toString().contains("Les directions accessibles sont"));
	}

	@Test
	public void diretionIsCorrect() {
		Map map = new Map(4, 4);
		Character playerCharacter = new Hero(0, 0, map);
		List<Direction> accessibleDirections = new LinkedList<>();
		List<Direction> notAccessibleDirections = new LinkedList<>();

		for (Direction direction : Direction.values()) {
			if (playerCharacter.getAccessibleDirections().contains(direction)) {
				accessibleDirections.add(direction);
			} else {
				notAccessibleDirections.add(direction);
			}
		}

		this.action.run(playerCharacter);

		for (Direction direction : accessibleDirections) {
			assertTrue(this.outContent.toString().contains(direction.toString()));
		}

		for (Direction direction : notAccessibleDirections) {
			assertFalse(this.outContent.toString().contains(direction.toString()));

		}

	}

	@Test
	public void stateReturnedWithPlayer() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		State state = this.action.run(playerCharacter);
		assertEquals(State.Ok, state);
	}

	@Test
	public void stateReturnedWithNpc() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Vendor(0, 0, map);
		State state = this.action.run(playerCharacter);
		assertEquals(State.Exit, state);
	}

	@Test
	public void inventoryIsNotEmpty() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		Item item = new Jewel();

		playerCharacter.addInv(item);
		this.action.run(playerCharacter);

		assertTrue(this.outContent.toString().contains("Tu as dans ton inventaire :"));
		assertTrue(this.outContent.toString().contains(item.toString()));
	}

	@Test
	public void inventoryIsEmpty() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		this.action.run(playerCharacter);

		assertFalse(this.outContent.toString().contains("Tu as dans ton inventaire :"));
	}
	
	@Test
	public void npcAround() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		Npc npc = new Samaritan(0, 0, map);

		this.action.run(playerCharacter);

		assertTrue(this.outContent.toString().contains("Voici les personnages autour de vous :"));
		assertTrue(this.outContent.toString().contains(npc.toString()));

	}
	
	@Test
	public void npcNotAround() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		this.action.run(playerCharacter);

		assertFalse(this.outContent.toString().contains("Voici les personnages autour de vous :"));
	}
	
	@Test
	public void itemAround() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		Item item = new Jewel();
		map.getCell(0, 0).addItem(item);

		this.action.run(playerCharacter);

		assertTrue(this.outContent.toString().contains("Voici les objets autour de vous :"));
		assertTrue(this.outContent.toString().contains(item.toString()));

	}
	
	@Test
	public void itemNotAround() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		this.action.run(playerCharacter);

		assertFalse(this.outContent.toString().contains("Voici les objets autour de vous :"));
	}

	// Overide test inutile
	@Override
	public void playerCancelState() {
	}

	@Override
	public void playerExitState() {
	}

}
