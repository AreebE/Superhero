package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class CleanseAbility extends SupportAbility 
{
    public CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Abilities.Name enumName, 
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
        Abilities.Name 
        enumName, 
        Element em,
        EnumMap<Abilities.Modifier, AbilityModifier> modifiers) 
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
    protected String castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        StringBuilder actions = new StringBuilder();
        target.removeEffects(getElement().getID(), actions);
        String action = actions.toString();
        if (action.equals(""))
        {
            return "No effects were cleansed.";
        }
        return action;
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