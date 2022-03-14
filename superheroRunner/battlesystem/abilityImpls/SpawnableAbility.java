package battlesystem.abilityImpls;

import java.util.List;
import java.util.EnumMap;

import battlesystem.EntityInfoItem;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.BattleLog;
import battlesystem.Ability;
import battlesystem.AbilityModifier;

public class SpawnableAbility extends DefenseAbility
{
    private EntityInfoItem template;

    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown, 
        EntityInfoItem template, 
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
        this.template = template;
    }


    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown,
        EntityInfoItem template, 
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
        Entity ai = template.create(caster);
        Object[] contents = new Object[]{target.getName(), ai.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SPAWN, contents));
        allPlayers.add(ai);
        return;
    }

    @Override
    public Ability copy() 
    {
        return new SpawnableAbility
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