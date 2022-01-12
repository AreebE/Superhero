package battlesystem;

public class PassiveEffect extends Effect 
{
    public PassiveEffect(
        int strength, 
        Effects.Type type, 
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
            0, 
            true, 
            name,
            desc, 
            element, 
            pierces,
            modifiers
        );
    }
    
    public PassiveEffect(
        int strength, 
        Effects.Type type, 
        String name, 
        String desc, 
        Element element,
        EffectModifier[] modifiers) 
    {
        this
        (
            strength, 
            type, 
            name,
            desc, 
            element, 
            null,
            modifiers
        );
    }


    @Override
    public void reduceDuration(
        Entity target,
        BattleLog log) 
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
                    getPierces(),
                    getModifiers()
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
                        getPierces(),
                        getModifiers()
                    );
        }

    @Override
    public boolean isRemovable() 
    {
        return false;
    }
}