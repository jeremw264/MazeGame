package mazegame.character;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mazegame.Cell;
import mazegame.Game;
import mazegame.Hint;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;

/**
 * Classe Npc.
 */
public abstract class Npc extends Character {

	protected String dataFileName;
	private Hint hint;

	/**
	 * Constructeur de l'objet Npc.
	 *
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le personnage non joueur ce déplace.
	 */
	public Npc(int x, int y, Map map, String dataFileName) {
		super(x, y, map);
		this.dataFileName = dataFileName;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Affiche l'indice donné par le npc
	 */
	public void displayHint() {
		if (this.hint != null) {
			this.hint.displayHint(this);
		} else {
			Game.DISPLAYER.displayMsg("Désolé copain je n'ai pas d'indice pour toi :<");
		}
	}



	/**
	 * Attribue l'indice en paramètre au npc
	 * 
	 * @param hint
	 */
	public void setHint(Hint hint) {
		this.hint = hint;
	}
	

	/**
	 * Renvoie une action du personnage.
	 *
	 * @return Une action du personnage.
	 */
	@Override
	public Action getAction() {
		return new DoNothing();
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
	 * Parler avec le joueur.
	 */
	@SuppressWarnings("unchecked")
	public void talk() {

		JSONParser npcParse = new JSONParser();
		try {
			JSONArray npcData = (JSONArray) npcParse
					.parse(new FileReader(System.getProperty("user.dir") + "/data/" + this.dataFileName + ".json"));
			Collections.shuffle(npcData);
			JSONObject npc = (JSONObject) npcData.get(0);

			String content = (String) npc.get("content");
			List<String> answer = (JSONArray) npc.get("answer");
			List<String> c = (JSONArray) npc.get("correct");
			String responce;
			String answerNpc = (String) npc.get("answerNpc");
			String answerNpc2 = (String) npc.get("answerNpc2");

			Game.DISPLAYER.displayMsg(content);
			Game.DISPLAYER.displayChoise("Voici les réponses possibles : ", answer);
			responce = Game.INPUT.getString();
			if (c.contains(responce)) {
				Game.DISPLAYER.displayMsg(answerNpc);
				this.displayHint();
			} else {
				Game.DISPLAYER.displayMsg(answerNpc2);
				return;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
