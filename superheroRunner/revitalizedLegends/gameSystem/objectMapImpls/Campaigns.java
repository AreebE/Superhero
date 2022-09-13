package revitalizedLegends.gameSystem.objectMapImpls;

import java.util.ArrayList;

import org.json.JSONArray;

import revitalizedLegends.gameSystem.Campaign;

public class Campaigns {
	private static ArrayList<Campaign> CAMPAIGNS = new ArrayList<Campaign>()
			{{
				add(new Campaign
						(
								new ArrayList<String>()
								{{
									add("Stupid King...");
									add("Friday Night Out");
									add("Engineering a Meme");
									add("Imposter");
									add("Intervention at Sawcon");
								}},
								"Joe",
								"Too many Jokes"
						)
				);
			}};
			
	public static JSONArray saveCampaigns()
	{
		JSONArray json = new JSONArray();
    	for (int i = 0; i < CAMPAIGNS.size(); i++)
    	{
    		json.put(CAMPAIGNS.get(i).toJson());
    		
    	}
		return json;
	}
}
