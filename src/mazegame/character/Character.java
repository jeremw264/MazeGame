package mazegame.character;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import mazegame.Cell;
import mazegame.Direction;

abstract public class Character {

	// Position du personnage.
	public int x;
	public int y;
	// Cellule courante du personnage.
	public Cell currentCell;

	/**
	 * Constructeur de l'objet Character.
	 * @param seedX Position de départ horizontale.
	 * @param seedY Position de départ verticale.
	 */
	protected Character(int seedX, int seedY) {
		this.x = seedX;
		this.y = seedY;
	}

	/**
	 * Renvoie la cellule sur laquelle ce trouve le personnage.
	 * 
	 * @return La cellule ou ce situe le personnage.
	 */
	public Cell getCurrentCell() {
		return this.currentCell;
	}

	/**
	 * Définit la nouvelle cellule où ce situe le personnage.
	 * 
	 * @param currentCell Cellule où est le personnage.
	 */
	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

	/**
	 * Renvoie la position horizontale du personnage.
	 * 
	 * @return Indice qui correspond a la position horizontale.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Renvoie la position verticale du personnage.
	 * 
	 * @return Indice qui correspond a la position verticale.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Renvoie si le personnage peut bouger ou non.
	 * 
	 * @return True si le personnage peut bouger, False sinon.
	 */
	public abstract boolean canBeLeft();

	public boolean speak() {
		
		List<String> responceList = new ArrayList<String>();

		try {
			
			// On recupere le fichier qu'on transforme en String pour crée l'objet JSON
			String jsonData = Files.readString(Paths.get(System.getProperty("user.dir") + "/data/data.json"));
			JSONObject jsonObject = new JSONObject(jsonData);
			
			// On recupere les données pour le personnage concerner
			jsonObject = jsonObject.getJSONObject(this.getClass().getSimpleName());
			// On recupere les dialogues
			JSONArray discusions = jsonObject.getJSONArray("talk");
			
			// On recupere un dialogue de maniere aléatoire
			int discutionIndex = (int)Math.random()*discusions.length();
			JSONObject objetDiscusion = new JSONObject(discusions.get(discutionIndex).toString());
			
			// On recupere la question
			String questionString = objetDiscusion.getString("question");
			// On récupere les réponses possible
			JSONArray answersArray = objetDiscusion.getJSONArray("answer");
			
			for (Object object : answersArray) {
				responceList.add((String)object);
			}
			
			// Affichage Question
			System.out.println(questionString);
			
			// Affichage Reponse possible
			for (String answer : responceList) {
				System.out.println(answer);
			}

		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		}
		
		return false;
	}
	

	/**
	 * Renvoie toute les directions accesible dupuis la case courante du personnage.
	 * 
	 * @return Une liste des directions accesible.
	 */
	public List<Direction> getAccesibleDirections() {
		List<Direction> accesibleDirections = new ArrayList<>(4);

		for (Direction direction : Direction.values()) {
			if (!this.currentCell.wallExist(direction)) {
				accesibleDirections.add(direction);
			}
		}

		return accesibleDirections;
	}
}
