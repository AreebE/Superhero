package gameSystem;

import java.util.Collection;

import org.json.JSONObject;

public class ModifierLoader {
	
	public static final String GROUP = "group";
	public static final String MULTI_CAST = "multi cast";
	public static final String PERCENTAGE = "percentage";
	public static final String RANDOM = "random";
	public static final String CONDITIONAL = "conditional";
	
	public static AbilityModifier loadModifier(JSONObject json)
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
			case CONDITIONAL:
				return new ConditionModifier(json);
		}
		return null;
	}

}
