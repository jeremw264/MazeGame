package mazegame.character;

import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import mazegame.Cell;
import mazegame.Map;
import mazegame.item.Item;

public abstract class Character {

		private int x;
		private int y;
		private Cell currentCell;
		private Map map;
		
		private List<Item> inventory;
		
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
		
		public List<Item> getListOfItems(){
			return this.inventory;
		}
		
		public boolean checkItems(Object o){
			//for(int i = 0; i < inventory.size(); i++){
			if (inventory.contains(o))
				return true;	
			else
				return false;
		}

		public void addInv(Object o){
			inventory.add((Item) o);		
		}
		
		public void removeInv(Object o) {
			inventory.remove(o);
		}
		
}
