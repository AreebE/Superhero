package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;

public class CleanseAbility extends SupportAbility 
{
    public CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Element em,
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            null, 
            em, 
            modifiers
        );
    }


    private CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Element em,
        EnumMap<Ability.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            null, 
            em, 
            modifiers
        );
    }


    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
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
                    getElement(),
                    getModifiers()
                );
    }
}