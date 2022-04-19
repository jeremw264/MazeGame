package mazegame.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Map;

public class ConsoleDisplayerTest {

	/**
	 * 
	 * Pour tester les sorties en console j'ai utiliser le code de cette source !
	 * https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	 * 
	 */

	private Displayer displayer;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		this.displayer = new ConsoleDisplayer();
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(this.outContent));
		System.setErr(new PrintStream(this.errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(this.originalOut);
		System.setErr(this.originalErr);
	}

	@Test
	public void messageTest() {
		String messageString = "Hello";
		this.displayer.displayMsg(messageString);

		assertEquals(messageString + "\n", this.outContent.toString());
	}

	@Test
	public void errorTest() {
		String messageString = "bug";
		this.displayer.displayError(messageString);

		assertEquals(messageString + "\n", this.errContent.toString());
	}

	@Test
	public void endGameMessage() {
		String messageString = "Fin de jeu ...\nMerci d'avoir jouer :)";
		this.displayer.displayEndGame();
		;

		assertEquals(messageString + "\n", this.outContent.toString());
	}

	@Test
	public void displayMapTest() {
		Map map = new Map(2, 2);
		this.displayer.displayMap(map);
		int targetSize = (map.getWidth() * 4 + 1) * (map.getHeight() * 2 + 1) + (2 * map.getHeight() + 1);
		assertEquals(this.outContent.toString().length(), targetSize + 1);
	}

	@Test
	public void choise() {
		String sentence = "On fait un choix";
		List<String> list = new LinkedList<String>();
		list.add("Hello");
		list.add("World");

		this.displayer.displayChoise(sentence, list);

		for (String string : list) {
			assertTrue(this.outContent.toString().contains(string));
		}

		assertTrue(this.outContent.toString().contains(sentence));
	}

	@Test
	public void help() {

		List<String> list = new LinkedList<String>();
		list.add("Hello");
		list.add("World");

		this.displayer.displayHelp(list);

		for (String string : list) {
			assertTrue(this.outContent.toString().contains(string));
		}

		assertTrue(this.outContent.toString().contains("Voici les actions possibles : "));
	}

}
