package mazegame.main;

import java.lang.reflect.InvocationTargetException;

import mazegame.Game;
import mazegame.GameBuilder;
import mazegame.Map;
import mazegame.character.npc.Imp;
import mazegame.character.npc.Samaritan;
import mazegame.character.npc.Sphinx;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.Kruskal;
import mazegame.item.Jewel;

/**
 * Class MainGame
 */
public class MainGame {

	/**
	 * Point d'entré du programme.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		GenerationAlgorithm algorithm = new Kruskal();
		GameBuilder gameBuilder = new GameBuilder();

		gameBuilder
			.setMap(10, 10, algorithm)
			.setPlayer(Hero.class)
			.setNpcClass(Vendor.class)
			.setNpcClass(Imp.class)
			.setNpcClass(Sphinx.class)
			.setNpcClass(Samaritan.class)
			.setItemClass(Jewel.class);

		Game game = null;
		try {
			game = gameBuilder.build();
		} catch (GameBuilderException e) {
			e.printStackTrace();
		}

		game.run();

	}
}
