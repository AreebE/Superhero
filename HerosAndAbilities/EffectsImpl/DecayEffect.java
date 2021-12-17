public class DecayEffect extends Effect{
    private int count;
    private int decayRate;
    private int turnDecayStarts;

    public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        EffectList.Type type, 
        int duration,  
        String name, 
        String desc,
        Element element)
    {
        super(basePower, type, duration, true, name, desc, element);
        this.decayRate = decayRate;
        this.count = 0;
        this.turnDecayStarts = turnDecayStarts;
    }
    
    @Override
    public void applyEffect(
        EffectList.Type type, 
        Superhero target)
    {
        if (count < turnDecayStarts)
        {
            super.applyEffect(type, target, super.getStrength());
        }
        else 
        {
            super.applyEffect(type, target, -decayRate);
        }
    }

    @Override 
    public void reduceDuration(
        Superhero target)
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
                    getElement()
                );
    }
}