package mazegame.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.Game;

public class UserInteractionTest {

	private UserInteration userInteration;

	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	protected final PrintStream originalOut = System.out;
	protected final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		this.userInteration = new UserInteration();
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


}
