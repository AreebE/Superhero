package revitalizedLegends.modifiers.abilityMods;

import java.util.List;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.Ability;
import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Storage;
import revitalizedLegends.gameSystem.Entity.Statistic;
import revitalizedLegends.gameSystem.abilityImpls.AbilityLoader;

public class PercentageModifier implements AbilityModifier
{

	private static final String PERCENTAGE_KEY = "percentage";
	private static final String STAT_KEY = "stat";
	private static final String CASTER_KEY = "use caster";
    private int percentage;
    private Entity.Statistic stat;
    private boolean useCaster;

    public PercentageModifier(JSONObject json)
    {
    	percentage = json.getInt(PERCENTAGE_KEY);
    	stat = Entity.Statistic.getStatistic(json.getString(STAT_KEY));
    	useCaster = json.getBoolean(CASTER_KEY);
    }
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
        Game g,
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


	@Override
	public JSONObject toJson() {
		JSONObject modifier = new JSONObject();
		modifier.put(TYPE_KEY, ModifierLoader.PERCENTAGE);
		modifier.put(CASTER_KEY, useCaster);
		modifier.put(PERCENTAGE_KEY, percentage);
		modifier.put(STAT_KEY, stat.name);
		return modifier;
	}

    @Override 
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
    
}