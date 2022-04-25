package mazegame.character;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mazegame.Game;
import mazegame.Map;
import mazegame.action.Action;
import mazegame.action.DoNothing;

/**
 * Classe Npc.
 */
public abstract class Npc extends Character {
	
	private String DataFileName = getClass().getSimpleName()+".json";

	/**
	 * Constructeur de l'objet Npc.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le personnage non joueur ce déplace.
	 */
	public Npc(int x, int y, Map map, String DataFileName) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
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
	public boolean talk() {
		
	JSONParser npc = new JSONParser();
    try {
       JSONObject sphinx = (JSONObject)npc.parse(new FileReader(System.getProperty("user.dir")+"/data/"+this.DataFileName+".json"));
       
       
       String content = (String) npc.get("content");
       String answer = (String) npc.get("answers");
       String c = (String) npc.get("correct");
       String nc = (String) npc.get("nextCorrect");
       String nic = (String) npc.get("nextIncorrect");
       do {
      	 Game.DISPLAYER.displayMsg(content);
      	 Game.DISPLAYER.displayMsg(answer);
      	 String responce = Game.INPUT.getString().toLowerCase();
      	 if (responce.equals(c)) {
      		 Game.DISPLAYER.displayMsg(nc);
      		 return true;
      		 }
      	 else {
      		 Game.DISPLAYER.displayMsg(nic);
      		 return false;
      		 }
      	 } while(!((npc.get("nextCorrect") == null) && (npc.get("nextIncorrect") == null)));
       } 
    catch (FileNotFoundException e) {
  	  e.printStackTrace();
  	  }
    catch (IOException e) {
  	  e.printStackTrace();
  	  }
    catch (ParseException e) {
        e.printStackTrace();
     }
	}

}
