package mazegame;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.challenge.Challenge;
import mazegame.challenge.WaitRound;
import mazegame.character.player.Hero;

public class QuestTest {

	@Before
	public void setUp() {

	}

	@Test
	public void emptyListChallengeFinishedTest() {
		List<Challenge> challenges = new LinkedList<Challenge>();
		Quest quest = new Quest(challenges);
		
		assertTrue(quest.isComplete());
	}
	
	@Test
	public void questIsNotFinished() {
		
		Map map = new Map(2, 2);
		
		List<Challenge> challenges = new LinkedList<Challenge>();
		
		challenges.add(new WaitRound(new Hero(0, 0, map), 2));
		
		Quest quest = new Quest(challenges);
		
		assertFalse(quest.isComplete());
	}
	
	@Test
	public void questIsFinished() {
		
		Map map = new Map(2, 2);
		
		List<Challenge> challenges = new LinkedList<Challenge>();
		
		challenges.add(new WaitRound(new Hero(0, 0, map), 2));
		
		Quest quest = new Quest(challenges);
		
		assertFalse(quest.isComplete());
		assertFalse(quest.isComplete());
		
		assertTrue(quest.isComplete());
	}
	
	
	

}
