package battlesystem.effectImpls;

// package Game.ablilites.Effects;

public class InstantEffect extends Effect 
{

    public InstantEffect(
        int strength, 
        Effects.Type type, 
        String name, 
        String desc, 
        Element element,
        Effects.Name enumName,
        EffectModifier[] modifiers) 
    {
        this
        (
            strength, 
            type, 
            name, 
            desc, 
            element,
            enumName,
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
        Effects.Name enumName,
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
            enumName,
            pierces,
            modifiers
        );
    }


    @Override
    public void reduceDuration(
        Entity target,
        BattleLog log) 
    {
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
                        getEnumName(),
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
                        getEnumName(),
                        getPierces(),
                        getModifiers()
                    );
    }
}