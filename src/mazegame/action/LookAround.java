package mazegame.action;

import java.util.LinkedList;
import java.util.List;

import mazegame.Direction;
import mazegame.Game;
import mazegame.State;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

/**
 * Classe LookAround
 *
 * Action pour regarder
 */
public class LookAround extends Action {

	/**
	 * Affiche une description de ce que le joueur peut voir.
	 */
	@Override
	public State run(Character character) {

		if (!(character instanceof Player)) {
			Game.DISPLAYER.displayError("Cette action doit être executé par un Player");
			return State.Exit;
		}

		this.displayHeaderData(character);
		this.displayDirectionData(character);
		this.displayNpcData(character);
		this.displayItemData(character);

		return State.Ok;

	}

	private void displayHeaderData(Character character) {

		final int currentX = character.getX();
		final int currentY = character.getY();

		Game.DISPLAYER.displayMap(character.getMap());
		Game.DISPLAYER.displayMsg("Vous êtes sur la case (" + currentX + "," + currentY + ").");
		Game.DISPLAYER.displayMsg("Vous avez " + character.getCoins() + " or sur vous.");

	}

	private void displayDirectionData(Character character) {
		List<String> directionNameList = new LinkedList<>();

		for (Direction direction : character.getAccessibleDirections()) {
			directionNameList.add(direction.toString());
		}

		Game.DISPLAYER.displayChoise("Les directions accessible sont : ", directionNameList);

	}

	private void displayNpcData(Character character) {
		List<String> npcsNameList = new LinkedList<>();

		for (Character c : character.getCell().charactersList()) {
			if (!(c instanceof Player)) {
				npcsNameList.add(c.toString());
			}
		}

		if (npcsNameList.size() > 0) {
			Game.DISPLAYER.displayChoise("Voici les personages autour de vous :", npcsNameList);
		}
	}

	private void displayItemData(Character character) {
		List<String> itemsNameList = new LinkedList<>();

		for (Item item : character.getCell().getItemList()) {
			itemsNameList.add(item.toString());
		}

		if (itemsNameList.size() > 0) {
			Game.DISPLAYER.displayChoise("Voici les objets autour de vous :", itemsNameList);
		}
	}

}
