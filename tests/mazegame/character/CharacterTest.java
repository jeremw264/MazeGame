package mazegame.character;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazegame.action.Action;
import mazegame.action.DoNothing;
import mazegame.character.player.Hero;
import mazegame.item.Item;
import mazegame.item.Scroll;
import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;

public class CharacterTest {

	private Character character;
	private Map map;

	@Before
	public void setUp() {
		this.map = new Map(5, 5);
		this.character = new Character(2, 3, this.map) {

			@Override
			public Action getAction() {
				return new DoNothing();
			}
		};
	}

	@Test
	public void getXTest() {
		assertEquals(this.character.getX(), 2);
	}

	@Test
	public void getYTest() {
		assertEquals(this.character.getY(), 3);
	}

	@Test
	public void getCellTest() {
		int x = this.character.getX();
		int y = this.character.getY();
		Cell correctCell = new Cell(x, y);

		assertEquals(this.character.getCell(), correctCell);
	}

	@Test
	public void setCellTest() {
		Cell cell = new Cell(5, 2);

		this.character.setCell(cell);

		assertEquals(cell, this.character.getCell());
	}

	@Test
	public void getMapTest() {
		assertSame(this.map, this.character.getMap());
	}

	@Test
	public void listOfItemIsEmptyToConstruction() {
		assertTrue(this.character.getListOfItems().isEmpty());
	}

	@Test
	public void addItemInList() {
		Item item = new Scroll("indice");

		this.character.addInv(item);

		assertTrue(this.character.getListOfItems().contains(item));
	}

	@Test
	public void removeElementFromInventory() {
		Item item = new Scroll("indice");

		assertTrue(this.character.getListOfItems().isEmpty());

		this.character.addInv(item);

		assertFalse(this.character.getListOfItems().isEmpty());

		this.character.removeInv(item);

		assertTrue(this.character.getListOfItems().isEmpty());

	}

	@Test
	public void elementIsInInventory() {
		Item item = new Scroll("indice");

		this.character.addInv(item);

		assertFalse(this.character.getListOfItems().isEmpty());

		assertEquals(this.character.getListOfItems().size(), 1);
		assertTrue(this.character.checkItems(item));
		assertSame(this.character.getListOfItems().get(0), item);

	}
	
	@Test
	public void elementIsNotInInventory() {
		Item item = new Scroll("indice");
		Item badItem = new Scroll("indice");

		this.character.addInv(item);

		assertFalse(this.character.getListOfItems().isEmpty());

		assertEquals(this.character.getListOfItems().size(), 1);
		assertTrue(this.character.checkItems(badItem));
		assertSame(this.character.getListOfItems().get(0), item);
	}

	@Test
	public void correctAccessibleDirection() {
		Map map = new Map(2, 2);
		Player player = new Hero(0, 0, map);

		assertFalse(player.getAccessibleDirections().contains(Direction.S));
		assertFalse(player.getAccessibleDirections().contains(Direction.N));
		assertFalse(player.getAccessibleDirections().contains(Direction.O));
		assertFalse(player.getAccessibleDirections().contains(Direction.E));

		map.getCell(0, 0).eraseWall(Direction.S);

		assertTrue(player.getAccessibleDirections().contains(Direction.S));
		assertFalse(player.getAccessibleDirections().contains(Direction.N));
		assertFalse(player.getAccessibleDirections().contains(Direction.O));
		assertFalse(player.getAccessibleDirections().contains(Direction.E));

		map.getCell(0, 0).eraseWall(Direction.E);

		assertTrue(player.getAccessibleDirections().contains(Direction.S));
		assertTrue(player.getAccessibleDirections().contains(Direction.E));
		assertFalse(player.getAccessibleDirections().contains(Direction.N));
		assertFalse(player.getAccessibleDirections().contains(Direction.O));

		map.getCell(0, 0).createWall(Direction.S);

		assertTrue(player.getAccessibleDirections().contains(Direction.E));
		assertFalse(player.getAccessibleDirections().contains(Direction.N));
		assertFalse(player.getAccessibleDirections().contains(Direction.O));
		assertFalse(player.getAccessibleDirections().contains(Direction.S));

	}

}
