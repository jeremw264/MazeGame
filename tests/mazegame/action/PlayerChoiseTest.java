package mazegame.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;

public class PlayerChoiseTest extends ActionTest {

	@Before
	public void setUp() {
		this.action = new PlayerChoise();
	}

	@Test
	public void characterIsNotPlayer() {
		Map map = new Map(5, 5);
		Character npCharacter = (Character) new Npc(0,0,map,null) {
			
			@Override
			public Cell computeNextCell() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		State returnState = this.action.run(npCharacter);
		
		assertEquals(returnState, State.Exit);
	}
	
	@Override
	public void playerCancelState() {
	}

}
