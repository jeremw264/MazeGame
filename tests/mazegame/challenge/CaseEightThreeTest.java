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

	private Player player;
	
	@Before
	public void setUp() throws Exception {
		Map map = new Map(10, 10);
		this.player = new Hero(0, 0, map);
		this.challenge = new CaseEightTree();
	}

	@Test
	public void IsOnCorrectCell() {
		Cell nextCell = this.player.getMap().getCell(8, 3);
		this.player.move(nextCell);
		
		assertTrue(this.challenge.isFinish(this.player));
	}
	
	@Test
	public void IsNotOnCorrectCell() {
		Cell nextCell = this.player.getMap().getCell(0, 0);
		this.player.move(nextCell);
		
		assertFalse(this.challenge.isFinish(this.player));
	}

}
