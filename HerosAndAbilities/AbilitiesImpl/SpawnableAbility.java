package battlesystem;

import java.util.List;
import java.util.EnumMap;

public class SpawnableAbility extends DefenseAbility
{
    private Spawnables.Name spawnName;

    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown, 
        Spawnables.Name spawnName, 
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
        this.spawnName = spawnName;
    }


    public SpawnableAbility(
        String name, 
        String desc, 
        int cooldown,
        Spawnables.Name spawnName, 
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
        this.spawnName = spawnName;
    }
    

    @Override
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        Entity ai = Spawnables.get(spawnName).create(target);
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
                    spawnName,
                    getElement()
                );
    }
}