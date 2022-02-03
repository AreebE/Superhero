package battlesystem;

public class PercentageModifier implements AbilityModifier<Integer>
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
    public Integer triggerModifier(
        Entity target, 
        Entity caster)
    {
        Entity source = (useCaster)? caster: target;
        int additionalStrength = (int) (source.getStatistic(stat) * percentage / 100.0);
        return additionalStrength;
    }

    @Override
    public Ability.Modifier getModifier(){
        return Ability.Modifier.PERCENTAGE;
    }
}