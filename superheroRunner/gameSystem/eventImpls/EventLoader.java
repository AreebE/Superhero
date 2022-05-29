package gameSystem.eventImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.Event;


public class EventLoader {
	
	public static final String BATTLE = "battle";
	public static final String TRADE = "trade";
	
	public static HashMap<String, Event> parseJsonFile(String fileSrc) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(fileSrc));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray eventsJson = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Event> listOfEvents = new HashMap<>();
		for (int i = 0; i < eventsJson.length(); i++)
		{
			JSONObject json = eventsJson.getJSONObject(i);
			try 
			{
				Event e = loadEvent(json);
				listOfEvents.put(e.getName().toLowerCase(), e);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone + ", " + json.toString());
			}
		}
		return listOfEvents;
	}
	
	public static Event loadEvent(JSONObject json)
	{
		switch(json.getString(Event.TYPE_KEY))
		{
			case BATTLE:
				return new BattleEvent(json);
			case TRADE:
				return new TradeEvent(json);
			default:
				return null;
		}		
	}
	
}
