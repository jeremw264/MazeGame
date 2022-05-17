package mazegame.challenge;

import java.util.List;

import mazegame.Cell;
import mazegame.Hint;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

/**
 * Challenge: Acceder à la case (8,3)
 */
public class CaseEightThree extends Challenge {

	private final Cell finalCell;

	/**
	 * Constructeur d'un challenge EightThree
	 */
	public CaseEightThree() {
		this.finalCell = new Cell(8, 3);
	}

	@Override
	/**
	 * Méthode renvoyant un booléen pour savoir si le challenge est complété
	 */
	public boolean isFinish(Player player) {

		return player.getCell().equals(this.finalCell);
	}

	@Override
	/**
	 * Méthode renvoyant un booléen pour savoir si le challenge est possible
	 */
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return characters.get(0).getMap().getCell(8, 3) != null;
	}

	@Override
	/**
	 * Renvoies l'indice lié au challenge
	 */
	public Hint getHint() {
		// TODO Auto-generated method stub
		return new Hint(this.finalCell);
	}
	
	@Override
	/**
	 * Renvoies une représentation du challenge en chaîne de caractères
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return "Défis : Arrivé sur la case (8,3)";
	}

}
