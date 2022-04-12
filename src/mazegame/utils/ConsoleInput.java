package mazegame.utils;

import java.util.Scanner;

public class ConsoleInput implements Input {
	
	private Scanner userInput;
	
	public ConsoleInput() {
		this.userInput = new Scanner(System.in);
	}

	@Override
	public String getString() {
		String data = this.userInput.nextLine();
		
		return data;
	}

	@Override
	public void closeInput() {
		this.userInput.close();
		
	}

}
