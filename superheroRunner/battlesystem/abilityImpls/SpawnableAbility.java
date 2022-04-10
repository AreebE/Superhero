package battlesystem.abilityImpls;

import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.Game;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * Note: Currently broken
 *  
 * This is intended to create another entity that follows another one.
 */
public class SpawnableAbility extends DefenseAbility
{
    private String template;

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
        String template, 
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
        String template, 
        Element em, 
        ArrayList<AbilityModifier> modifiers) 
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
        Game g,
        BattleLog log) 
    {
        Entity ai = g.getSpawnable(template).create(caster, g);
        Object[] contents = new Object[]{target.getName(), ai.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SPAWN, contents));
        g.addMember(ai, target.getTeamID());
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
    
    public JSONObject toJson() {
		JSONObject start = new JSONObject();
		return null;
	}
}