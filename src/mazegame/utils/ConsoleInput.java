package mazegame.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Classe ConsoleInput.
 * 
 * Classe responsable des entrées en console.
 */
public class ConsoleInput implements Input {

	private BufferedReader userInput;

	/**
	 * Constructeur de l'objet ConsoleInput.
	 */
	public ConsoleInput() {
		// this.userInput = new Scanner(System.in);
		//this.userInput = new Scanner(System.in);
		
	}

	/**
	 * Renvoie la saisie en console sous forme de chaine de caractère.
	 */
	@Override
	public String getString() {

		this.userInput = new BufferedReader(new InputStreamReader(System.in));
		String data = "";
		try {
			data = this.userInput.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return data;
	}

	/**
	 * Ferme le scanner.
	 */
	@Override
	public void closeInput() {
		//this.userInput.close();

	}

}
