package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.item.Item;
import mazegame.utils.UserInteration;

public class UseItem extends Action {

	public State run(Character character) {

		// Map des objets utilisables
		Map<String, Item> itemMap = new HashMap<>();

		for (Item item : character.getListOfItems()) {
			if (item.isUsable() == true)
				itemMap.put(item.toString(), item);
		}

		List<String> choisePossibility = new LinkedList<String>(itemMap.keySet());

		// Choix du joueur

		Map<String, Object> responceMap = UserInteration.getChoise("Voila les objets disponible : ", choisePossibility);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		String choice = (String) responceMap.get("choice");

		// Objet choisi
		Item choseItem = itemMap.get(choice);

		// Utilisation de l'objet choisi (DISPLAYER)
		choseItem.use();

		// Suppression de l'objet de l'inventaire du personnage
		character.removeInv(choseItem);

		Game.DISPLAYER.displayMsg("l'objet utilis� a �t� supprim� de votre inventaire");

		return State.Ok;

	}

}
