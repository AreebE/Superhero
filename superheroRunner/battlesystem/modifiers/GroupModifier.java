package battlesystem.modifiers;

import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Entity;

public class GroupModifier implements AbilityModifier
{
    private int percentage;
    private int limit;

    public GroupModifier(int percentage, int limit)
    {
        this.percentage = percentage;
        this.limit = limit;
    }

    @Override 
    public boolean triggerModifier(List<Entity> targets, Entity caster, Ability holder, BattleLog log)
    {
    	holder.adjustAdditionalStrength(percentage, true);
    	for (int i = 0; i < targets.size(); i++)
    	{
    		holder.castAbility(targets.get(i), caster, targets, log);
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
    
}