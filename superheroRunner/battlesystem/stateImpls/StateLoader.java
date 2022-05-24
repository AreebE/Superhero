package battlesystem.stateImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import battlesystem.Effect;
import battlesystem.EntityInfoItem;
import battlesystem.State;

public class StateLoader {
	
	public static final String NORMAL = "normal";
	public static final String STUN = "stun";
	public static final String VIGOR = "vigor";
	
	public static HashMap<String, State> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray items = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, State> listOfStates = new HashMap<>();
		for (int i = 0; i < items.length(); i++)
		{
			JSONObject json = items.getJSONObject(i);
			try 
			{
//				System.out.println(json);
				State s = loadItem(json);
				listOfStates.put(s.getName().toLowerCase(), s);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println("exception " + jsone.toString() + ", " + json.toString());
			}
		}
		return listOfStates;
	}
	
	public static State loadItem(JSONObject json)
	{
		switch(json.getString(State.TYPE_KEY))
		{
			case NORMAL:
				return new NormalState(json);
			case STUN:
				return new StunState(json);
			case VIGOR:
				return new VigorState(json);
		}
		return null;
	}
}
