package mazegame.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mazegame.Cell;
import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.utils.UserInteration;

/**
 * Action : ramasser un objet sur la case.
 */
public class PickUp extends Action {

	@Override
	public State run(Character character) {
		// Cellule actuelle du joueur
		final Cell cell = character.getCell();

		// Map des objets presents sur la cellule
		Map<String, Item> itemMap = new HashMap<>();

		for (Item item : character.getCell().getItemList()) {
			itemMap.put(item.toString(), item);
		}

		List<String> itemStrings = new ArrayList<>(itemMap.keySet());

		// Choix du joueur

		Map<String, Object> responceMap = UserInteration.getChoise("Voici les objets sur la case : ", itemStrings,true);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		String choice = (String) responceMap.get("choice");

		Item choseItem = itemMap.get(choice);

		// Ajout d'une piece au nombre d'or que le personnage possede
		if (choseItem instanceof GoldCoin) {
			character.changeCoins(choseItem.getValue());

			Game.DISPLAYER.displayMsg("Vous possedez maintenant "+ character.getCoins() + " pieces");
		}
		else {
			// Ajout de l'objet choisi a l'inventaire du personnage si ce n'est pas une piece
			character.getListOfItems().add(choseItem);

			// Suppression de l'objet de la liste d'objets de la cellule actuelle
			cell.rmvItem(choseItem);

			Game.DISPLAYER.displayMsg("L'objet a vos pieds a ete ramasse");

		}

		return State.Ok;

	}
}
