package mazegame.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputConsoleTest {

	private ConsoleInput input;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		this.input = new ConsoleInput();
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
	public void getStringTest() {
		try {
			System.setIn(new ByteArrayInputStream("hello".getBytes()));
			// Scanner scanner = new Scanner(System.in);
			// System.out.println(scanner.nextLine());
			System.out.println(this.input.getString());
		} finally {
			System.setIn(System.in);
		}
	}

}
