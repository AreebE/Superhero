import java.util.EnumMap;

public class SupportAbility extends Ability 
{
    // Effect types
    private Effect template;


    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        Effect template, 
        AbilityList.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(
            name, 
            desc, 
            cooldown, 
            0, 
            AbilityList.Type.SUPPORT, 
            enumName, 
            em, 
            modifiers
        );
        this.template = template;
    }


    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        Effect template, 
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
            AbilityList.Type.SUPPORT, 
            enumName, 
            em, 
            modifiers
        );
        this.template = template;
    }


    @Override
    protected void castAbility(
        Superhero target, 
        Superhero caster) 
    {
        target.addEffect(template.copy());
    }


    @Override
    public Ability copy() 
    {
        return new SupportAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    template, 
                    getEnumName(), 
                    getElement(),
                    getModifiers()
                );
    }
    
}

// Ideas: arena elemental affects, any sort of effect that causes a player to be
// at an advantage/disadvantage

// if attack Effect --> all attack plus 1
// if defense Effect --> all attacks minus 1