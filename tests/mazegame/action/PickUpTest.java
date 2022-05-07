package mazegame.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.State;
import mazegame.character.Player;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.item.Jewel;

public class PickUpTest extends ActionTest {
	
	private Player player;

	@Before
	public void setUp(){
		this.action = new PickUp();
		
		Map map = new Map(5, 5);
		this.player = new Player(0,0,map) {
			
			@Override
			public Action getAction() {
				return null;
			}
			
			@Override
			public Cell computeNextCell(Direction direction) {
				return null;
			}
		};
	}
	
	@Test
	public void returnStateWithGoodChoise() {
		Item item = new Jewel();
		this.player.getMap().getCell(0, 0).addItem(item);
		
		System.setIn(new ByteArrayInputStream(item.toString().getBytes()));
		State returnState = this.action.run(player);
		
		assertEquals(returnState, State.Ok);
		
	}
	
	@Test
	public void getGoldCoin() {
		Item item = new GoldCoin();
		this.player.getMap().getCell(0, 0).addItem(item);
		
		assertEquals(0, this.player.getCoins());
		
		System.setIn(new ByteArrayInputStream(item.toString().getBytes()));
		State returnState = this.action.run(player);
		
		assertEquals(1, this.player.getCoins());
		
		assertEquals(returnState, State.Ok);
	}
	
	@Test
	public void itemIsRemoveAfterPickUp() {
		Item item = new Jewel();
		this.player.getMap().getCell(0, 0).addItem(item);
		
		System.setIn(new ByteArrayInputStream(item.toString().getBytes()));
		this.action.run(player);
		
		assertFalse(this.player.getMap().getCell(0, 0).getItemList().contains(item));
		
	}

	
}
