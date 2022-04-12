package mazegame.utils;

import java.util.List;

import mazegame.Game;
import mazegame.Map;

public interface Displayer {

	/**
	 * Affiche un message;
	 */
	public void displayMsg(String message);

	/**
	 * Affiche un message d'erreur
	 * @param string
	 */
	public void displayError(String message);
	
	/**
	 * Affiche le titre du jeu.
	 */
	public void displayGameTitle();

	/**
	 * Affiche le labyrinthe.
	 */
	public void displayMap(Map map);

	/**
	 * Affiche l'état du jeu.
	 * 
	 * @param game Le jeu courant
	 */
	public void displayStateGame(Game game);


	public void displayHelp(List<String> commmandList);

	public void displayCommand(List<String> choiseList);

	public void displayEndGame();
}
