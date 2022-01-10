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
    public void useEffect(
        Entity target,
        StringBuilder actions) 
    {
        if (!used) 
        {
            applyEffect(target, actions);
            used = true;
        }
        reduceDuration(target, actions);
    }


    @Override
    public void removeEffect(
        Entity target,
        StringBuilder actions) 
    {
        if (!isPermanent()) 
        {
            actions.append("The effect was also removed.");
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