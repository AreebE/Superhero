public class OneTimeEffect extends Effect 
{
    private boolean used;


    public OneTimeEffect(
        int strength, 
        EffectList.Type type, 
        int duration, 
        String name, 
        String desc,
        Element element,
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
            pierces
        );
    }

    public OneTimeEffect(
        int strength, 
        EffectList.Type type, 
        int duration, 
        String name, 
        String desc,
        Element element) 
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
            null
        );
    }

    @Override
    public void applyEffect(
        Superhero target) 
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
        Superhero target) 
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
                        getElement()
                    );
    }
}