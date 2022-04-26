package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Player;
import mazegame.character.Character;

/**
 * Classe PlayerChoise
 */
public class PlayerChoise extends Action {

	private Map<String, Action> actionsMap;

	/**
	 * Constructeur de l'objet PlayerChoise.
	 */
	public PlayerChoise() {
		this.initPlayerActions();
	}

	/**
	 * Initialise les actions possible pour le joueur.
	 */
	private void initPlayerActions() {
		this.actionsMap = new HashMap<String, Action>();

		this.actionsMap.put("regarder", new LookAround());
		this.actionsMap.put("bouger", new Move());
		this.actionsMap.put("utiliser", new UseItem());
		this.actionsMap.put("ramasser", new PickUp());
		this.actionsMap.put("discuter", new Talk());

	}

	/**
	 * Demande au joueur de faire un choix d'action.
	 * 
	 * @param character Le personnage qui doit faire le choix.
	 */
	@Override
	public State run(Character character) {
		if (!(character instanceof Player)) {
			Game.DISPLAYER.displayError("Cette action doit être executé par un Player");
			return State.Exit;
		}

		List<String> keysList = new LinkedList<String>(this.actionsMap.keySet());
		keysList.add("quitter");

		String choise;

		do {
			Game.DISPLAYER.displayMsg("Que voulez-vous faire ? (taper 'aide' pour plus d'info)");

			choise = Game.INPUT.getString().toLowerCase().strip();

			if (!this.actionsMap.containsKey(choise)) {
				if (choise.equals("aide")) {
					Game.DISPLAYER.displayHelp(keysList);
				} else if (choise.equals("quitter")) {
					return State.Exit;

				} else {
					Game.DISPLAYER.displayError("Choix non valide\n");
				}
			}

		} while (!this.actionsMap.containsKey(choise));

		if (this.actionsMap.get(choise).run(character) == State.Cancel) {
			this.run(character);
		}

		return State.Ok;

	}

}
