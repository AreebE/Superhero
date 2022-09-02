package modifiers.abilityMods;

import java.util.List;

import org.json.JSONObject;

import gameSystem.Ability;
import gameSystem.BattleLog;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Storage;
import modifiers.conditionalItems.ConditionItem;
import modifiers.conditionalItems.ConditionLoader;

public class ConditionModifier implements AbilityModifier
{

	private static final String ITEM_KEY = "item";
	private static final String USE_KEY = "will consume";
	private static final String ON_TARGET_KEY = "uses target";
	private ConditionItem item;
	private boolean useItems;
    private boolean onTarget;

	public ConditionModifier(JSONObject json)
	{
		item = ConditionLoader.loadConditionItem(json.getJSONObject(ITEM_KEY));
		useItems = json.getBoolean(USE_KEY);
        onTarget = json.getBoolean(ON_TARGET_KEY);
	}
	
	public ConditionModifier(
			ConditionItem item,
            boolean useItems,
            boolean onTarget)
	{
		this.item = item;
	}
	
	@Override
	public boolean verifyValidity(Storage s) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean triggerModifier(List<Entity> target, Entity caster, Ability holder, Game g, BattleLog log) {
		// TODO Auto-generated method stub
		boolean canUse = item.meetsRequirements((onTarget)? target.get(0): caster);
		if (!canUse)
		{
			holder.doNotSetCooldown();
            holder.doNotCast();
            Object[] contents = new Object[]{BattleLog.Entry.Interruption.NO_CONDITION_MET, item.getNumRequired(), item.getName(), item.getComparison()};
			log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
        }
		else if (useItems)
		{
			item.changeEntity(caster, log);
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