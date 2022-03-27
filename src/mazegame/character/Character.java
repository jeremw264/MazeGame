package mazegame.character;

import java.util.List;

import mazegame.Cell;
import mazegame.Map;
import mazegame.item.Item;

public abstract class Character {

		private int x;
		private int y;
		private Cell currentCell;
		private Map map;
		
		public List<Item> inventory;
		
		public Character(int x, int y) {
			this.x = x;
			this.y = y;
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
}
