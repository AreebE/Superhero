package revitalizedLegends.gameSystem.stackImpls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.ItemStack;
import revitalizedLegends.gameSystem.Shield;
import revitalizedLegends.gameSystem.shieldImpls.ConditionalShield;
import revitalizedLegends.gameSystem.shieldImpls.DeathShield;
import revitalizedLegends.gameSystem.shieldImpls.DualShield;
import revitalizedLegends.gameSystem.shieldImpls.ReflectiveShield;
import revitalizedLegends.gameSystem.shieldImpls.SelfDeathShield;
import revitalizedLegends.gameSystem.shieldImpls.SelfShield;
import revitalizedLegends.gameSystem.shieldImpls.TrapShield;

public class StackLoader {
	public static HashMap<String, ItemStack> parseJSONFile(String src) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(src));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray items = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, ItemStack> listOfStacks = new HashMap<>();
		for (int i = 0; i < items.length(); i++)
		{
			JSONObject json = items.getJSONObject(i);
			try 
			{
//				Shield s = loadItem(json);
//				System.out.println(json.toString());
//				listOfStacks.put(s.getName().toLowerCase(), s);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone.toString() + " , " + json.toString());
			}
		}
		return listOfStacks;
	}
	
	public static ItemStack loadItem(JSONObject json)
	{
//		switch(json.getString(ItemStack.TYPE_KEY))
//		{
//			
//        }
		return null;
	}
}
