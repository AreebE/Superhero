import java.util.EnumMap;

public class DefenseAbility extends Ability 
{
    private Sheild sheild;

    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Sheild sheild, 
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
    }


    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        Sheild sheild, 
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
        this.sheild = sheild;
    }


    @Override
    protected void castAbility(
        Superhero target, 
        Superhero caster) 
    {
        // target.addSheild(sheild);
    }

    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    null,
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}

// regeneration, heal, and sheilds/ resistance effects