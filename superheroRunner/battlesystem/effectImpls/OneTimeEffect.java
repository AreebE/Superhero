package battlesystem.effectImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.EffectModifier;
import battlesystem.Element;
import battlesystem.Entity;

/**
 * An effect intended to only apply its buff once.
 *
 */
public class OneTimeEffect extends Effect 
{
    private boolean used;
    
    /**
     * Create a basic version
     * 
     * @param strength the base strength
     * @param type the type of effect
     * @param duration the lenth it lasts for
     * @param name the name of the effect
     * @param desc the description of what it does
     * @param element what element it belongs to
     * @param pierces if it pierces the shield
     * @param modifiers the modifiers it has
     */
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

    /**
     * An overloaded version not meant for damaging effects
     * 
     * @param strength the base strength of this effect
     * @param type the type of effect
     * @param duration the length this effect lasts
     * @param name the name of this effect
     * @param desc the description of how this effect works
     */
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

    /**
     * Only apply the effect if it hasn't been used
     * @param target the target who has the effect
     * @param log the battle log used to contain what the effect did
     */
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

    /**
     * Remove the effect, depending on if it was permanent or not.
     * @param target the person with the effect
     * @param log to record the removed effect
     */
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


    /**
     * A basic version of the copy
     * @return a new effect of this type
     */
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

    /**
     * An upgraded version of the copy
     * @param additionalStrength the amount of strength to add
     * @return the copied effect
     */
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