package mazegame;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import mazegame.challenge.WaitRound;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.Kruskal;
import mazegame.item.Jewel;

public class GameBuilderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void setterReturnThis() {
		GenerationAlgorithm algorithm = new Kruskal();
		GameBuilder gameBuilder = new GameBuilder();
		assertSame(gameBuilder, gameBuilder.setMap(5, 5, algorithm));
		assertSame(gameBuilder, gameBuilder.setChallenge(new WaitRound(5)));
		assertSame(gameBuilder, gameBuilder.setPlayer(Hero.class));
		assertSame(gameBuilder, gameBuilder.setItemClass(Jewel.class));
		assertSame(gameBuilder, gameBuilder.setNpcClass(Vendor.class));
	}

	@Test(expected = GameBuilderException.class)
	public void mapIsNotSet() throws GameBuilderException {
		GameBuilder gameBuilder = new GameBuilder();

		gameBuilder.setPlayer(Hero.class).setChallenge(new WaitRound(2));

		gameBuilder.build();
	}

	@Test(expected = GameBuilderException.class)
	public void challengeIsNotSet() throws GameBuilderException {
		GameBuilder gameBuilder = new GameBuilder();
		GenerationAlgorithm algorithm = new Kruskal();

		gameBuilder.setPlayer(Hero.class).setMap(5, 5, algorithm);

		gameBuilder.build();
	}

	@Test
	public void buildReturnGameInstance() throws GameBuilderException {
		GenerationAlgorithm algorithm = new Kruskal();
		GameBuilder gameBuilder = new GameBuilder();
		gameBuilder
			.setMap(5, 5, algorithm)
			.setChallenge(new WaitRound(5))
			.setPlayer(Hero.class)
			.setItemClass(Jewel.class)
			.setNpcClass(Vendor.class);
		
		assertTrue(gameBuilder.build() instanceof Game);
	}

}
