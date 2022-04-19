package mazegame.action;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.item.*;

public class UseItem{
	
	public void run(Character character,Item item) {
		if (!(character.inventory.contains(item))) {
			Game.DISPLAYER.displayError("Vous ne possédez pas cette objet");
			return;
		}
		
		if (item instanceof Scroll) {
			Game.DISPLAYER.displayMsg(item.hint);
			character.inventory.remove(item);
		}
		
		else {
		Game.DISPLAYER.displayMsg(item.toString());
		}
	}
}
