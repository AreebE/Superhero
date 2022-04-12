package battlesystem;

/**
 * A percentage modifier for effects, based on some statistic
 *
 */
//public class PercentageEffectModifier implements EffectModifier<Integer>
//{
//    private Entity.Statistic stat;
//    private Integer percent;
//
//    /**
//     * A constructor for choosing a statistifc
//     * @param stat the stat to use
//     * @param percent how much percent out of 100 to use
//     */
//    public PercentageEffectModifier(
//        Entity.Statistic stat,
//        Integer percent
//    )
//    {
//        this.stat = stat;
//        this.percent = percent;
//    }
//
//    /**
//     * Apply the effect by returning the statistic times a percent of it
//     * @param creator the person the effect applies to
//     */
//    @Override 
//    public Integer applyEffect(Entity creator)
//    {
//        return creator.getStatistic(stat) * percent / 100;
//    }
//
//    /**
//     * the name of this, which will be percent
//     */
//    @Override
//    public Effect.Modifier getName()
//    {
//        return Effect.Modifier.PERCENT;
//    }
//
//}