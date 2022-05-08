package mazegame.character;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;

public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {

		Map map = new Map(2, 2);

		this.player = new Player(0,0,map) {

			@Override
			public Action getAction() {
				// TODO Auto-generated method stub
				return new DoNothing();
			}

			@Override
			public Cell computeNextCell(Direction direction) {
				// TODO Auto-generated method stub
				return this.getCell();
			}
		};
	}

	@Test
	public void moveToGoodCell() {

		Cell nextCell = this.player.getMap().getCell(1, 1);

		this.player.move(nextCell);

		assertSame(this.player.getCell(), nextCell);

	}

}
