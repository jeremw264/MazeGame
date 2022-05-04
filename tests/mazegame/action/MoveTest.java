package mazegame.action;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.player.Hero;
import mazegame.utils.UserInteration;

public class MoveTest extends ActionTest {

	@Before
	public void setUp() throws Exception {
		this.action = new Move();
	}

	@Test
	public void characterIsNotPlayerState() {
		Map map = new Map(2, 2);
		Character npcCharacter = new Npc(0, 0, map, null) {

			@Override
			public Cell computeNextCell() {
				// TODO Auto-generated method stub
				return new Cell(getX(), getY());
			}
		};

		State state = this.action.run(npcCharacter);

		assertEquals(state, State.Ok);

	}

	@Test
	public void playerExitState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		System.setIn(new ByteArrayInputStream(UserInteration.EXITWORD_STRING.getBytes()));
		State state = this.action.run(playerCharacter);
		
		assertEquals(state, State.Exit);

	}

	@Test
	public void playerCancelState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		System.setIn(new ByteArrayInputStream(UserInteration.RETURNWORD_STRING.getBytes()));
		State state = this.action.run(playerCharacter);
		
		assertEquals(state, State.Cancel);

	}
	
	@Test
	public void playerCorrectChoiceState() {
		Map map = new Map(2, 2);
		map.getCell(0, 0).eraseWall(Direction.E);
		map.getCell(1, 0).eraseWall(Direction.O);
		Character playerCharacter = new Hero(0, 0, map);
		
		List<Direction> accessibleDirections = playerCharacter.getAccessibleDirections();

		System.setIn(new ByteArrayInputStream(accessibleDirections.get(0).toString().getBytes()));
		State state = this.action.run(playerCharacter);
		
		assertEquals(state, State.Ok);

	}
	
	

}
