package mazegame.action;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Game;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.player.Hero;
import mazegame.utils.UserInteraction;

public class ActionTest {

	protected Action action;

	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	protected final PrintStream originalOut = System.out;
	protected final PrintStream originalErr = System.err;

	@Before
	public void setUp() {
		this.action = new Action() {

			@Override
			public State run(Character character) {

				String choiseString = Game.INPUT.getString();

				if (choiseString.equals(UserInteraction.EXITWORD_STRING) ) {
					return State.Exit;
				}
				else if (choiseString.equals(UserInteraction.RETURNWORD_STRING)) {
					return State.Cancel;
				}else {

					return State.Ok;
				}
			}
		};
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(this.outContent));
		System.setErr(new PrintStream(this.errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(this.originalOut);
		System.setErr(this.originalErr);
	}

	@Test
	public void playerExitState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		System.setIn(new ByteArrayInputStream(UserInteraction.EXITWORD_STRING.getBytes()));
		State state = this.action.run(playerCharacter);

		assertEquals(state, State.Exit);

	}

	@Test
	public void playerCancelState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		System.setIn(new ByteArrayInputStream(UserInteraction.RETURNWORD_STRING.getBytes()));
		State state = this.action.run(playerCharacter);

		assertEquals(state, State.Cancel);

	}

}
