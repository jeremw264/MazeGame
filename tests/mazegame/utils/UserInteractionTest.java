package mazegame.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mazegame.State;

public class UserInteractionTest {

	private UserInteraction userInteration;

	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	protected final PrintStream originalOut = System.out;
	protected final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		this.userInteration = new UserInteraction();
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
	public void getChoiseWithoutDescription() {
		List<String> choiseList = new LinkedList<>();
		choiseList.add("c1");
		choiseList.add("c2");

		System.setIn(new ByteArrayInputStream(choiseList.get(0).getBytes()));

		UserInteraction.getChoise("Saisir choix :", choiseList, false);

		for (String string : choiseList) {
			assertTrue(this.outContent.toString().contains(string));
		}

		assertTrue(this.outContent.toString().contains("Saisir choix :"));
	}

	@Test
	public void getChoiseWithDescription() {
		List<String> choiseList = new LinkedList<>();
		choiseList.add("c1");
		choiseList.add("c2");

		List<String> descriptionList = new LinkedList<>();
		descriptionList.add(" | description c1");
		descriptionList.add(" | description c2");

		System.setIn(new ByteArrayInputStream(choiseList.get(0).getBytes()));

		UserInteraction.getChoise("Saisir choix :", choiseList, descriptionList, false);

		for (String string : choiseList) {
			assertTrue(this.outContent.toString().contains(string));
		}

		for (String string : descriptionList) {
			assertTrue(this.outContent.toString().contains(string));
		}

		assertTrue(this.outContent.toString().contains("Saisir choix :"));
	}

	@Test
	public void okState() {
		List<String> choiseList = new LinkedList<>();
		choiseList.add("c1");
		choiseList.add("c2");

		List<String> descriptionList = new LinkedList<>();
		descriptionList.add(" | description c1");
		descriptionList.add(" | description c2");

		System.setIn(new ByteArrayInputStream(choiseList.get(0).getBytes()));

		Map<String,Object> responceMap = UserInteraction.getChoise("Saisir choix :", choiseList, descriptionList, false);

		assertEquals(responceMap.get("STATE"), State.Ok);
		
	}
	
	@Test
	public void cancelState() {
		List<String> choiseList = new LinkedList<>();
		choiseList.add("c1");
		choiseList.add("c2");

		List<String> descriptionList = new LinkedList<>();
		descriptionList.add(" | description c1");
		descriptionList.add(" | description c2");

		System.setIn(new ByteArrayInputStream(UserInteraction.RETURNWORD_STRING.getBytes()));

		Map<String,Object> responceMap = UserInteraction.getChoise("Saisir choix :", choiseList, descriptionList, true);

		assertEquals(responceMap.get("STATE"), State.Cancel);
	}

	@Test
	public void exitState() {
		List<String> choiseList = new LinkedList<>();
		choiseList.add("c1");
		choiseList.add("c2");

		List<String> descriptionList = new LinkedList<>();
		descriptionList.add(" | description c1");
		descriptionList.add(" | description c2");

		System.setIn(new ByteArrayInputStream(UserInteraction.EXITWORD_STRING.getBytes()));

		Map<String,Object> responceMap = UserInteraction.getChoise("Saisir choix :", choiseList, descriptionList, false);

		assertEquals(responceMap.get("STATE"), State.Exit);
	}

}
