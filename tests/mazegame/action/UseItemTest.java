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

}
