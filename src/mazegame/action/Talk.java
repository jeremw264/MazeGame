package mazegame.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;


public class Talk extends Action {
	public State run(Character character) {
				
		List<Character> characters = character.getCell().charactersList();
		
		java.util.Map<String, Npc> npcMap = new HashMap<String, Npc>();
		for (Character character2 : characters) {
			if(character2 instanceof Npc) {
				npcMap.put(character2.toString(), (Npc)character2);
			}
		}
		List<String>npcOnCell = new LinkedList<>();
		npcOnCell.add("Retour");
		npcOnCell.addAll(npcMap.keySet());
		String choice;
		
		//Choix du joueur
		do {
			Game.DISPLAYER.displayChoise("A qui souhaitez vous parler ?", npcOnCell);
			choice = Game.INPUT.getString().toLowerCase();
	        if (!(npcMap.containsKey(choice))) {
	        	if (choice.equals("retour")) {
					return State.Cancel;
				}
	            if (choice.equals("aide")) {
	                Game.DISPLAYER.displayHelp(new ArrayList<String>(npcMap.keySet()));
	            } else {
	                Game.DISPLAYER.displayError("Choix non valide\n");
	            }
	        }
		}while (!(npcMap.containsKey(choice)));
		
		Npc npc = npcMap.get(choice);
		
		npc.talk();
		
		//boolean responceBoolean = Npc.talk();
		
		/*if(responceBoolean) {
			Game.DISPLAYER.displayMsg("Bonne r�ponse");
		}
		else {
			Game.DISPLAYER.displayMsg("Mauvaise r�ponse");
		}*/
		return State.Ok;
	}
	
	
}
