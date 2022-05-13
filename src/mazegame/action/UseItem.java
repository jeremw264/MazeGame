package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.utils.UserInteraction;

/**
 * Action : Utiliser un objet.
 */
public class UseItem extends Action {

	@Override
	/**
	 * Méthode exécutant l'action
	 */
	public State run(Character character) {

		// Map des objets utilisables.
		Map<String, Item> itemMap = new HashMap<>();

		for (Item item : character.getListOfItems()) {
			if (item.isUsable())
				itemMap.put(item.toString(), item);
		}

		List<String> choicePossibility = new LinkedList<>(itemMap.keySet());

		// Choix du joueur.

		Map<String, Object> responseMap = UserInteraction.getChoise("Voila les objets disponible : ", choicePossibility,
				true);

		if (responseMap.get("STATE") != State.Ok) {
			return (State) responseMap.get("STATE");
		}

		String choice = (String) responseMap.get("choice");

		// Objet choisi.
		Item choseItem = itemMap.get(choice);

		if (choseItem instanceof GoldCoin) {
			character.changeCoins(choseItem.getValue());
		}

		// Utilisation de l'objet choisi (DISPLAYER).
		choseItem.use(character);

		// Suppression de l'objet de l'inventaire du personnage.
		character.removeInv(choseItem);

		Game.DISPLAYER.displayMsg("l'objet utilisé a été supprimé de votre inventaire");

		return State.Ok;

	}

}
