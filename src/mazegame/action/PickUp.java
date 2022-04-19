package mazegame.action;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.Cell;

public class PickUp extends Action{
	
	@Override
	public void run(Character character) {
		final int currentX = character.getX();
		final int currentY = character.getY();
		final Cell cell = new Cell(currentX,currentY);
		
		if (cell.item == null) {
			Game.DISPLAYER.displayError("Il n'y a pas d'objet sur cette case");
			return;
		}
		
		character.inventory.add(cell.item);
		
		
		Game.DISPLAYER.displayMsg("L'objet a vos pieds a été ramassé");
		
	}
}
