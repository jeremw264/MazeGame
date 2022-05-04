package mazegame.action;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Game;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.player.Hero;

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
	public void characterIsPlayerState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		InputStream stdin = System.in;
		ByteArrayInputStream d = new ByteArrayInputStream("quitter".getBytes());
		System.setIn(d);
		State state = this.action.run(playerCharacter);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(byteArrayOutputStream);
		PrintStream stdout = System.out;
		System.setOut(ps);
		
		assertEquals(state, State.Exit);

	}

}
