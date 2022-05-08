package mazegame.main;

import java.lang.reflect.InvocationTargetException;

import mazegame.Game;
import mazegame.GameBuilder;
import mazegame.Map;
import mazegame.challenge.GetGoldCoins;
import mazegame.challenge.WaitRound;
import mazegame.character.npc.Imp;
import mazegame.character.npc.Samaritan;
import mazegame.character.npc.Sphinx;
import mazegame.character.npc.Vendor;
import mazegame.character.player.Hero;
import mazegame.exception.GameBuilderException;
import mazegame.generation.GenerationAlgorithm;
import mazegame.generation.Kruskal;
import mazegame.generation.RecursiveBacktracker;
import mazegame.item.GoldCoin;
import mazegame.item.Jewel;
import mazegame.utils.ConsoleDisplayer;
import mazegame.utils.Displayer;

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
		
		Displayer displayer = new ConsoleDisplayer();

		GenerationAlgorithm algorithm;
		
		if (args.length != 1) {
			displayer.displayMsg("Utilisation du programme\n\n\t Paramètre: <numéro de l'algo de génération (1 ou 2)>\n");
			displayer.displayMsg("\t Exemple : \n\t - java -jar jeu.jar 1 \n\t - java -jar jeu.jar 2");
			displayer.displayMsg("\nFermeture du programme.");
			return;
		}

		switch (args[0]) {
		case "1":
			algorithm = new Kruskal();
			break;
		case "2":
			algorithm = new RecursiveBacktracker(0, 0);
			break;

		default:
			algorithm = new Kruskal();
			break;
		}

		GameBuilder gameBuilder = new GameBuilder();

		gameBuilder
			.setMap(10, 10, algorithm)
			.setPlayer(Hero.class)
			.setNpcClass(Vendor.class)
			.setNpcClass(Imp.class)
			.setNpcClass(Sphinx.class)
			.setNpcClass(Samaritan.class)
			.setItemClass(Jewel.class)
			.setItemClass(GoldCoin.class)
			.setChallenge(new WaitRound(5))
			.setChallenge(new GetGoldCoins(5));

		try {
			gameBuilder.build().run();
		} catch (GameBuilderException e) {
			e.printStackTrace();
		}

		// Game game = new Game(5, 5, algorithm);
		// game.run();

	}
}
