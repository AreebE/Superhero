package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.Shield;

public class DefenseAbility extends Ability 
{
    private Shield shield;

    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Shield shield, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Ability.Type.DEFENSE,  
            em,
            modifiers
        );
        this.shield = shield;
    }


    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        Shield shield, 
        Element em, 
        EnumMap<Ability.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            0, 
            Ability.Type.DEFENSE, 
            em,
            modifiers
        );
        this.shield = shield;
    }


    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        target.addShield(shield.copy());
        Object[] contents = new Object[]{target.getName(), shield.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.DEFENSE, contents));
        return;
    }

    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    shield.copy(),
                    getElement(),
                    getModifiers()
                );
    }


}

// regeneration, heal, and shields/ resistance effects