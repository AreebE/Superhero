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
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        caster.searchForShield(Shields.Trigger.SUPPORT, Elements.getElement(Elements.Name.ALL), caster, target, log);
        target.removeEffects(getElement().getID(), log);
        return;
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