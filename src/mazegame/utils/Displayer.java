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
	 * 
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
	
	public void displayChoise(String firstSentece,List<String> listOfChoises);

	public void displayEndGame();

	public void displayHelp(List<String> keysList);
}
