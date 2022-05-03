package mazegame.challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Map;
import mazegame.character.Player;
import mazegame.character.player.Hero;

public class CaseEightThreeTest {

	private Challenge challenge;

	@Before
	public void setUp() throws Exception {
		Map map = new Map(10, 10);
		Player player = new Hero(0, 0, map);
		this.challenge = new CaseEightTree(player);
	}

	@Test
	public void IsOnCorrectCell() {
		Cell nextCell = this.challenge.player.getMap().getCell(8, 3);
		this.challenge.player.move(nextCell);
		
		assertTrue(this.challenge.isFinish());
	}
	
	@Test
	public void IsNotOnCorrectCell() {
		Cell nextCell = this.challenge.player.getMap().getCell(0, 0);
		this.challenge.player.move(nextCell);
		
		assertFalse(this.challenge.isFinish());
	}

}
