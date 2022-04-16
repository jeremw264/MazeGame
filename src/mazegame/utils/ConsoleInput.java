package mazegame.utils;

import java.util.Scanner;

/**
 * Classe ConsoleInput.
 * 
 * Classe responsable des entrées en console.
 */
public class ConsoleInput implements Input {

	private Scanner userInput;

	/**
	 * Constructeur de l'objet ConsoleInput.
	 */
	public ConsoleInput() {
		this.userInput = new Scanner(System.in);
	}

	/**
	 * Renvoie la saisie en console sous forme de chaine de caractère.
	 */
	@Override
	public String getString() {
		String data = this.userInput.nextLine();

		return data;
	}

	/**
	 * Ferme le scanner.
	 */
	@Override
	public void closeInput() {
		this.userInput.close();

	}

}
