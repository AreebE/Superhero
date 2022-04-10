package battlesystem.modifiers;

import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Entity;

public class PercentageModifier implements AbilityModifier
{

    private int percentage;
    private Entity.Statistic stat;
    private boolean useCaster;

    public PercentageModifier(
        int percentage,
        Entity.Statistic stat,
        boolean useCaster
    )
    {
        this.percentage = percentage;
        this.stat = stat;
        this.useCaster = useCaster;
    }
    

    @Override 
    public boolean triggerModifier(
        List<Entity> targets, 
        Entity caster,
        Ability holder,
        BattleLog log
        )
    {
        Entity source = (useCaster)? caster: targets.get(0);
        int additionalStrength = (int) (source.getStatistic(stat) * percentage / 100.0);
        holder.adjustAdditionalStrength(additionalStrength, false);
        return true;
    }

    @Override
    public int getPriority(){
        return Ability.ADJUSTMENT_PRIORITY;
    }
}