package gameSystem;

import org.json.JSONObject;

public class ConditionLoader 
{
	
	public static ConditionItem loadConditionItem(
		JSONObject json	
	)
	{
		switch (json.getString(ConditionItem.TYPE_KEY))
		{
			case "basic":
			default:
				return new ConditionItem(json);
		}
	}


}
