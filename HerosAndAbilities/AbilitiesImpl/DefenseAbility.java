import java.util.EnumMap;

public class DefenseAbility extends Ability 
{
    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.AbilityNames enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            strength, 
            AbilityList.AbilityType.DEFENSE, 
            enumName, 
            em,
            modifiers
        );
    }


    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        int strength, 
        AbilityList.AbilityNames enumName,
        Element em, 
        EnumMap<AbilityList.AbilityModifierNames, 
        AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            AbilityList.AbilityType.DEFENSE, 
            enumName, 
            em, 
            modifiers
        );
    }


    @Override
    protected void castAbility(
        Superhero target, 
        Superhero caster) 
    {
        target.addSheildHealth(getStrength());
    }

    @Override
    public Ability copyAbility() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getStrength(), 
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}

// regeneration, heal, and sheilds/ resistance effects