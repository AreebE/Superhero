package battlesystem.abilityImpls;

import java.util.List;
import java.util.EnumMap;

import battlesystem.EntityInfoItem;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.BattleLog;
import battlesystem.Ability;
import battlesystem.AbilityModifier;

/**
 * Note: Currently broken
 *  
 * This is intended to create another entity that follows another one.
 */
public class SpawnableAbility extends DefenseAbility
{
    private EntityInfoItem template;

    /**
     * The basic constructor 
     * 
     * @param name the name of this ability
     * @param desc the description, mostly of the entity summoned
     * @param cooldown the cooldown of this ability
     * @param template *NEW* the info item used to create an entity
     * @param em the element of this ability
     * @param modifiers the other modifiers of this ability.
     */
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

    /**
     * The constructor for the copy method 
     *  
     * @param name the name of the ability
     * @param desc the description of it
     * @param cooldown how many turns it is on cooldown
     * @param template the info item to create an entity
     * @param em the elemental attributes
     * @param modifiers the modifiers to add
     */
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
    

    /**
     * This is going to cast the ability, which will spawn an entity
     * 
     * @param target the person who is considered the creator of the entity
     * @param caster the person who casted the ability
     * @param otherTargets any other targets present.
     * @param allPlayers the other players here.
     * @param log the battlelog to record the new person spawned.
     */
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

    /**
     * Return a spawnable ability copy.
     * 
     * @return a copt of a spawnable ability
     */
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