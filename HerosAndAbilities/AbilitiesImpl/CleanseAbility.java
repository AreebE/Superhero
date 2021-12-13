import java.util.EnumMap;

public class CleanseAbility extends SupportAbility 
{
    public CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
        AbilityList.Name enumName, 
        Element em,
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            null, 
            enumName, 
            em, 
            modifiers
        );
    }


    private CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
        AbilityList.Name 
        enumName, 
        Element em,
        EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            null, 
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
        target.removeEffects(getElement().getID());
    }


    @Override
    public Ability copy() 
    {
        return new CleanseAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getEnumName(), 
                    getElement(),
                    getModifiers()
                );
    }
}