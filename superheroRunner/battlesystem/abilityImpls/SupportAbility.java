package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Element;
import battlesystem.Entity;

public class SupportAbility extends Ability 
{
    // Effect types
    private Effect template;


    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        Effect template, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(
            name, 
            desc, 
            cooldown, 
            0, 
            Ability.Type.SUPPORT, 
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
        Element em,
        EnumMap<Ability.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Ability.Type.SUPPORT, 
            em,
            modifiers
        );
        this.template = template;
    }


    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        target.addEffect(template.copy());
        Object[] contents = new Object[]{target.getName(), template.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.EFFECT_APPLIED, contents));
        return;
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
                    getElement(),
                    getModifiers()
                );
    }
}


// Ideas: arena elemental affects, any sort of effect that causes a player to be
// at an advantage/disadvantage

// if attack Effect --> all attack plus 1
// if defense Effect --> all attacks minus 1