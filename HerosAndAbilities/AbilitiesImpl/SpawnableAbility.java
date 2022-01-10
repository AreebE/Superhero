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
    protected String castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        Entity ai = info.create(target);
        allPlayers.add(ai);
        return new StringBuilder(ai.getName())
                .append(" was created. Their leader is ")
                .append(target.getName())
                .toString();
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