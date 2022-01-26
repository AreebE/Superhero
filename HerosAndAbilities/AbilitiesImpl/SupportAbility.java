package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class SupportAbility extends Ability 
{
    // Effect types
    private Effect template;


    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        Effect template, 
        Abilities.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(
            name, 
            desc, 
            cooldown, 
            0, 
            Abilities.Type.SUPPORT, 
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
        Abilities.Name enumName,
        Element em,
        EnumMap<Abilities.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Abilities.Type.SUPPORT, 
            enumName, 
            em, 
            modifiers
        );
        this.template = template;
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