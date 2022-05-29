package gameSystem.campaignImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import gameSystem.Ability;
import gameSystem.Campaign;

public class CampaignLoader {
	
	public static HashMap<String, Campaign> parseJsonFile(String fileSrc) throws FileNotFoundException
	{
		Scanner fileReader = new Scanner(new File(fileSrc));
		StringBuilder jsonString = new StringBuilder();
		while (fileReader.hasNext())
		{
			jsonString.append(fileReader.nextLine());
		}
		JSONArray campaignsJson = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		HashMap<String, Campaign> listOfCampaigns = new HashMap<>();
		for (int i = 0; i < campaignsJson.length(); i++)
		{
			Object json = campaignsJson.get(i);
			System.out.println(json.getClass());
			try 
			{
				Campaign c = loadCampaign((JSONObject) json);
				listOfCampaigns.put(c.getName().toLowerCase(), c);
			}
			catch(JSONException|NullPointerException jsone)
			{
				System.out.println(jsone + ", " + json.toString());
			}
		}
		return listOfCampaigns;
	}
	
	public static Campaign loadCampaign(JSONObject json)
	{
		switch(json.getString(Campaign.TYPE_KEY))
		{
			default:
				return new Campaign(json);
		}		
	}
}
