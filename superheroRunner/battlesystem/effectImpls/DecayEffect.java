package battlesystem.effectImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.EffectModifier;
import battlesystem.Element;
import battlesystem.Entity;

/**
 * This decay effect is intended to start with one effect, then change soon after some number of turns pass.
 *
 */
public class DecayEffect extends Effect{
    private int count;
    private int decayRate;
    private int turnDecayStarts;

    /**
     * A basic constructor
     * 
     * @param basePower *NEW* The starting base rate this starts with.
     * @param decayRate *NEW* The amount of decay that occures.
     * @param turnDecayStarts *NEW* the turn the decay starts.
     * @param type *NEW* The type of statistic this will modify.
     * @param duration the length this effect lasts for
     * @param name the name of this effect
     * @param desc the description of this effect
     * @param element the element of this effect
     * @param modifiers what modifiers this effect has
     */
    public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        Effect.Type type, 
        int duration,  
        String name, 
        String desc,
        Element element,
        EffectModifier[] modifiers)
    {
        this(basePower, decayRate, turnDecayStarts, type, duration, name, desc, element, null, modifiers);
    }
    
    /**
     * A constructor for damaging decay effects
     * @param basePower the base power
     * @param decayRate what rate the attack may increase by
     * @param turnDecayStarts the turn the decay starts
     * @param type what type of statistic to modify
     * @param duration how long the effect lasts
     * @param name the name of the effect
     * @param desc the description of the effect
     * @param element the elemental attributes of this effect
     * @param pierces If this pierces defense and shield.
     * @param modifiers any extra modifiers
     */
    public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        Effect.Type type, 
        int duration,  
        String name, 
        String desc,
        Element element,
        boolean[] pierces,
        EffectModifier[] modifiers)
    {
        super(basePower, type, duration, true, name, desc, element, pierces, modifiers);
        this.decayRate = decayRate;
        this.count = 0;
        this.turnDecayStarts = turnDecayStarts;
    }

    /**
     * Use the effect and change based on if it is time to decay.
     * 
     * @param target the entity to affect
     * @param log Record any changes with the entity
     */
    @Override
    public void useEffect(
        Entity target,
        BattleLog log)
    {
        if (count < turnDecayStarts)
        {
            super.applyEffect(target, super.getStrength(target));
        }
        else 
        {
            super.applyEffect(target, -decayRate);
        }
        reduceDuration(target, log);
    }

    /**
     * Reduce the duration, but add to the count since it was applied.
     * @param target the target of the effect
     * @param log the log to record the removal of an effect
     */
    @Override 
    public void reduceDuration(
        Entity target,
        BattleLog log)
    {
        super.reduceDuration(target, log);
        count++;
    }

    /**
     * A basic copy method
     * @return the copied effect
     */
    @Override
    public Effect copy()
    {
        return new DecayEffect
                (
                    getStrength(), 
                    this.decayRate, 
                    this.turnDecayStarts,
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
     * return a version of the copied effect
     * @param additionalStrength what else to add to the base rate
     * @return the copied version
     */
    @Override
    public Effect copy(int additionalStrength) 
    {
        return new DecayEffect
                (
                    getStrength() + additionalStrength, 
                    this.decayRate, 
                    this.turnDecayStarts, 
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