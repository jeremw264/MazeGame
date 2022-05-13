package mazegame.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import mazegame.Map;
import mazegame.character.Character;
import mazegame.character.player.Hero;
import mazegame.item.Item;

public class UseItemTest extends ActionTest {

	@Override
	@Before
	public void setUp() {
		this.action = new UseItem();
	}
	
	@Test
	public void itemIsDestroy() {
		Map map = new Map(2, 2);
		Character playerCharacter = new Hero(0, 0, map);
		Item item = new Item(2,true,true) {
			
			@Override
			public void use(Character character) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "myItem";
			}
		};
		
		playerCharacter.addInv(item);
		
		System.setIn(new ByteArrayInputStream((item.toString()).getBytes()));
		this.action.run(playerCharacter);
		
		assertEquals(playerCharacter.getListOfItems().size(),0);
		
		
	}



}
