package revitalizedLegends.gameSystem.abilityImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import revitalizedLegends.gameSystem.Ability;

public class AbilityLoader {
	public static final String ATTACK = "attack";
	public static final String ATTACK_STATUS = "attack status";
	public static final String CLEANSE = "cleanse";
	public static final String DEFENSE = "defense";
	public static final String PASS = "pass";
	public static final String SPAWNABLE = "spawnable";
	public static final String STATE = "state";
	public static final String SUPPORT = "support";
	
	
	public static HashMap<String, Ability> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
        // System.out.println(jsonString.substring(10000, 10200));
		JSONArray abilities = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Ability> listOfAbilities = new HashMap<>();
		for (int i = 0; i < abilities.length(); i++)
		{
			JSONObject json = abilities.getJSONObject(i);
			try 
			{
				Ability a = loadAbility(json);
				listOfAbilities.put(a.getName().toLowerCase(), a);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone + ", " + json.toString());
			}
		}
		return listOfAbilities;
	}
	
	static Ability loadAbility(JSONObject json)
	{
		switch(json.getString(Ability.TYPE_KEY))
		{
			case ATTACK:
				return new AttackAbility(json);
			case ATTACK_STATUS:
				return new AttackStatusAbility(json);
			case CLEANSE:
				return new CleanseAbility(json);
			case DEFENSE:
				return new DefenseAbility(json);
			case PASS:
				return new PassAbility(json);
			case SPAWNABLE:
				return new SpawnableAbility(json);
			case STATE:
				return new StateChangeAbility(json);
			case SUPPORT:
				return new SupportAbility(json);
		}
		return null;
	}
	
	
}
