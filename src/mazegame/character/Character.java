package mazegame.character;

import java.util.LinkedList;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.item.Item;

public abstract class Character {

	private int x;
	private int y;
	private Cell currentCell;
	private Map map;

	private List<Item> inventory;

	public Character(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.currentCell = map.getCell(x, y);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setCell(Cell nextCell) {
		this.currentCell = nextCell;
	}

	public Cell getCell() {
		return currentCell;
	}

	public Map getMap() {
		return this.map;
	}

	public abstract Action getAction();

	public List<Item> getListOfItems() {
		return this.inventory;
	}

	public boolean checkItems(Object o) {
		// for(int i = 0; i < inventory.size(); i++){
		if (inventory.contains(o))
			return true;
		else
			return false;
	}

	public void addInv(Object o) {
		inventory.add((Item) o);
	}

	public void removeInv(Object o) {
		inventory.remove(o);
	}

	/**
	 * Renvoie toute les directions accesible dupuis la case courante du personnage.
	 * 
	 * @return Une liste des directions accesible.
	 */
	public List<Direction> getAccessibleDirections() {
		List<Direction> accesibleDirections = new LinkedList<Direction>();

		for (Direction direction : Direction.values()) {
			if (!this.currentCell.wallExist(direction)) {
				accesibleDirections.add(direction);
			}
		}

		return accesibleDirections;

	}

}
