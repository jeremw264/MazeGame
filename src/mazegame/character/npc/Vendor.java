package mazegame.character.npc;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.Move;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
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
		return "vendor";
	}
	
	public void talk() {
		
		Character character = null;
		Character player = null;
		Item items = null;
		List<Character> characters =character.getCell().charactersList();
		
		HashMap<String, Player> playerMap = new HashMap<String, Player>();
		for (Character character1 : characters) {
			if (character1 instanceof Player) {
				playerMap.put(character1.toString(), (Player) character1);
			}
		}
		
		List<String> playerOnCell = new LinkedList<>(playerMap.keySet());
		
		
		HashMap<String, Item> sellMap = new HashMap<>(); 
		for (Item item : character.getListOfItems()) { 
			if (item.canSell() == true)
				sellMap.put(item.toString(), item); }
		
		String buyList = this.getListOfItems().toString();
		String value = String.valueOf(items.getValue());
		
		Game.DISPLAYER.displayMsg("Weeeelllllcoooooommmmmeeee");
		Game.DISPLAYER.displayMsg("J'ai des choses rares en soldes �tranger !" + buyList);
		String responce = Game.INPUT.getString();
		if(buyList.contains(responce)) {
			Game.DISPLAYER.displayMsg("Le prix de cette objet est :" +value );
			Game.DISPLAYER.displayMsg("Souhaitez vous l'acheter ? (oui/non)");
			responce = Game.INPUT.getString();
			if(responce =="oui") {
				player.addInv(items); 
				this.removeInv(items); 
				Game.DISPLAYER.displayMsg("He he he, Merci.");
			}
			else { 
				return; 
				} 
			}
		else {
			Game.DISPLAYER.displayMsg("Qu'aimeriez vous vendre ?" + sellMap);
			responce = Game.INPUT.getString();
			if(sellMap.containsValue(responce)) {
				Game.DISPLAYER.displayMsg("Souhaitez vous vendre cette object pour : " +value+ "(oui/non)");
				responce = Game.INPUT.getString();
				if(responce == "oui") { 
					this.addInv(items); 
					player.removeInv(items);
					Game.DISPLAYER.displayMsg("He he he, Merci.");
					}
				else {
					return;
					
				}
			}
		}
		
	}

	/*
	 * @SuppressWarnings("unchecked")
	 *
	 * @Override public void talk() { JSONParser npcParse = new JSONParser(); try {
	 * JSONArray npcData = (JSONArray) npcParse .parse(new
	 * FileReader(System.getProperty("user.dir") + "/data/" + this.dataFileName +
	 * ".json")); Collections.shuffle(npcData); JSONObject npc = (JSONObject)
	 * npcData.get(0);
	 *
	 * String content = (String) npc.get("content"); List<String> answer =
	 * (JSONArray) npc.get("answer"); List<String> c = (JSONArray)
	 * npc.get("correct"); String answerNpc = (String) npc.get("answerNpc"); String
	 * answerNpc2 = (String) npc.get("answerNpc2"); String buyList =
	 * this.getListOfItems().toString();
	 *
	 *
	 * HashMap<String, Item> sellMap = new HashMap<>(); for (Item item :
	 * player.getListOfItems()) { if (item.canSell() == true)
	 * sellMap.put(item.toString(), item); }
	 *
	 * Game.DISPLAYER.displayMsg(content);
	 * Game.DISPLAYER.displayChoise("Voici les réponses possibles : ", answer);
	 * String responce = Game.INPUT.getString(); if (c.contains(responce)) {
	 * Game.DISPLAYER.displayMsg("Voici les objects disponible à la vente" +
	 * buyList); responce = Game.INPUT.getString(); if(buyList.contains(responce)) {
	 * Game.DISPLAYER.displayMsg("Le prix de cette object est :"
	 * +item.getValue().toString()+ " Souhaitez vous l'acheter ?(oui/non)");
	 * responce = Game.INPUT.getString(); if(responce == "oui") {
	 * Player.inventory.put(item); this.removeInv(item); } else { return; } }
	 * Game.DISPLAYER.displayMsg(answerNpc); } else {
	 * Game.DISPLAYER.displayMsg("Qu'aimeriez vous vendre ?" + sellMap); responce =
	 * Game.INPUT.getString(); if(sellMap.containsValue(responce)) {
	 * Game.DISPLAYER.displayMsg("Souhaitez vous vendre cette object pour : "
	 * +item.getValue+ "(oui/non)"); responce = Game.INPUT.getString(); if(responce
	 * == "oui") { this.addInv(item); player.removeInv(item); } else { return; } }
	 * Game.DISPLAYER.displayMsg(answerNpc2);
	 *
	 * } } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } catch (ParseException e) {
	 * e.printStackTrace(); } }
	 */

}
