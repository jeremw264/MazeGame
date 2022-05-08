package mazegame.challenge;

import java.util.List;
import java.util.Random;

import mazegame.Cell;
import mazegame.Hint;
import mazegame.Map;
import mazegame.character.Character;
import mazegame.character.Player;
import mazegame.item.Item;

/**
 * Classe Final Case
 * 
 * Challenge Pour arriver sur une case calculer aléatoirement.
 */
public class FinalCase extends Challenge {

	// La case de fin.
	private Cell finalCell;

	/**
	 * Constructeur de l'objet FinalCase.
	 * 
	 * @param player Le joueur qui doit validé le défi.
	 */
	public FinalCase() {
	}

	/**
	 * Renvoie si le joueur est sur la cellule de fin.
	 * 
	 * @return true si le joueur est sur la cellule de fin, false sinon.
	 */
	@Override
	public boolean isFinish(Player player) {
		
		if (this.finalCell == null) {
			this.computeFinalCell(player);
		}
		
		return this.finalCell.equals(player.getCell());
	}

	/**
	 * Renvoie la cellule de fin.
	 * 
	 * @return la cellule de fin.
	 */
	private Cell computeFinalCell(Player player) {
		int cellX, cellY;
		Map map = player.getMap();
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		Random r = new Random();

		cellX = r.nextInt(mapWidth);
		cellY = r.nextInt(mapHeight);

		return new Cell(cellX, cellY);
	}

	@Override
	public boolean isPossible(List<Character> characters, List<Item> items) {
		return characters.get(0).getMap().getListsOfCells().contains(this.finalCell);
	}

	@Override
	public Hint getHint() {
		return new Hint(this.finalCell);
	}
}
