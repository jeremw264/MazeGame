package mazegame.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Classe ConsoleInput.
 *
 * Classe responsable des entrées en console.
 */
public class ConsoleInput implements Input {

	private Scanner userInput;

	/**
	 * Renvoie la saisie en console sous forme de chaine de caractère.
	 */
	@Override
	public String getString() {

		this.userInput = new Scanner(System.in);

		return this.userInput.nextLine();
	}

	/**
	 * Ferme le scanner.
	 */
	@Override
	public void closeInput() {
		this.userInput.close();

	}

}
