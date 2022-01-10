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
    protected String castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        String oldStateName = target.getState().getName();
        target.replaceState(template.copy());
        return new StringBuilder(target.getName())
                .append("\'s state changed from ")
                .append(oldStateName)
                .append(" to ")
                .append(template.getName())
                .toString();
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