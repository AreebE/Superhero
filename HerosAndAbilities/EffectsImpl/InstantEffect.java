package battlesystem;

// package Game.ablilites.Effects;

public class InstantEffect extends Effect 
{

    public InstantEffect(
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

    public InstantEffect(
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


    @Override
    public void reduceDuration(
        Entity target,
        StringBuilder actions) 
    {
                System.out.println("reduced duration");
        target.removeEffect(this);
    }


    @Override
    public Effect copy() {
        return new InstantEffect
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
        return new InstantEffect
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
}