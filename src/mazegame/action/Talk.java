package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.utils.UserInteraction;

/**
 * Action : Parler avec un Npc
 */
public class Talk extends Action {
	@Override
	/**
	 * Méthode exécutant l'action
	 */
	public State run(Character character) {

		List<Character> characters = character.getCell().charactersList();

		Map<String, Npc> npcMap = new HashMap<>();
		for (Character character2 : characters) {
			if (character2 instanceof Npc) {
				npcMap.put(character2.toString(), (Npc) character2);
			}
		}
		List<String> npcOnCell = new LinkedList<>(npcMap.keySet());

		// Choix du joueur.

		Map<String, Object> responseMap = UserInteraction.getChoise("A qui souhaitez vous parler ?", npcOnCell,true);

		if (responseMap.get("STATE") != State.Ok) {
			return (State) responseMap.get("STATE");
		}

		String choice = (String) responseMap.get("choice");

		Npc npc = npcMap.get(choice);

		npc.talk();

		return State.Ok;
	}

}
