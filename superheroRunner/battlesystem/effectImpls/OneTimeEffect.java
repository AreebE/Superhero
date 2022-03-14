package battlesystem.effectImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.EffectModifier;
import battlesystem.Element;
import battlesystem.Entity;

public class OneTimeEffect extends Effect 
{
    private boolean used;
    
    public OneTimeEffect(
        int strength, 
        Effect.Type type, 
        int duration, 
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
            duration,
            false, 
            name, 
            desc, 
            element, 
            pierces,
            modifiers
        );
    }

    public OneTimeEffect(
        int strength, 
        Effect.Type type, 
        int duration, 
        String name, 
        String desc,
        Element element,
        EffectModifier[] modifiers) 
    {
        this
        (
            strength, 
            type, 
            duration,
            name, 
            desc, 
            element, 
            null,
            modifiers
        );
    }

    @Override
    public void useEffect(
        Entity target,
        BattleLog log) 
    {
        if (!used) 
        {
            applyEffect(target, log);
            used = true;
        }
        reduceDuration(target, log);
    }


    @Override
    public void removeEffect(
        Entity target,
        BattleLog log) 
    {
        if (!isPermanent()) 
        {
            switch (getType()) 
            {
                case ATTACK:
                    target.addAttack(-getStrength());
                    break;
                case DEFENSE:
                    target.addDefense(-getStrength());
                    break;
                case SPEED:
                    target.addSpeed(-getStrength());
            }
        }
        target.removeEffect(this);
    }


    @Override
    public Effect copy() 
    {
        return new OneTimeEffect
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
    public Effect copy(int additionalStrength) {
        return new OneTimeEffect
                    (
                        getStrength() + additionalStrength, 
                        getType(),
                        getDuration(),
                        getName(), 
                        getDesc(), 
                        getElement(),
                        getPierces(),
                        getModifiers()
                    );
    }
}