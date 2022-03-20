package mazegame.utils;

import java.util.List;

import mazegame.Game;
import mazegame.Maze;

public interface Displayer {

	/**
	 * Affiche un message;
	 */
	public void displayMsg(String message);

	/**
	 * Affiche le titre du jeu.
	 */
	public void displayGameTitle();

	/**
	 * Affiche le labyrinthe.
	 */
	public void displayMaze(Maze maze);

	/**
	 * Affiche l'état du jeu.
	 * 
	 * @param game Le jeu courant
	 */
	public void displayStateGame(Game game);

	/**
	 * Affiche un message d'erreur
	 * @param string
	 */
	public void displayError(String message);

	public void displayHelp(List<String> commmandList);

	public void displayCommand(List<String> choiseList);

	public void displayEndGame();
}
