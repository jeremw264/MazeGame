package mazegame.main;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import mazegame.Direction;
import mazegame.Maze;
import mazegame.generation.*;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Class MainGame
 */
public class MainGame {

	
	/**
	 * Class d'execution du programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"/src/mazegame/main/db.json"));

            JSONObject jsonObject =  (JSONObject) obj;
            
            Object fouJsonObject = jsonObject.get("fou");
            
            
            System.out.println(jsonObject.keySet());
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		}
		

	}

}
