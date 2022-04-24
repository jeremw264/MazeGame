package mazegame.action;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.Cell;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import mazegame.item.*;


public class PickUp extends Action{
	
	@Override
	public boolean run(Character character) {
		//Cellule actuelle du joueur
		final Cell cell = character.getCell();
		
		//Verification qu'il y ait au moins 1 objet sur la cellule
		if (cell.getItemList().isEmpty()) {
			Game.DISPLAYER.displayError("Il n'y a pas d'objet sur cette case");
			return false;
		}
		
	
		//Map des objets présents sur la cellule
		Map<String, Item> itemMap = new HashMap<>();
        
        for (Item item : character.getListOfItems()) {
            itemMap.put(item.toString(), item);
        }
        
		String choice;
		
		choice = Game.INPUT.getString().toLowerCase();
		
		//Choix du joueur
        do {
        	if (!(itemMap.containsKey(choice))) {
                if (choice.equals("aide")) {
                    Game.DISPLAYER.displayHelp(new ArrayList<String>(itemMap.keySet()));
                } else if (choice.equals("quitter")){
                	return false;
                }else {
                    Game.DISPLAYER.displayError("Choix non valide\n");
                }
        	}
        } while (!(itemMap.containsKey(choice)));
        
        Item choseItem = itemMap.get(choice);
        
        //Ajout de l'objet choisi a l'inventaire du personnage
        character.getListOfItems().add(choseItem);
        
        
        //Suppression de l'objet de la liste d'objets de la cellule actuelle
        cell.rmvItem(choseItem);
        
        
		Game.DISPLAYER.displayMsg("L'objet a vos pieds a été ramassé");
		
		return true;
		
	}
}
