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

/**
 * Classe Character
 */
public abstract class Character {

	private int x;
	private int y;
	private Cell currentCell;
	private Map map;

	private List<Item> inventory;

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
		this.inventory = new LinkedList<Item>();

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
	 * Renvoie si le personnage possède cette objet ou non.
	 * 
	 * @param o L'objet dont on veut connaitre la possesion.
	 * @return true si le personnage possède cette objet, false dans le cas
	 *         contraire.
	 */
	public boolean checkItems(Object o) {
		// for(int i = 0; i < inventory.size(); i++){
		if (inventory.contains(o))
			return true;
		else
			return false;
	}

	/**
	 * Ajoute un objet a la liste des objets que le personnage possède.
	 * 
	 * @param o L'objet à ajouter.
	 */
	public void addInv(Item item) {
		if (this.inventory != null) {
			this.inventory.add((Item) item);	
		}
		else {
			this.inventory = new LinkedList<Item>();
			this.inventory.add((Item) item);
		}
		
	}

	/**
	 * Supprime l'objet passé en paramètre de son inventaire.
	 * 
	 * @param o L'objet à enlever.
	 */
	public void removeInv(Item item) {
		this.inventory.remove(item);
	}

	/**
	 * Renvoie la prochaine cellule où le personnage doit ce déplacer.
	 * 
	 * @return La prochaine cellule où le personnage doit ce déplacer.
	 */
	abstract public Cell computeNextCell();

	/**
	 * Déplace le joueur sur la carte.
	 */
	public void move() {
		Cell nextCell = this.computeNextCell();

		this.currentCell.removeCharacter(this);
		this.setCell(nextCell);
		this.x = this.currentCell.getX();
		this.y = this.currentCell.getY();

		this.currentCell.setCharacter(this);
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
