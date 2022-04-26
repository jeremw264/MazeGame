package mazegame.action;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.Cell;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import mazegame.item.*;

public class PickUp extends Action {

	@Override
	public State run(Character character) {
		// Cellule actuelle du joueur
		final Cell cell = character.getCell();

		// Verification qu'il y ait au moins 1 objet sur la cellule
		/*
		 * if (cell.getItemList().isEmpty()) {
		 * Game.DISPLAYER.displayError("Il n'y a pas d'objet sur cette case"); return
		 * State.Exit; }
		 */

		// Map des objets pr�sents sur la cellule
		Map<String, Item> itemMap = new HashMap<>();

		for (Item item : character.getCell().getItemList()) {
			itemMap.put(item.toString(), item);
		}

		String choice;

		List<String> itemStrings = new ArrayList<String>();
		itemStrings.add("Retour");
		itemStrings.addAll(itemMap.keySet());

		// Choix du joueur
		do {
			Game.DISPLAYER.displayChoise("Voici les objets sur la case : ", itemStrings);
			choice = Game.INPUT.getString().toLowerCase();
			if (!(itemMap.containsKey(choice))) {
				if (choice.equals("aide")) {
					Game.DISPLAYER.displayHelp(itemStrings);
				} else if (choice.equals("quitter")) {
					return State.Exit;
				} else if (choice.equals("retour")) {
					return State.Cancel;
				} else {
					Game.DISPLAYER.displayError("Choix non valide\n");
				}
			}
		} while (!(itemMap.containsKey(choice)));

		Item choseItem = itemMap.get(choice);

		// Ajout de l'objet choisi a l'inventaire du personnage
		character.getListOfItems().add(choseItem);

		// Suppression de l'objet de la liste d'objets de la cellule actuelle
		cell.rmvItem(choseItem);

		Game.DISPLAYER.displayMsg("L'objet a vos pieds a �t� ramass�");

		return State.Ok;

	}
}
