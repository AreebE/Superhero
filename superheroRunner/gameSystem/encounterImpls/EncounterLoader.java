package gameSystem.encounterImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.Encounter;

public class EncounterLoader {
	public static final String ONE_V_ALL = "one v all";
	public static final String FREE_FOR_ALL = "free for all";
	public static final String MULTI_TEAM = "multi team";
    
	public static HashMap<String, Encounter> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray encounters = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Encounter> listOfEncounters = new HashMap<>();
		for (int i = 0; i < encounters.length(); i++)
		{
			JSONObject encounter = encounters.getJSONObject(i);
			Encounter e = loadEncounter(encounter);
			// System.out.println(encounter);
			listOfEncounters.put(e.getName().toLowerCase(), e);
			
		}
		return listOfEncounters;
	}
	
	private static Encounter loadEncounter(JSONObject json)
	{
		switch (json.getString(Encounter.TYPE_KEY))
		{
			case ONE_V_ALL:
				return new OneVAll(json);
			case FREE_FOR_ALL:
				return new FreeForAll(json);
            case MULTI_TEAM:
                return new MultiTeamBattle(json);
		}
		return null;
	}
}
