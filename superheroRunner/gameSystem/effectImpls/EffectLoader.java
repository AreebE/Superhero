package gameSystem.effectImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.Ability;
import gameSystem.Effect;

public class EffectLoader {
	public static final String DECAY = "decay";
	public static final String DELAY = "delay";
	public static final String GROUP = "group";
	public static final String INSTANT = "instant";
	public static final String ONE_TIME = "one time";
	public static final String PASSIVE = "passive";
	public static final String DELAYED_PASSIVE = "delayed passive";
    
	public static HashMap<String, Effect> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray effects = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Effect> listOfEffects = new HashMap<>();
		for (int i = 0; i < effects.length(); i++)
		{
			JSONObject json = effects.getJSONObject(i);
			try 
			{
				Effect e = loadEffect(json);
				listOfEffects.put(e.getName().toLowerCase(), e);
			    // System.out.println(e.getName() + ", " + e);
            }
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone + " , " + json.toString());
			}
		}
		return listOfEffects;
	}
	
	public static Effect loadEffect(JSONObject json)
	{
		switch(json.getString(Effect.TYPE_KEY))
		{
			case DECAY:
				return new DecayEffect(json);
			case DELAY:
				return new DelayedEffect(json);
			case GROUP:
				return new GroupEffect(json);
			case INSTANT:
				return new InstantEffect(json);
			case ONE_TIME:
				return new OneTimeEffect(json);
			case PASSIVE:
				return new PassiveEffect(json);
            case DELAYED_PASSIVE:
                return new DelayedPassive(json);
			default:
				return new Effect(json);
		}
	}
}
