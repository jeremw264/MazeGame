package mazegame.main;

import mazegame.Game;

/**
 * Class MainGame
 */
public class MainGame {

	
	/**
	 * Class d'execution du programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game game = new Game(5, 5);
		game.run();
	}
	
	

}
