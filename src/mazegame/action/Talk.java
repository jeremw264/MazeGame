package mazegame.action;

import java.util.LinkedList;
import java.util.List;

import mazegame.Game;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;


public class Talk extends Action {
	public void run(Character character) {
				
		List<Character> characters = character.getCell().CharactersList();
		//characters.removeIf(c -> (c));
		characters.remove(character);
		
		Game.DISPLAYER.displayChoise("A qui souhaitez vous parler ?", List<characters>);
		boolean responceBoolean = Npc.talk();
		
		/*if(responceBoolean) {
			Game.DISPLAYER.displayMsg("Bonne réponse");
		}
		else {
			Game.DISPLAYER.displayMsg("Mauvaise réponse");
		}*/
	}
	
	
}
