public class PassiveEffect extends Effect 
{


    public PassiveEffect(
        int strength, 
        EffectList.EffectType type, 
        String name, 
        String desc, 
        Element element,
        boolean... pierces) 
    {
        super
        (
            strength, 
            type, 
            0, 
            true, 
            name,
            desc, 
            element, 
            pierces
        );
    }


    @Override
    public void reduceDuration(
        Superhero target) 
    {
    }


    @Override
    public Effect copyEffect() 
    {
        return new PassiveEffect
                (
                    getStrength(), 
                    getEffectType(), 
                    getName(), 
                    getDesc(), 
                    getElement(), 
                    getPierces()
                );
    }


    @Override
    public boolean isRemovable() 
    {
        return false;
    }
}