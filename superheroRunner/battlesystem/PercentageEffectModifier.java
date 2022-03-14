package battlesystem;

public class PercentageEffectModifier implements EffectModifier<Integer>
{
    private Entity.Statistic stat;
    private Integer percent;

    public PercentageEffectModifier(
        Entity.Statistic stat,
        Integer percent
    )
    {
        this.stat = stat;
        this.percent = percent;
    }

    @Override 
    public Integer applyEffect(Entity creator)
    {
        return creator.getStatistic(stat) * percent / 100;
    }

    @Override
    public Effect.Modifier getName()
    {
        return Effect.Modifier.PERCENT;
    }

}