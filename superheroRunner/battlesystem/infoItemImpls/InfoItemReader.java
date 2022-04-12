package battlesystem.infoItemImpls;

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
import battlesystem.effectImpls.DecayEffect;
import battlesystem.effectImpls.DelayedEffect;
import battlesystem.effectImpls.GroupEffect;
import battlesystem.effectImpls.InstantEffect;
import battlesystem.effectImpls.OneTimeEffect;
import battlesystem.effectImpls.PassiveEffect;

public class InfoItemReader {
	public static final String PATTERN_AI_INFO = "simple ai";
	
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
				System.out.println(jsone + ", kmolk" + json.getString("name"));
			}
		}
		return listOfEntities;
	}
	
	public static EntityInfoItem loadItem(JSONObject json)
	{
		switch(json.getString(EntityInfoItem.TYPE_KEY))
		{
			case PATTERN_AI_INFO:
				return new AIInfoItem(json);
			default:
				return new EntityInfoItem(json);
		}
	}
}
