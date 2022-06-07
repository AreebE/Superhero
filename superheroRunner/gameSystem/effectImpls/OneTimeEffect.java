package gameSystem.effectImpls;

import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.Element;
import gameSystem.Entity;

/**
 * An effect intended to only apply its buff once.
 *
 */
public class OneTimeEffect extends Effect 
{
    private boolean used;
    
    public OneTimeEffect(JSONObject json)
    {
    	super(json);
    }
    /**
     * Create a basic version
     * 
     * @param strength the base strength
     * @param type the type of effect
     * @param duration the length it lasts for
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
        String stack,
        boolean[] pierces) 
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
            stack,
            pierces
        );
    }

    public OneTimeEffect(
        int strength, 
        Effect.Type type, 
        int duration, 
        String name, 
        String desc,
        Element element,
        boolean[] pierces) 
    {
        this
        (
            strength, 
            type, 
            duration,
            false, 
            name, 
            desc, 
            element, 
            null,
            pierces
        );
    }

    public OneTimeEffect(
        int strength, 
        Effect.Type type, 
        int duration, 
        String name, 
        String desc,
        Element element,
        String stack) 
    {
        this
        (
            strength, 
            type, 
            duration,
            false, 
            name, 
            desc, 
            element, 
            stack,
            null
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
        Element element) 
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
            null
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
                        getStack(),
                        getPierces()
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
                        getStack(),
                        getPierces()
                    );
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject effect = super.toJson();
    	effect.put(TYPE_KEY, EffectLoader.ONE_TIME);
    	return effect;
    }
}