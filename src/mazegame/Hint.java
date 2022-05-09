package mazegame;

import java.util.Random;

import mazegame.character.Character;
import mazegame.character.Npc;
import mazegame.character.npc.Imp;
import mazegame.item.Item;

public class Hint {

	private Cell destinationCell;
	private Npc npcToMeet;
	private int numberOfRoundsToWait;
	private Item item;
	private int nbOfItem;
	private HintType type;

	public enum HintType {
		OBJECT_HINT, NPC_HINT, CELL_HINT, TIME_HINT, NONE_HINT
	}

	/**
	 * Constructeur de l'objet Hint
	 */
	public Hint(Cell cell) {
		this.type = HintType.CELL_HINT;
		this.destinationCell = cell;
	}

	public Hint(Npc npc) {
		this.type = HintType.NPC_HINT;
		this.npcToMeet = npc;
	}

	public Hint(int numberOfRoundsToWait) {
		this.type = HintType.TIME_HINT;
		this.numberOfRoundsToWait = numberOfRoundsToWait;
	}

	public Hint(Item item,int nbOfItem) {
		this.type = HintType.OBJECT_HINT;
		this.item = item;
		this.nbOfItem = nbOfItem;
	}
	
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

	private void displayNoneHint(Character character) {
		Game.DISPLAYER.displayHint("Malhuereusement tu devra trouvé par toi-même");

	}

	private void displayObjectHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois ramasser 50 bout de calamar");
		} else {
			Game.DISPLAYER.displayHint("Tu dois ramasser "+this.nbOfItem+" "+this.item);
		}

	}

	private void displayTimeToWaitHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois ne plus bouger sinon tu meurs");
		} else {
			Game.DISPLAYER.displayHint("Vous devez attendre " + this.numberOfRoundsToWait + " tours de jeu");
		}

	}

	private void displayNpcToMeetHint(Character character) {
		if (character instanceof Imp) {
			Game.DISPLAYER.displayHint("Tu dois te trouvéé héhé");
		} else {
			Game.DISPLAYER.displayHint("Vous devez trouver un " + this.npcToMeet);
		}
	}

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
