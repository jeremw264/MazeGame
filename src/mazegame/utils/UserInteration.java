package mazegame.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mazegame.Game;
import mazegame.State;

public class UserInteration {

	public static final String EXITWORD_STRING = "quitter";
	public static final String RETURNWORD_STRING = "retour";
	public static final String INVALIDCHOISE_STRING = "Choix incorrect";

	public static Map<String, Object> getChoise(String firstSentence, List<String> listOfChoises, boolean canCancel) {

		Map<String, Object> responceObjects = new HashMap<String, Object>();
		responceObjects.put("STATE", State.Ok);

		List<String> choises = new LinkedList<String>();

		// Ajouter le choix de pouvoir revenir en arrière
		if (canCancel) {
			choises.add(UserInteration.RETURNWORD_STRING);
		}

		choises.addAll(listOfChoises);

		String choise;

		do {

			Game.DISPLAYER.displayMsg("--------------------------------------------------");

			Game.DISPLAYER.displayChoise(firstSentence, choises);

			choise = Game.INPUT.getString().toLowerCase().strip();

			if (choise.equals(UserInteration.EXITWORD_STRING)) {
				responceObjects.put("STATE", State.Exit);
				break;
			} else if (choise.equals(UserInteration.RETURNWORD_STRING)) {
				responceObjects.put("STATE", State.Cancel);
			}

			if (!choises.contains(choise)) {
				Game.DISPLAYER.displayError(UserInteration.INVALIDCHOISE_STRING);
			}

		} while (!choises.contains(choise));

		responceObjects.put("choice", choise);

		return responceObjects;

	}

}
