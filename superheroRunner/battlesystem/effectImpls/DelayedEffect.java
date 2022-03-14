package battlesystem.effectImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.EffectModifier;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.databaseImpls.Effects;

public class DelayedEffect extends Effect 
{

    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
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
            null, 
            modifiers
        );
    }

    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
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
                    getPierces(),
                    getModifiers()
                );
    }
}