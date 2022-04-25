package mazegame.character;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mazegame.Game;
import mazegame.Map;

/**
 * Classe Player
 */
public abstract class Player extends Character {

	/**
	 * Constructeur de l'objet Player.
	 * 
	 * @param x   Position verticale de départ.
	 * @param y   Position horizontale de départ.
	 * @param map La carte sur laquelle le joueur ce déplace.
	 */
	public Player(int x, int y, Map map) {
		super(x, y, map);
		this.getCell().setVisited();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		super.move();
		this.getCell().setVisited();
	}

	public static void talk() {
		// TODO Auto-generated method stub
		JSONParser jsonP = new JSONParser();
	      try {
	         JSONObject sphinx = (JSONObject)jsonP.parse(new FileReader(System.getProperty("user.dir")+"/data/Sphinx.json"));
	         /*JSONObject imp = (JSONObject)jsonP.parse(new FileReader("Imp.json"));
	         JSONObject vendor = (JSONObject)jsonP.parse(new FileReader("Vendor.json"));
	         JSONObject data = (JSONObject)jsonP.parse(new FileReader("data.json"));*/
	         String content = (String) sphinx.get("content");
	         String answer = (String) sphinx.get("answers");
	         String c = (String) sphinx.get("correct");
	         String nc = (String) sphinx.get("nextCorrect");
	         String nic = (String) sphinx.get("nextIncorrect");
	         do {
	        	 Game.DISPLAYER.displayMsg(content);
	        	 Game.DISPLAYER.displayMsg(answer);
	        	 String rp = Game.INPUT.getString().toLowerCase();
	        	 if (rp.equals(c)) {
	        		 Game.DISPLAYER.displayMsg(nc);
	        		 }
	        	 else {
	        		 Game.DISPLAYER.displayMsg(nic);
	        		 }
	        	 } while(!((sphinx.get("nextCorrect") == null) && (sphinx.get("nextIncorrect") == null)));
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
