package battlesystem.effectImpls;

public class DelayedEffect extends Effect 
{

    public DelayedEffect(
        int strength, 
        Effects.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
        Effects.Name enumName,
        EffectModifier[] modifiers) 
    {
        this
        (
            strength, 
            type, 
            timer, 
            name, 
            desc, 
            element, 
            enumName,
            null, 
            modifiers
        );
    }

    public DelayedEffect(
        int strength, 
        Effects.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
        Effects.Name enumName,
        boolean[] pierces,
        EffectModifier[] modifiers) 
    {
        super
        (
            strength, 
            type, 
            timer, 
            true, 
            name, 
            desc, 
            element,
            enumName, 
            pierces,
            modifiers
        );
    }

    @Override
    public void useEffect(
        Entity target,
        BattleLog log) 
    {
        reduceDuration(target, log);
    }


    @Override
    protected void removeEffect(
        Entity target,
        BattleLog log) 
    {
        applyEffect(target, log);
        target.removeEffect(this);
    }


    @Override
    public Effect copy() 
    {
        return new DelayedEffect
                    (
                        getStrength(), 
                        getType(), 
                        getDuration(), 
                        getName(), 
                        getDesc(), 
                        getElement(), 
                        getEnumName(),
                        getPierces(),
                        getModifiers()
                    );
    }

    @Override
    public Effect copy(int additionalStrength) 
    {
        return new DelayedEffect
                (
                    getStrength() + additionalStrength, 
                    this.getType(), 
                    this.getDuration(), 
                    getName(), 
                    getDesc(), 
                    getElement(),
                    getEnumName(),
                    getPierces(),
                    getModifiers()
                );
    }
}