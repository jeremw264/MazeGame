package mazegame.main;

import mazegame.Game;
import mazegame.GameBuilder;
import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.Kruskal;

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

		gameBuilder.setMap(10, 10, algorithm)
				.setPlayer("mazegame.character.player.Hero")
				.setNpcClass("mazegame.character.npc.Vendor")
				.setNpcClass("mazegame.character.npc.Imp")
				.setNpcClass("mazegame.character.npc.Sphinx")
				.setNpcClass("mazegame.character.npc.Samaritan");
		
		gameBuilder.build().run();

		/*
		 * GenerationAlgorithm algorithm = new Kruskal(); Game game = new Game(10, 10,
		 * algorithm); game.run();
		 */
	}

}
