package gameSystem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Campaign 
	implements Saveable{
	
	private static final String EVENT_KEY = "events";
	private static final String PROTAG_NAME = "protag";
	private static final String NAME_KEY = "name";
	
	public static final String TYPE_KEY = "type";
	
	private ArrayList<String> events;
	private String protagName;
	private String name;
	
	private OutputSystem output;
	private InputSystem input;
	
	public Campaign(
			JSONObject json)
	{
		this.name = json.getString(NAME_KEY);
		this.protagName = json.getString(PROTAG_NAME);
		this.events = new ArrayList<>();
		JSONArray jsonEvents = json.getJSONArray(EVENT_KEY);
		for (int i = 0; i < jsonEvents.length(); i++)
		{
			events.add(jsonEvents.getString(i));
		}
	}
	public Campaign(
			ArrayList<String> events,
			String protagonist,
			String name)
	{
		this.name = name;
		this.events = events;
		this.protagName = protagonist;	
	}
	
	public void beginCampaign(
			Storage s,
			BattleLog log)
	{
		EntityInfoItem protagonist = s.getEntity(protagName).copy();
		output.displayString(name + "\n", 0);
		for (int i = 0; i < events.size(); i++)
		{
			Event current = s.getEvent(events.get(i));
			boolean isGameOver = current.executeEvent(protagonist, output, input, s, log);
			output.displayString("\n", i);
			if (isGameOver)
			{
				return;
			}
		}
	}
	
	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(TYPE_KEY, "basic");
		json.put(NAME_KEY, name);
		json.put(PROTAG_NAME, protagName);
		
		JSONArray eventsJson = new JSONArray();
		for (int i = 0; i < events.size(); i++)
		{
			eventsJson.put(events.get(i));
		}
		json.put(EVENT_KEY, eventsJson);
		return json;
	}
	
	@Override
	public boolean verifyValidity(Storage s) {
		for (int i = 0; i < events.size(); i++)
		{
			if (s.getEvent(events.get(i)) == null)
			{
				return false;
			}
		}
		return s.getEntity(protagName) != null;
	}
	
	public void setInput(InputSystem input)
	{
		this.input = input;
	}
	
	public void setOutput(OutputSystem output)
	{
		this.output = output;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
