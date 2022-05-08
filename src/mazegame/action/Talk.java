package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.utils.UserInteration;

/**
 * Action : Parler avec un Npc
 */
public class Talk extends Action {
	@Override
	public State run(Character character) {

		List<Character> characters = character.getCell().charactersList();

		Map<String, Npc> npcMap = new HashMap<>();
		for (Character character2 : characters) {
			if (character2 instanceof Npc) {
				npcMap.put(character2.toString(), (Npc) character2);
			}
		}
		List<String> npcOnCell = new LinkedList<>(npcMap.keySet());

		// Choix du joueur

		Map<String, Object> responceMap = UserInteration.getChoise("A qui souhaitez vous parler ?", npcOnCell,true);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		String choice = (String) responceMap.get("choice");

		Npc npc = npcMap.get(choice);

		npc.talk();

		return State.Ok;
	}

}
