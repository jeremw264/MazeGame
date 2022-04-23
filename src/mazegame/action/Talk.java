package mazegame.action;

import java.io.FileReader;
import mazegame.Game;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;

import org.json.JSONObject;
import org.json.simple.parser.*;


public class Talk extends Action {
	@Override
	public boolean run(Character character) {
		// TODO Auto-generated method stub
		Player.talk();
		
		return true;
	}
}
