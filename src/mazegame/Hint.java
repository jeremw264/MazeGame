package mazegame;

import java.util.Random;

import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.npc.Imp;
import mazegame.item.Item;

/**
 * Classe Hint
 */
public class Hint {

	// Case de destination de l'indice
	private Cell destinationCell;
	
	// Npc à rencontrer.
	private Npc npcToMeet;
	
	// Nombre de tour à attendre pour l'indice.
	private int numberOfRoundsToWait;
	
	// L'objet à trouvé pour l'indice
	private Item item;
	
	// Le nombre de l'objet à trouvé pour l'indice.
	private int nbOfItem;
	
	// Le type de l'indice
	private HintType type;

	// Les types possible pour l'indice.
	public enum HintType {
		OBJECT_HINT, NPC_HINT, CELL_HINT, TIME_HINT, NONE_HINT
	}

	/**
	 * Constructeur de l'objet Hint pour un indice jusqu'a une case à trouvé
	 * 
	 * @param La case sur laquelle donnée un indice
	 */
	public Hint(Cell cell) {
		this.type = HintType.CELL_HINT;
		this.destinationCell = cell;
	}

	/**
	 * Constructeur de l'objet Hint pour un indice jusqu'a un npc à trouvé
	 * 
	 * @param npc Le Npc à trouver
	 */
	public Hint(Npc npc) {
		this.type = HintType.NPC_HINT;
		this.npcToMeet = npc;
	}

	/**
	 * Constructeur de l'objet Hint pour un indice sur le nombre de tours à attendre
	 * 
	 * @param numberOfRoundsToWait
	 */
	public Hint(int numberOfRoundsToWait) {
		this.type = HintType.TIME_HINT;
		this.numberOfRoundsToWait = numberOfRoundsToWait;
	}

	/**
	 * Constructeur de l'objet Hint pour un indice d'objet à trouver
	 * 
	 * @param item
	 * @param nbOfItem
	 */
	public Hint(Item item,int nbOfItem) {
		this.type = HintType.OBJECT_HINT;
		this.item = item;
		this.nbOfItem = nbOfItem;
	}
	
	/**
	 * Constructeur de l'objet Hint qui n'aide pas 
	 */
	public Hint() {
		this.type = HintType.NONE_HINT;
	}

	/**
	 * Renvoie le bon indice
	 *
	 * @return String : good hint
	 */
	public void displayHint(Character character) {
		if (type == HintType.CELL_HINT) {
			this.displayCellHint(character);
		} else if (type == HintType.NPC_HINT) {
			this.displayNpcToMeetHint(character);
		} else if (type == HintType.TIME_HINT) {
			this.displayTimeToWaitHint(character);
		} else if (type == HintType.OBJECT_HINT) {
			this.displayObjectHint(character);
		} else {
			this.displayNoneHint(character);
		}
	}

	/**
	 * Affichage d'un indice qui n'aide pas
	 * 
	 * @param character
	 */
	private void displayNoneHint(Character character) {
		Game.DISPLAYER.displayHint("Malhuereusement tu devra trouvé par toi-même");

	}

	/**
	 * Affichage d'un indice objet
	 * 
	 * @param character
	 */
	private void displayObjectHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois ramasser 50 bout de calamar");
		} else {
			Game.DISPLAYER.displayHint("Tu dois ramasser "+this.nbOfItem+" "+this.item);
		}

	}

	/**
	 * Affichage d'un indice de tours à attendre
	 * 
	 * @param character
	 */
	private void displayTimeToWaitHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois ne plus bouger sinon tu meurs");
		} else {
			Game.DISPLAYER.displayHint("Vous devez attendre " + this.numberOfRoundsToWait + " tours de jeu");
		}

	}

	/**
	 * Affichage d'un indice menant à un npc
	 * 
	 * @param character
	 */
	private void displayNpcToMeetHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois te trouvéé héhé");
		} else {
			Game.DISPLAYER.displayHint("Vous devez trouver un " + this.npcToMeet);
		}
	}

	/**
	 * Affichage d'un indice menant à une cellule
	 * 
	 * @param character
	 */
	private void displayCellHint(Character character) {

		if (character instanceof Imp) {
			Random random = new Random();
			int x = random.nextInt(character.getMap().getWidth());
			int y = random.nextInt(character.getMap().getHeight());
			Cell fakeCell = new Cell(x, y);
			Game.DISPLAYER.displayHint("L'endroit ou tu dois aller est en " + fakeCell);

		} else {
			Cell destinationCell = this.destinationCell;

			if (destinationCell != null) {

				Game.DISPLAYER.displayHint("L'endroit ou tu dois aller est en " + destinationCell);
			} else {
				Game.DISPLAYER.displayHint("Je ne sais pas ou tu dois aller ...");
			}
		}

	}

}
