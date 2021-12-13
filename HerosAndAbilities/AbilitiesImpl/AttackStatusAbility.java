import java.util.EnumMap;

public class AttackStatusAbility extends AttackAbility 
{
    private Effect sideEffect;

    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing, 
        Effect sideEffect,
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            enumName, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        Effect sideEffect,
        EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            enumName, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    @Override
    protected boolean castAbility(
        Superhero target, 
        Superhero caster) 
    {
        super.castAbility(target, caster);
        if (isPiercing() 
            || !caster.hasSheild()) 
        {
            target.addEffect(sideEffect.copy());
        }
        return true;
    }


    @Override
    public Ability copy() 
    {
        return new AttackStatusAbility
                    (   getName(), 
                        getDescription(), 
                        getCooldown(), 
                        getStrength(), 
                        getEnumName(),
                        getElement(), 
                        doesIgnoreBaseDefense(), 
                        isPiercing(), 
                        sideEffect.copy(),
                        getModifiers()
                    );
    }
}