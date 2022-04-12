package mazegame.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.character.Player;
import mazegame.character.Character;

public class PlayerChoise extends Action {

	private Map<String, Action> actionsMap;

	public PlayerChoise() {
		this.initPlayerActions();
	}

	private void initPlayerActions() {
		this.actionsMap = new HashMap<String, Action>();

		this.actionsMap.put("regarder", new LookAround());

	}

	@Override
	public void run(Character character) {
		if (!(character instanceof Player)) {
			Game.DISPLAYER.displayError("Cette action doit être executé par un Player");
			return;
		}

		List<String> keysList = new LinkedList<String>(this.actionsMap.keySet());

		String choise;

		do {
			Game.DISPLAYER.displayMsg("Que voulez-vous faire ? (taper 'aide' pour plus d'info)");

			choise = Game.INPUT.getString().toLowerCase();

			if (!this.actionsMap.containsKey(choise)) {
				if (choise.equals("aide")) {
					Game.DISPLAYER.displayHelp(keysList);
				} else {
					Game.DISPLAYER.displayError("Choix not valide\n");
				}
			}

		} while (!this.actionsMap.containsKey(choise));

		this.actionsMap.get(choise).run(character);

	}

}
