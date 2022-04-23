package mazegame.action;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.Cell;
import java.util.ArrayList;
public class PickUp extends Action{
	
	@Override
	public boolean run(Character character) {
		final int currentX = character.getX();
		final int currentY = character.getY();
		final Cell cell = new Cell(currentX,currentY);
		
		if (cell.items.isEmpty()) {
			Game.DISPLAYER.displayError("Il n'y a pas d'objet sur cette case");
			return false;
		}
        
		String choice;
		
		Game.DISPLAYER.displayMsg("Quel objet voulez vous ramasser? (taper 'aide' pour plus d'info)");
		
		choice = Game.INPUT.getString().toLowerCase();

        if (!(cell.itemMap.containsKey(choice))) {
            if (choice.equals("aide")) {
                Game.DISPLAYER.displayHelp(new ArrayList<String>(cell.itemMap.keySet()));
            } else {
                Game.DISPLAYER.displayError("Choix non valide\n");
            }
        }else {
            	character.inventory.add(cell.itemMap.get(choice));
            }
            
        
		Game.DISPLAYER.displayMsg("L'objet a vos pieds a été ramassé");
		
		return true;
		
	}
}
