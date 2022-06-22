package gameSystem.shieldImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.Effect;
import gameSystem.Shield;
import gameSystem.State;
import gameSystem.stateImpls.NormalState;
import gameSystem.stateImpls.StunState;
import gameSystem.stateImpls.VigorState;

public class ShieldLoader {
	
	public static final String DEATH = "death";
	public static final String DUAL = "dual";
	public static final String SELF_DEATH = "self death";
	public static final String SELF = "self";
	public static final String TRAP = "trap";
    public static final String REFLECTIVE = "reflective";
    
	public static HashMap<String, Shield> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray items = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Shield> listOfShields = new HashMap<>();
		for (int i = 0; i < items.length(); i++)
		{
			JSONObject json = items.getJSONObject(i);
			try 
			{
				Shield s = loadItem(json);
//				System.out.println(json.toString());
				listOfShields.put(s.getName().toLowerCase(), s);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone.toString() + " , " + json.toString());
			}
		}
		return listOfShields;
	}
	
	public static Shield loadItem(JSONObject json)
	{
		switch(json.getString(Effect.TYPE_KEY))
		{
			case DEATH:
				return new DeathShield(json);
			case DUAL:
				return new DualShield(json);
			case SELF_DEATH:
				return new SelfDeathShield(json);
			case SELF:
				return new SelfShield(json);
			case TRAP:
				return new TrapShield(json);
            case REFLECTIVE:
                return new ReflectiveShield(json);
        }
		return null;
	}
}
