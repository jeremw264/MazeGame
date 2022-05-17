package mazegame;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import mazegame.challenge.WaitRound;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.generation.Kruskal;
import mazegame.item.Jewel;

public class GameTest {

	private Game game;

	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	protected final PrintStream originalOut = System.out;
	protected final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		GameBuilder gameBuilder = new GameBuilder();
		gameBuilder.setMap(5, 5, new Kruskal()).setChallenge(new WaitRound(5)).setPlayer(Hero.class)
				.setItemClass(Jewel.class).setNpcClass(Vendor.class);

		this.game = gameBuilder.build();
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
	/*
	 * @Test public void runTest() { this.game.run(); System.setIn(new
	 * ByteArrayInputStream("regarder".getBytes()));
	 *
	 * assertEquals(this.outContent.toString(),
	 * "Fin de jeu ...\nMerci d'avoir jouer :)\n");
	 *
	 * }
	 */

}
