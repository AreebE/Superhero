package revitalizedLegends.gameSystem.infoItemImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.EntityInfoItem;
import revitalizedLegends.gameSystem.effectImpls.DecayEffect;
import revitalizedLegends.gameSystem.effectImpls.DelayedEffect;
import revitalizedLegends.gameSystem.effectImpls.GroupEffect;
import revitalizedLegends.gameSystem.effectImpls.InstantEffect;
import revitalizedLegends.gameSystem.effectImpls.OneTimeEffect;
import revitalizedLegends.gameSystem.effectImpls.PassiveEffect;

public class InfoItemReader {
	public static final String PATTERN_CONTROLLABLE_AI_INFO = "simple controllable ai";
	public static final String SIMPLE_AI_INFO_ITEM = "simple ai";
    
	public static HashMap<String, EntityInfoItem> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray items = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, EntityInfoItem> listOfEntities = new HashMap<>();
		for (int i = 0; i < items.length(); i++)
		{
			JSONObject json = items.getJSONObject(i);
//			System.out.println(json.toString());
			try 
			{
				EntityInfoItem e = loadItem(json);
				listOfEntities.put(e.getName().toLowerCase(), e);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone + ", " + json.getString("name"));
			}
		}
		return listOfEntities;
	}
	
	public static EntityInfoItem loadItem(JSONObject json)
	{
//		System.out.println(json.getString(EntityInfoItem.TYPE_KEY) + ", " + json.getString("name"));
//		System.out.println(json.getString("name") + ", " + json.getString(EntityInfoItem.TYPE_KEY));
        switch(json.getString(EntityInfoItem.TYPE_KEY))
		{
			case PATTERN_CONTROLLABLE_AI_INFO:
				return new ControllableAutoEntityInfoItem(json);
            case SIMPLE_AI_INFO_ITEM:
                // System.out.println("create");
                return new BaseAIInfoItem(json);
            default:
				return new EntityInfoItem(json);
		}
	}

    public static MoveItem loadMoveItem(JSONObject json)
    {
        switch (json.getString(MoveItem.TYPE_KEY))
        {
            case "basic":
            	return new MoveItem(json);
        }
        return null;
    }
}
