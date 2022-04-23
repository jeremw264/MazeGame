package mazegame.action;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.item.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class UseItem extends Action{
	
	public boolean run(Character character) {
	
		Map<String, Item> itemMap = new HashMap<>();
        
        for (Item item : character.getListOfItems()) {
            itemMap.put(item.toString(), item);
        }
		
		
		String choice;
		
		Game.DISPLAYER.displayMsg("Que voulez-vous faire ? (taper 'aide' pour plus d'info)");

        choice = Game.INPUT.getString().toLowerCase();

        if (!(itemMap.containsKey(choice))) {
            if (choice.equals("aide")) {
                Game.DISPLAYER.displayHelp(new ArrayList<String>(itemMap.keySet()));
            } else {
                Game.DISPLAYER.displayError("Choix non valide\n");
            }
        }else {
        	if (choice == "parchemin") {
        		Game.DISPLAYER.displayMsg(itemMap.get(choice).hint);
    			character.inventory.remove(itemMap.get(choice));
        	}
        	if (choice == "objet de qu�te") {
        		Game.DISPLAYER.displayMsg("Cet objet n'est pas vendable, Il vous sera utile dans votre qu�te");
        	}
        	else {
        		if (itemMap.get(choice).value <= 20) {
        			Game.DISPLAYER.displayMsg("Cette émeraude pèse " + itemMap.get(choice).value / 4 + " et vaut " + itemMap.get(choice).value);
        		} else if (itemMap.get(choice).value <= 50) {
        			Game.DISPLAYER.displayMsg("Ce saphir pèse " + itemMap.get(choice).value / 4 + " et vaut " + itemMap.get(choice).value);
        		} else if (itemMap.get(choice).value <= 100) {
        			Game.DISPLAYER.displayMsg("Ce rubis pèse " + itemMap.get(choice).value / 4 + " et vaut " + itemMap.get(choice).value);
        		} else if (itemMap.get(choice).value <= 250) {
        			Game.DISPLAYER.displayMsg("Cette améthyste pèse " + itemMap.get(choice).value / 4 + " et vaut " + itemMap.get(choice).value);
        		} else if (itemMap.get(choice).value <= 500) {
        			Game.DISPLAYER.displayMsg("Cette pépite d'or pèse " + itemMap.get(choice).value + " et vaut donc " + itemMap.get(choice).value);
        		} else {
        			Game.DISPLAYER.displayMsg("Ce sac de joyaux " + itemMap.get(choice).value / 15 + " et vaut " + itemMap.get(choice).value);
        		}
        	}
        }
		
        return true;
		
	}
}
