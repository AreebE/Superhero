package gameSystem;

import java.util.List;

import org.json.JSONObject;

import gameSystem.abilityImpls.AbilityLoader;

public class ConditionModifier implements AbilityModifier
{

	private static final String ITEM_KEY = "item";
	private static final String USE_KEY = "will consume";
	
	private ConditionItem item;
	private boolean useItems;

	public ConditionModifier(JSONObject json)
	{
		item = ConditionLoader.loadConditionItem(json.getJSONObject(ITEM_KEY));
		useItems = json.getBoolean(USE_KEY);
	}
	
	public ConditionModifier(
			ConditionItem item)
	{
		this.item = item;
	}
	
	@Override
	public boolean verifyValidity(Storage s) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog Log) {
		// TODO Auto-generated method stub
		boolean canUse = item.meetsRequirements(caster);
		if (!canUse)
		{
			holder.doNotSetCooldown();
		}
		else if (useItems)
		{
			item.changeEntity(caster);
		}
		return canUse;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return Ability.NECESSARY_PRIORITY;
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(TYPE_KEY, ModifierLoader.CONDITIONAL);
		json.put(USE_KEY, useItems);
		json.put(ITEM_KEY, item.toJson());
		return json;
	}
        
}