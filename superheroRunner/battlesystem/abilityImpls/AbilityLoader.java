package battlesystem.abilityImpls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import battlesystem.Ability;
import battlesystem.AbilityModifier;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AbilityLoader {
	public static final String ATTACK = "attack";
	public static final String ATTACK_STATUS = "attack status";
	public static final String CLEANSE = "cleanse";
	public static final String DEFENSE = "defense";
	public static final String PASS = "pass";
	public static final String SPAWNABLE = "spawnable";
	public static final String STATE = "state";
	public static final String SUPPORT = "support";
	
	public static final String GROUP = "group";
	public static final String MULTI_CAST = "multi cast";
	public static final String PERCENTAGE = "percentage";
	public static final String RANDOM = "random";
	
	public static HashMap<String, Ability> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
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
				System.out.println(jsone + ", " + json);
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
	
	static AbilityModifier loadModifier(JSONObject json)
	{
		switch(json.getString(AbilityModifier.TYPE_KEY))
		{
			case GROUP:
				return new GroupModifier(json);
			case MULTI_CAST:
				return new MultiCastModifier(json);
			case RANDOM:
				return new RandomModifier(json);
			case PERCENTAGE:
				return new PercentageModifier(json);
		}
		return null;
	}

}
