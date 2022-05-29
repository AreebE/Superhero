package gameSystem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Event
	implements Saveable
{
	public static final String TYPE_KEY = "type";
	private static final String NAME_KEY = "name";
	private static final String PRELUDE_KEY = "prelude";
	private static final String POSTLUDE_KEY = "postludes";
	private static final String PROMPT_KEY = "prompt";
	private static final String CHOICES_KEY = "choices";
	
	public static final int ACTION = 1;
	public static final int DESCRIPTION = 0;
	private String name;
	private ArrayList<String> prelude;
	private ArrayList<ArrayList<String>> postludes;
	private ArrayList<String[]> choices;
	private String prompt;

	public Event(JSONObject json)
	{
		name = json.getString(NAME_KEY);
		prompt = json.getString(PROMPT_KEY);
		
		prelude = new ArrayList<>();
		JSONArray jsonPrelude = json.getJSONArray(PRELUDE_KEY);
		for (int i = 0; i < jsonPrelude.length(); i++)
		{
			prelude.add(jsonPrelude.getString(i));
		}
		
		postludes = new ArrayList<>();
		JSONArray jsonPostludes = json.getJSONArray(POSTLUDE_KEY);
		for (int i = 0; i < jsonPostludes.length(); i++)
		{
			JSONArray jsonSinglePostlude = jsonPostludes.getJSONArray(i);
			ArrayList<String> singlePostlude = new ArrayList<>();
			for (int j = 0; j < jsonSinglePostlude.length(); j++)
			{
				singlePostlude.add(jsonSinglePostlude.getString(j));
			}
			postludes.add(singlePostlude);
		}
		
		choices = new ArrayList<>();
		JSONArray choicesJson = json.getJSONArray(CHOICES_KEY);
		for (int i = 0; i < choicesJson.length(); i++)
		{
			JSONArray choiceOptionsJson = choicesJson.getJSONArray(i);
			String[] choiceOptions = new String[choiceOptionsJson.length()];
			for (int j = 0; j < choiceOptionsJson.length(); j++)
			{
				choiceOptions[j] = choiceOptionsJson.getString(j);
			}
			choices.add(choiceOptions);
		}
	}
	
	public Event(
			String title,
			ArrayList<String> preludeLines,
			ArrayList<ArrayList<String>> postludeLines,
			ArrayList<String[]> choices,
			String prompt)
	{
		this.name = title;
		this.prelude = preludeLines;
		this.postludes = postludeLines;
		this.choices = choices;
		this.prompt = prompt;
	}
	
	public boolean executeEvent(
			EntityInfoItem protag, 
			OutputSystem output,
			InputSystem input,
			Storage s,
			BattleLog log)
	{
		for (int i = 0; i < prelude.size(); i++)
		{
			output.displayString(prelude.get(i), 0);
		}
		
		int choice = input.getChoice(prompt, choices) - 1;
		boolean isGameOver = executeAction(choices.get(choice), log, s, protag, input, output);
		if (isGameOver)
		{
			return true;
		}
		ArrayList<String> postlude = postludes.get(choice);
		for (int i = 0; i < postlude.size(); i++)
		{
			output.displayString(postlude.get(i), 0);
		}
		return false;
	}

	protected abstract boolean executeAction(
			String[] string, 
			BattleLog log,
			Storage s, 
			EntityInfoItem protag, 
			InputSystem input, 
			OutputSystem output);

	@Override
	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		json.put(NAME_KEY, name);
		json.put(PROMPT_KEY, prompt);
		
		JSONArray preludeJson = new JSONArray();
		for (int i = 0; i < prelude.size(); i++)
		{
			preludeJson.put(prelude.get(i));
		}
		json.put(PRELUDE_KEY, preludeJson);
		
		JSONArray postludeJson = new JSONArray();
		for (int i = 0; i < postludes.size(); i++)
		{
			ArrayList<String> singlePostlude = postludes.get(i);
			JSONArray singlePostludeJson = new JSONArray();
			for (int j = 0; j < singlePostlude.size(); j++)
			{
				singlePostludeJson.put(singlePostlude.get(j));
			}
			postludeJson.put(singlePostludeJson);
		}
		json.put(POSTLUDE_KEY, postludeJson);
		
		JSONArray choicesJson = new JSONArray();
		for (int i = 0; i < choices.size(); i++)
		{
			String[] singleChoiceItem = choices.get(i);
			JSONArray singleChoiceItemJson = new JSONArray();
			for (int j = 0; j < singleChoiceItem.length; j++)
			{
				singleChoiceItemJson.put(singleChoiceItem[j]);
			}
			choicesJson.put(singleChoiceItemJson);
		}
		json.put(CHOICES_KEY, choicesJson);
		return json;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	protected ArrayList<String[]> getChoices() {
		// TODO Auto-generated method stub
		return choices;
	}
}