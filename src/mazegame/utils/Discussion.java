package mazegame.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Discussion {

	private final String characterName;

	private JSONObject discusionDataObject;
	
		
	private JSONObject talkObject;
	


	public Discussion(String characterName) {

		this.characterName = characterName;
		this.getData();
		// TODO Auto-generated constructor stub
	}

	private void getData() {

		// On recupere le fichier qu'on transforme en String pour crée l'objet JSON
		String jsonData = null;
		try {
			jsonData = Files
					.readString(Paths.get(System.getProperty("user.dir") + "/data/" + this.characterName + ".json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.discusionDataObject = new JSONObject(jsonData);

	}
	
	private void setupTalk() {
		// On recupere la profondeur 1
		JSONObject depthOne = discusionDataObject.getJSONObject("1");
		
		List<String> keys = new LinkedList<>();
		
		for (String string : depthOne.keySet()) {
			keys.add(string);
		}
		
		Collections.shuffle(keys);
		
		System.out.println(keys.get(0));
		
		this.talkObject = depthOne.getJSONObject(keys.get(0));
		
	}

	/**
	 * @return List
	 */
	public Map<String, Object> getTalk() {
		
		Map<String, Object> talkMap = this.talkObject.toMap();
		Map<String, Object> talk = new HashMap<>();
		
		talk.put("content", talkMap.get("content"));
		talk.put("answers", talkMap.get("answers"));
		
		return talk;
	}



	public void next(String id) {

	}
	
	public static void main(String[] args) {
		Discussion discussion = new Discussion("Imp");
		discussion.setupTalk();
		System.out.println(discussion.getTalk());
	}

}
