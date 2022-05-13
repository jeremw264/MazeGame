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
public class CaseEightTree extends Challenge {

	private final Cell finalCell;

	public CaseEightTree() {
		this.finalCell = new Cell(8, 3);
	}

	@Override
	public boolean isFinish(Player player) {

		return player.getCell().equals(this.finalCell);
	}

	@Override
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return characters.get(0).getMap().getCell(8, 3) != null;
	}

	@Override
	public Hint getHint() {
		// TODO Auto-generated method stub
		return new Hint(this.finalCell);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Défis : Arrivé sur la case (8,3)";
	}

}
