package battlesystem;

import java.util.List;
import java.util.EnumMap;

public class SpawnableAbility extends DefenseAbility
{
    AIInfoItem info;

    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown, 
        AIInfoItem info, 
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
        this.info = info;
    }


    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown,
        AIInfoItem info, 
        Abilities.Name enumName,
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
        this.info = info;
    }
    

    @Override
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        Entity ai = info.create(target);
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
                    info,
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}