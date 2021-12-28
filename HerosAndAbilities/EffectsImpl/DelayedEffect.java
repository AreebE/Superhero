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
        boolean[] pierces) 
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
            pierces
        );
    }

    public DelayedEffect(
        int strength, 
        Effects.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element) 
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
            null
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
                        getPierces()
                    );
    }
}