package battlesystem;

public class DelayedEffect extends Effect 
{

    public DelayedEffect(
        int strength, 
        Effects.Type type, 
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
        Effects.Type type, 
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
    public void applyEffect(
        Entity target) 
    {
        reduceDuration(target);
    }


    @Override
    protected void removeEffect(
        Entity target) 
    {
        applyEffect(super.getType(), target);
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