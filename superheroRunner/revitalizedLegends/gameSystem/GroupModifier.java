package revitalizedLegends.gameSystem;

import java.util.List;

import org.json.JSONObject;

import revitalizedLegends.modifiers.abilityMods.AbilityModifier;
import revitalizedLegends.modifiers.abilityMods.ModifierLoader;

public class GroupModifier implements AbilityModifier
{
	private static final String LIMIT_KEY = "limit";
    private int limit;

    public GroupModifier(JSONObject json)
    {
    	limit = json.getInt(LIMIT_KEY);
    }
    
    public GroupModifier(int limit)
    {
        this.limit = limit;
    }

    @Override 
    public boolean triggerModifier(List<Entity> targets, Entity caster, Ability holder, Game g, BattleLog log)
    {
    	for (int i = 0; i < targets.size(); i++)
    	{
    		holder.castAbility(targets.get(i), caster, g, log);
    		if (!holder.continueAttacking())
    		{
    			Object[] contents = new Object[]{BattleLog.Entry.Interruption.SHIELD};
                log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
                return false;                
    		}
    	}
    	return true;
    }

    @Override
    public int getPriority()
    {
        return Ability.HIT_PRIORITY;
    }

    public int getLimit()
    {
    	return limit;
    }

	@Override
	public JSONObject toJson() 
	{
		JSONObject modifier = new JSONObject();
		modifier.put(TYPE_KEY, ModifierLoader.GROUP);
		modifier.put(LIMIT_KEY, limit);
		return modifier;
	}

    @Override 
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
}