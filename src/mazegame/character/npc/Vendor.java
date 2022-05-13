package mazegame.character.npc;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Game;
import mazegame.Map;
import mazegame.State;
import mazegame.action.Action;
import mazegame.action.Move;
import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.Player;
import mazegame.item.GoldCoin;
import mazegame.item.Item;
import mazegame.item.Jewel;
import mazegame.item.Scroll;
import mazegame.utils.UserInteraction;

/**
 * Classe Vendor
 *
 * Personnage Marchand
 */
public class Vendor extends Npc {
	
	private final int nbCoinStart = 1000;

	/**
	 * Constructeur de l'objet Vendor.
	 *
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le marchand ce déplace.
	 */
	public Vendor(int x, int y, Map map) {
		super(x, y, map, "Vendor");
		this.changeCoins(this.nbCoinStart);
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

	/**
	 * Renvoie une représentation du npc en chaîne de caractères
	 */
	@Override
	public String toString() {
		return "vendor";
	}
<<<<<<< HEAD

	/**
	 * Gestion de la discussion avec ce npc
=======
	
	/**
	 * Override Talk npc
>>>>>>> 983b1500ed98af9eb1ecd46cece77fd471753082
	 */
	public void talk() {

		Player player = null;
		List<Character> characters = currentCell.charactersList();
		HashMap<String, Item> sellableItems = new HashMap<>();
		HashMap<String, Item> buyableItems = new HashMap<>();
		List<String> descriptionSellableItems = new LinkedList<>();
		List<String> descriptionBuyableItems = new LinkedList<>();
		List<String> modeList = new LinkedList<>();
		
		modeList.add("vendre");
		modeList.add("acheter");

		// Récupération du joueur
		for (Character character : characters) {
			if (character instanceof Player) {
				player = (Player) character;
			}
		}

		// Récupération des object vendable
		for (Item item : player.getListOfItems()) {
			if (item.canSell() == true)
				sellableItems.put(item.toString(), item);
		}
		
		//Récupération des object achetable
		for (Item item : this.getListOfItems()) {
			if(item.canSell()) {
				buyableItems.put(item.toString(), item);
			}
		}
		
		//List des descriptions des objets vendables
		for (Item item : sellableItems.values()) {
			descriptionSellableItems.add(" | Le prix de cette objet est : " +item.getValue());
		}
		
		//List des descriptions des objets achetables
		for (Item item : buyableItems.values()) {
			descriptionBuyableItems.add(" | Le prix de cette objet est : " +item.getValue());
		}
		
		Game.DISPLAYER.displayMsg("--------------------------------------------------");
		
		Game.DISPLAYER.displayMsg("Weeeelllllcoooooommmmmeeee");
		java.util.Map<String, Object> responceMode = UserInteraction.getChoise("Souhaitez vous acheter ou vendre ? : ", modeList,true);

		if (responceMode.get("STATE") != State.Ok) {
			Game.DISPLAYER.displayMsg("Arretez de me faire perdre mon temps, Etranger ! ");
			return;
		}
		
		String modeChoice = (String) responceMode.get("choice");
		
		if(modeChoice.equals("acheter")) {
			//Si le joueur choisis d'acheter un objet 
			List<String> choiceBuy = new LinkedList<>(buyableItems.keySet());
			java.util.Map<String, Object> responceBuy = UserInteraction.getChoise("Que voulez vous acheter ?: ",choiceBuy,descriptionBuyableItems,true);

			if (responceBuy.get("STATE") != State.Ok) {
				Game.DISPLAYER.displayMsg("Vous êtes difficile satisfaire, etranger ! ");
				return;
			}
			
			String buyChoice = (String) responceBuy.get("choice");
			Item itemBought = buyableItems.get(buyChoice);
			
			//Vérifie si le joueur a assez d'argent
			if (player.getCoins() >= itemBought.getValue()) {
				this.changeCoins(itemBought.getValue()); // modifie l'argent du marchand
				player.changeCoins(-itemBought.getValue()); // modifie l'argent du joueur
				this.removeInv(itemBought); // supprime l'item de l'inventaire du marchand
				player.addInv(itemBought); // ajoute l'item dans l'inventaire du joueur
				Game.DISPLAYER.displayMsg("vous avez acheté "+itemBought.toString());
				Game.DISPLAYER.displayMsg("Vous avez reçu "+itemBought.toString());
				Game.DISPLAYER.displayMsg("He he he, Merci.");
				return;
			}
			else {
				Game.DISPLAYER.displayMsg("Pas assez d’argent etranger !");
				Game.DISPLAYER.displayMsg("He he he, Merci.");
				return;
			}
			
		}
		else {
			//Si le joueur choisis de vendre un objet
			List<String> choiceSell = new LinkedList<>(sellableItems.keySet()); // passe la HashMap sellableItem en List<String> choiseSell avec le contenu passé en element clé
			// Fournit la liste d'objet à vendre avec leur description  
			//récupére un objet dans une map
			java.util.Map<String, Object> responceSell = UserInteraction.getChoise("Que voulez vous vendre ? : ",choiceSell,descriptionSellableItems ,true); 

			if (responceSell.get("STATE") != State.Ok) {
				Game.DISPLAYER.displayMsg("C’est tout etranger ?");
				Game.DISPLAYER.displayMsg("He he he, Merci.");

				return;
			}
			
			String sellChoice = (String) responceSell.get("choice");
			
			Item itemSold = sellableItems.get(sellChoice);
			
			//Vérifie si le marchand a assez d'argent
			if (this.getCoins() >= itemSold.getValue()) {
				this.changeCoins(-itemSold.getValue());
				player.changeCoins(itemSold.getValue());
				player.removeInv(itemSold);
				this.addInv(itemSold);
				Game.DISPLAYER.displayMsg("Ah ! Je vais acheter" +itemSold.toString()+ " a un tres bon prix !");
				Game.DISPLAYER.displayMsg("Le marchand a bien reçu votre "+itemSold.toString());
				Game.DISPLAYER.displayMsg("He he he, Merci.");

				return;
			} 
			else {
				Game.DISPLAYER.displayMsg("L'argent me manque etranger !");
				Game.DISPLAYER.displayMsg("He he he, Merci.");

				return;
			}
			
		}
		
	}
}
