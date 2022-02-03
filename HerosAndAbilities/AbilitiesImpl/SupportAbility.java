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
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(
            name, 
            desc, 
            cooldown, 
            0, 
            Abilities.Type.SUPPORT, 
            em
        );
        this.template = template;
    }


    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        Effect template, 
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
            em
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
                    getElement()
                );
    }
    
}

// Ideas: arena elemental affects, any sort of effect that causes a player to be
// at an advantage/disadvantage

// if attack Effect --> all attack plus 1
// if defense Effect --> all attacks minus 1