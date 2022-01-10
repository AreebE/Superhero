package battlesystem;

public class DecayEffect extends Effect{
    private int count;
    private int decayRate;
    private int turnDecayStarts;

    public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        Effects.Type type, 
        int duration,  
        String name, 
        String desc,
        Element element,
        EffectModifier[] modifiers)
    {
        this(basePower, decayRate, turnDecayStarts, type, duration, name, desc, element, null, modifiers);
    }
    
    public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        Effects.Type type, 
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

    @Override
    public void applyEffect(
        Effects.Type type, 
        Entity target)
    {
        if (count < turnDecayStarts)
        {
            super.applyEffect(type, target, super.getStrength(target));
        }
        else 
        {
            super.applyEffect(type, target, -decayRate);
        }
    }

    @Override 
    public void reduceDuration(
        Entity target)
    {
        super.reduceDuration(target);
        count++;
    }

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