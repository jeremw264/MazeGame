package mazegame.action;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.item.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UseItem extends Action{
	
	public State run(Character character) {
	
		
		//Map des objets utilisables
		Map<String, Item> itemMap = new HashMap<>();
        
        for (Item item : character.getListOfItems()) {
            if (item.isUsable() == true)
            	itemMap.put(item.toString(), item);
        }
		
        List<String> choisePossibility = new LinkedList<String>();
		choisePossibility.add("Retour");
		choisePossibility.addAll(itemMap.keySet());
        
        
		String choice;
		
		//Choix du joueur
		do {
			Game.DISPLAYER.displayChoise("Voila les objets disponible : ", choisePossibility);
			
			choice = Game.INPUT.getString().toLowerCase();

	        if (!(itemMap.containsKey(choice))) {
	        	if (choice.equals("retour")) {
					return State.Cancel;
				}
	            if (choice.equals("aide")) {
	                Game.DISPLAYER.displayHelp(new ArrayList<String>(itemMap.keySet()));
	            } else {
	                Game.DISPLAYER.displayError("Choix non valide\n");
	            }
	        }
		}while (!(itemMap.containsKey(choice)));
	        
	    //Objet choisi    
		Item choseItem = itemMap.get(choice);
		
		
		//Utilisation de l'objet choisi (DISPLAYER)
		choseItem.use();
	    
		//Suppression de l'objet de l'inventaire du personnage
	    character.removeInv(choseItem);
	    
	    Game.DISPLAYER.displayMsg("l'objet utilis� a �t� supprim� de votre inventaire");
        
		
        return State.Ok;
        
	}
		
}
	
