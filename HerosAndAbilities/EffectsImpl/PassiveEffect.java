package battlesystem;

public class PassiveEffect extends Effect 
{
    public PassiveEffect(
        int strength, 
        Effects.Type type, 
        String name, 
        String desc, 
        Element element,
        boolean[] pierces) 
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

    public PassiveEffect(
        int strength, 
        Effects.Type type, 
        String name, 
        String desc, 
        Element element) 
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
            null
        );
    }


    @Override
    public void reduceDuration(
        Entity target) 
    {
    }


    @Override
    public Effect copy() 
    {
        return new PassiveEffect
                (
                    getStrength(),
                    getType(), 
                    getName(), 
                    getDesc(), 
                    getElement(), 
                    getPierces()
                );
    }

    @Override
    public Effect copy(int additionalStrength) {
        return new PassiveEffect
                    (
                        getStrength() + additionalStrength, 
                        getType(),
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