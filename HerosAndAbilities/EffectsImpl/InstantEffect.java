// package Game.ablilites.Effects;

public class InstantEffect extends Effect 
{

    public InstantEffect(
        int strength, 
        EffectList.Type type, 
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

     public InstantEffect(
        int strength, 
        EffectList.Type type, 
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
                        getPierces()
                    );
    }
}