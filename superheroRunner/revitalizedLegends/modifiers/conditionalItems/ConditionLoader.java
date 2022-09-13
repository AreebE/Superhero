package revitalizedLegends.modifiers.conditionalItems;

import java.util.Collection;

import org.json.JSONObject;

public class ConditionLoader 
{
	
	public static final String SCALEABLE = "scaleable";

	public static ConditionItem loadConditionItem(
		JSONObject json	
	)
	{
		switch (json.getString(ConditionItem.TYPE_KEY))
		{
			case SCALEABLE:
				return new ScaleableItem(json);
			case "basic":
			default:
				return new ConditionItem(json);
		}
	}


}
