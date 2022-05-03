package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.State;
import mazegame.character.Player;
import mazegame.utils.UserInteration;
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

		Map<String, Object> responceMap = UserInteration.getChoise("Que voulez-vous faire ? ", keysList);

		if (responceMap.get("STATE") != State.Ok) {
			return (State) responceMap.get("STATE");
		}

		String choise = (String) responceMap.get("choice");

		State state = this.actionsMap.get(choise).run(character);

		if (state == State.Cancel) {
			return this.run(character);
		} else if (state == State.Exit) {
			return State.Exit;
		}

		return State.Ok;

	}

}
