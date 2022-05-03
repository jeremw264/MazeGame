package mazegame.main;

import mazegame.Game;
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
		Game game = new Game(10, 10, algorithm);
		game.run();
	}

}
