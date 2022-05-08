package mazegame.challenge;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.character.player.Hero;
import mazegame.item.Item;

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
	
	@Test
	public void goToCellEightThreeIsPossible() {

		Map map = new Map(10, 10);
		List<Item> items = new LinkedList<>();
		List<Character> characters = new LinkedList<Character>();
		characters.add((Character) new Player(0, 0, map) {
			
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
		});
		
		assertTrue(this.challenge.isPossible(characters, items));
		
	}
	
	@Test
	public void goToCellEightThreeIsNotPossible() {

		Map map = new Map(5, 5);
		List<Item> items = new LinkedList<>();
		List<Character> characters = new LinkedList<Character>();
		characters.add((Character) new Player(0, 0, map) {
			
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
		});
		
		assertTrue(this.challenge.isPossible(characters, items));
		
	}

}
