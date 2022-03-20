package mazegame.utils;

import java.util.List;

import mazegame.Game;
import mazegame.Maze;

/**
 * Classe Responsable des affichage en console.
 */
public class ConsoleDisplayer implements Displayer{

	/**
	 * Affiche un message en console
	 * 
	 * @param message
	 */
	public void displayMsg(String message) {
		System.out.println(message);
	}

	public void displayGameTitle() {
		String title = "                                                                     \n"
				+ ",--.   ,--.                       ,----.                             \n"
				+ "|   `.'   | ,--,--.,-----. ,---. '  .-./    ,--,--.,--,--,--. ,---.  \n"
				+ "|  |'.'|  |' ,-.  |`-.  / | .-. :|  | .---.' ,-.  ||        || .-. : \n"
				+ "|  |   |  |\\ '-'  | /  `-.\\   --.'  '--'  |\\ '-'  ||  |  |  |\\   --. \n"
				+ "`--'   `--' `--`--'`-----' `----' `------'  `--`--'`--`--`--' `----' \n"
				+ "                                                                     ";
		this.displayMsg(title);
	}

	@Override
	public void displayMaze(Maze maze) {
		System.out.println(maze);
	}

	@Override
	public void displayStateGame(Game game) {
		
		String message = "Vous êtes sur la case ("+game.getHero().getX()+" , "+game.getHero().getY()+")";
		
		this.displayMsg(message);
	}

	@Override
	public void displayError(String message) {
		System.err.println(message);
		
	}

	@Override
	public void displayHelp(List<String> commmandList) {
		
		this.displayMsg("Les actions disponible sont :");
		
		for (String string : commmandList) {
			this.displayMsg(" - "+string);
		}
		
		this.displayMsg("\n Signification des cases : ");
		this.displayMsg(" - P = vous (Le player) ");
		this.displayMsg(" - # = Les cases non visité\n");
		
	}

	@Override
	public void displayCommand(List<String> choiseList) {
		for (String string : choiseList) {
			this.displayMsg(" - "+string);
		}
		
	}

	

	
}
