import java.util.EnumMap;

public class DefenseAbility extends Ability 
{
    private Shield shield;

    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Shield shield, 
        AbilityList.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            AbilityList.Type.DEFENSE, 
            enumName, 
            em,
            modifiers
        );
        this.shield = shield;
    }


    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        Shield shield, 
        AbilityList.Name enumName,
        Element em, 
        EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            0, 
            AbilityList.Type.DEFENSE, 
            enumName, 
            em, 
            modifiers
        );
        this.shield = shield;
    }


    @Override
    protected boolean castAbility(
        Superhero target, 
        Superhero caster) 
    {
        target.addShield(shield.copy());
        return true;
    }

    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    shield.copy(),
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}

// regeneration, heal, and shields/ resistance effects