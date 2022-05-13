package mazegame.character;

import java.util.LinkedList;
import java.util.List;

import mazegame.Cell;
import mazegame.Direction;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.item.Item;

/**
 * Classe Character
 */
public abstract class Character {

	protected int x;
	protected int y;
	protected Cell currentCell;
	private Map map;

	private List<Item> inventory;
	private int coins;

	/**
	 * Constructeur de l'objet Character
	 *
	 * @param x   Position verticale du personnage.
	 * @param y   Position horizontale du personnage.
	 * @param map La carte sur laquelle ce déplace le personnage.
	 */
	public Character(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.currentCell = map.getCell(x, y);
		this.inventory = new LinkedList<>();
		this.coins = 0;

		// Place le personnage sur la case à la construction.
		this.currentCell.setCharacter(this);
	}

	/**
	 * Renvoie la position horizontale du personnage.
	 *
	 * @return La position horizontale du personnage.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Renvoie la position verticale du personnage.
	 *
	 * @return La position verticale du personnage.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Définit la cellule ou le personnage ce situe.
	 *
	 * @param nextCell La cellule où le personnage ce situe.
	 */
	public void setCell(Cell nextCell) {
		this.currentCell = nextCell;
	}

	/**
	 * Renvoie la cellule courante du personnage.
	 *
	 * @return La cellule courante du personnage.
	 */
	public Cell getCell() {
		return currentCell;
	}

	/**
	 * Renvoie la carte où est le personnage.
	 *
	 * @return La carte où est le personnage.
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Renvoie une action du personnage.
	 *
	 * @return Une action du personnage.
	 */
	public abstract Action getAction();

	/**
	 * Renvoie la liste des objets que le personnage possède.
	 *
	 * @return La liste des objets que le personnage possède.
	 */
	public List<Item> getListOfItems() {
		return this.inventory;
	}

	/**
	 * Ajoute un objet a la liste des objets que le personnage possède.
	 *
	 * @param o L'objet à ajouter.
	 */
	public void addInv(Item item) {
		this.getInventory().add(item);
	}

	/**
	 * Renvoie si le personnage possède cette objet ou non.
	 *
	 * @param o L'objet dont on veut connaitre la possesion.
	 * @return true si le personnage possède cette objet, false dans le cas
	 *         contraire.
	 */
	public boolean checkItems(Item item) {
		// for(int i = 0; i < inventory.size(); i++){
		return getInventory().contains(item);
	}

	/**
	 * Supprime l'objet passé en paramètre de son inventaire.
	 *
	 * @param o L'objet à enlever.
	 */
	public void removeInv(Item item) {
		this.getInventory().remove(item);
	}

	/**
	 * Renvoie le nombre de pieces que le personnage possede
	 *
	 * @return nombre de pieces (int)
	 */
	public int getCoins() {
		return this.coins;
	}

	/**
	 * Ajoute ou retire des pieces de "l'inventaire" du personnage
	 *
	 * @param i : nombre de pieces a retirer ou ajouter ( negatif si retirer,
	 *          positif si ajouter)
	 */
	public void changeCoins(int i) {
		this.coins += i;
	}

	/**
	 * Renvoie toute les directions accesible dupuis la case courante du personnage.
	 *
	 * @return Une liste des directions accesible.
	 */
	public List<Direction> getAccessibleDirections() {
		List<Direction> accesibleDirections = new LinkedList<>();

		for (Direction direction : Direction.values()) {
			if (!this.currentCell.wallExist(direction)) {
				accesibleDirections.add(direction);
			}
		}

		return accesibleDirections;

	}

	/**
	 * Renvoie l'inventaire du personnages
	 *
	 * @return Une liste des objets du personnage.
	 */
	public List<Item> getInventory() {
		return inventory;
	}

}
