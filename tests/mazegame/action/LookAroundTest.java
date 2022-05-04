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
import mazegame.character.npc.Sphinx;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;

public class LookAroundTest extends ActionTest {

	@Before
	public void setUp() throws Exception {
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
		assertTrue(this.outContent.toString().contains("Les directions accessible sont"));
	}
	
	@Test
	public void diretionIsCorrect() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		List<Direction> accessibleDirections = new LinkedList<Direction>();
		List<Direction> notAccessibleDirections = new LinkedList<Direction>();
		
		for (Direction direction : Direction.values()) {
			if (playerCharacter.getAccessibleDirections().contains(direction)) {
				accessibleDirections.add(direction);
			}else {
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

}
