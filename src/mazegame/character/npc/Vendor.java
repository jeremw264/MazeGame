package mazegame.character.npc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.Move;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.character.Character;
import mazegame.item.Item;

/**
 * Classe Vendor
 * 
 * Personnage Marchand
 */
public class Vendor extends Npc {

	/**
	 * Constructeur de l'objet Vendor.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le marchand ce déplace.
	 */
	public Vendor(int x, int y, Map map) {
		super(x, y, map, "Vendor");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Renvoie la prochaine cellule où le personnage doit ce déplacer.
	 * 
	 * @return La prochaine cellule où le personnage doit ce déplacer.
	 */
	@Override
	public Cell computeNextCell() {

		List<Direction> accessibleDirections = this.getAccessibleDirections();

		Collections.shuffle(accessibleDirections);

		return this.getMap().getCellWithDirection(this.getCell(), accessibleDirections.get(0));
	}

	/**
	 * Renvoie une action du personnage.
	 * 
	 * Par défault il ne peut que bouger.
	 * 
	 * @return Une action du personnage.
	 */
	@Override
	public Action getAction() {
		return new Move();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "vendor";
	}	 
	
	@Override
	public void talk() {
		JSONParser npcParse = new JSONParser();
		try {
			JSONArray npcData = (JSONArray) npcParse
					.parse(new FileReader(System.getProperty("user.dir") + "/data/" + this.dataFileName + ".json"));
			Collections.shuffle(npcData);
			JSONObject npc = (JSONObject) npcData.get(0);

			String content = (String) npc.get("content");
			List<String> answer = (JSONArray) npc.get("answer");
			List<String> c = (JSONArray) npc.get("correct");
			String answerNpc = (String) npc.get("answerNpc");
			String answerNpc2 = (String) npc.get("answerNpc2");
			String achatList = this.getListOfItems().toString();
			
			/*HashMap<String, Item> venteMap = new HashMap<>();
			for (Item item : .getListOfItems()) {
				if (item.isUsable() == true)
					venteMap.put(item.toString(), item);
			}*/
		 
			

			Game.DISPLAYER.displayMsg(content);
			Game.DISPLAYER.displayChoise("Voici les réponses possibles : ", answer);
			String responce = Game.INPUT.getString();
			String achat = Game.INPUT.getString();
			if (c.contains(responce)) {
				Game.DISPLAYER.displayMsg(achatList);
				if(achatList.contains(achat)) {
					//Player.inventory.put();
					
					//this.removeInv();
				}
				Game.DISPLAYER.displayMsg(answerNpc);
			} else {
				//Game.DISPLAYER.displayMsg(venteList);
				Game.DISPLAYER.displayMsg(answerNpc2);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
