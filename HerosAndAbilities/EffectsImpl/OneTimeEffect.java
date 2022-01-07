package battlesystem;

public class OneTimeEffect extends Effect 
{
    private boolean used;
    
    public OneTimeEffect(
        int strength, 
        Effects.Type type, 
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
        Effects.Type type, 
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
    public void applyEffect(
        Entity target) 
    {
        if (!used) 
        {
            applyEffect(getType(), target);
            used = true;
        }
        reduceDuration(target);
    }


    @Override
    public void removeEffect(
        Entity target) 
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