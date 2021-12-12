public class OneTimeEffect extends Effect 
{
    private boolean used;


    public OneTimeEffect(
        int strength, 
        EffectList.EffectType type, 
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
            applyEffect(getEffectType(), target);
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
            switch (getEffectType()) 
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
    public Effect copyEffect() 
    {
        return new OneTimeEffect
                    (
                        getStrength(), 
                        getEffectType(), 
                        getDuration(), 
                        getName(), 
                        getDesc(), 
                        getElement()
                    );
    }
}