package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class StateChangeAbility extends SupportAbility
{

    private State template;

    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        State template, 
        Abilities.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(name, desc, cooldown, null, enumName, em, modifiers);
        this.template = template;
    }

    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        State template, 
        Abilities.Name enumName,
        Element em,
        EnumMap<Abilities.Modifier, AbilityModifier> modifiers) 
    {
        super(name, desc, cooldown, null, enumName, em, modifiers);
        this.template = template;
    }
    
    @Override
    protected boolean castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        target.replaceState(template.copy());
        return true;
    }


    @Override
    public Ability copy() 
    {
        return new StateChangeAbility
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