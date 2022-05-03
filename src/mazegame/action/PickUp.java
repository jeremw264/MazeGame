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
import mazegame.utils.UserInteration;

public class PickUp extends Action {

	@Override
	public State run(Character character) {
		// Cellule actuelle du joueur
		final Cell cell = character.getCell();

		// Map des objets pr�sents sur la cellule
		Map<String, Item> itemMap = new HashMap<>();

		for (Item item : character.getCell().getItemList()) {
			itemMap.put(item.toString(), item);
		}

		List<String> itemStrings = new ArrayList<String>(itemMap.keySet());

		// Choix du joueur

		Map<String, Object> responceMap = UserInteration.getChoise("Voici les objets sur la case : ", itemStrings,true);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		String choice = (String) responceMap.get("choice");

		Item choseItem = itemMap.get(choice);

		// Ajout de l'objet choisi a l'inventaire du personnage
		character.getListOfItems().add(choseItem);

		// Suppression de l'objet de la liste d'objets de la cellule actuelle
		cell.rmvItem(choseItem);

		Game.DISPLAYER.displayMsg("L'objet a vos pieds a �t� ramass�");

		return State.Ok;

	}
}
