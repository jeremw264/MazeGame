package mazegame.challenge;

import java.util.Random;

import mazegame.Cell;
import mazegame.Map;
import mazegame.character.Player;

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
	public FinalCase(Player player) {
		super(player);
		this.finalCell = this.computeFinalCell();

	}

	/**
	 * Renvoie si le joueur est sur la cellule de fin.
	 * 
	 * @return true si le joueur est sur la cellule de fin, false sinon.
	 */
	@Override
	public boolean isFinish() {
		return this.finalCell.equals(this.player.getCell());
	}

	/**
	 * Renvoie la cellule de fin.
	 * 
	 * @return la cellule de fin.
	 */
	private Cell computeFinalCell() {
		int cellX, cellY;
		Map map = this.player.getMap();
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		Random r = new Random();

		cellX = r.nextInt(mapWidth);
		cellY = r.nextInt(mapHeight);

		return new Cell(cellX, cellY);
	}
}
