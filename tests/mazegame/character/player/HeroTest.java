package mazegame.character.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.PlayerChoise;

public class HeroTest {

	private Hero hero;

	@Before
	public void setUp() throws Exception {
		Map map = new Map(2, 2);
		map.getCell(0, 0).eraseWall(Direction.E);
		map.getCell(1, 0).eraseWall(Direction.O);

		this.hero = new Hero(0, 0, map);
	}

	@Test
	public void getActionTest() {
		assertTrue(this.hero.getAction() instanceof PlayerChoise);
	}

	@Test
	public void computeNextCellTest() {
		List<Direction> accessibleDirections = this.hero.getAccessibleDirections();
		Cell currentCell = this.hero.getCell();
		Cell nextCell = this.hero.getMap().getCellWithDirection(currentCell, accessibleDirections.get(0));

		Cell computeCell =  this.hero.computeNextCell(accessibleDirections.get(0));

		assertEquals(computeCell, nextCell);

	}

}