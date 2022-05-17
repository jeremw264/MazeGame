package mazegame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.action.Action;
import mazegame.challenge.Challenge;
import mazegame.challenge.WaitRound;
import mazegame.character.Player;

public class QuestTest {

	private Player player;

	@Before
	public void setUp() {

	}

	@Test
	public void emptyListChallengeFinishedTest() {
		List<Challenge> challenges = new LinkedList<>();
		Map map = new Map(5, 5);
		this.player = new Player(0, 0, map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		Quest quest = new Quest(this.player, challenges);

		assertTrue(quest.isComplete());
	}

	@Test
	public void questIsNotFinished() {

		Map map = new Map(2, 2);

		List<Challenge> challenges = new LinkedList<>();

		challenges.add(new WaitRound(2));

		Quest quest = new Quest(this.player, challenges);

		assertFalse(quest.isComplete());
	}

	@Test
	public void questIsFinished() {

		Map map = new Map(2, 2);

		List<Challenge> challenges = new LinkedList<>();

		challenges.add(new WaitRound(2));

		Quest quest = new Quest(this.player, challenges);

		assertFalse(quest.isComplete());
		assertFalse(quest.isComplete());

		assertTrue(quest.isComplete());
	}

}
