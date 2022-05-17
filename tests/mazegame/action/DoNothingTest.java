package mazegame.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.player.Hero;

public class DoNothingTest extends ActionTest {

	@Override
	@Before
	public void setUp() {
		this.action = new DoNothing();
	}

	@Test
	public void correctState() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);

		assertEquals(this.action.run(playerCharacter), State.Ok);
	}

	// override test inutile

	@Override
	public void playerCancelState() {
	}


	@Override
	public void playerExitState() {
	}

}
