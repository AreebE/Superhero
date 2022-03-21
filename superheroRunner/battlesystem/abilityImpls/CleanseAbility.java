package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;

/**
 * This ability is intended to cleanse some effects, based on elemental attributes..
 */
public class CleanseAbility extends SupportAbility 
{
	/**
	 * This is a base constructor for the cleanse ability, though it's notably smaller than the others.
	 * 
	 * @param name the name of the ability
	 * @param desc how it works
	 * @param cooldown how many turns it is on cooldown for
	 * @param em *NEW* The element to use in order to 'purify' the person
	 * @param modifiers the modifiers it has
	 */
    public CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
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
    }


    /**
     * A constructor for the copy method
     * 
     * @param name the name of the ability 
     * @param desc the description of how it works
     * @param cooldown how long it is on cooldown for.
     * @param em the element used to purify the person
     * @param modifiers the modifiers it has
     */
    private CleanseAbility(
        String name, 
        String desc, 
        int cooldown, 
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
    }


    /**
     * Cast the ability by cleansing the target
     * 
     * @param target the person who recieves the cleansing
     * @param caster the person who has this ability
     * @param otherTargets other potential targets
     * @param allPlayers the other players.
     * @param log the battle log to record all effects removed.
     */
    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        target.removeEffects(getElement().getID(), log);
        return;
    }


    /**
     * The copy ability method
     * 
     * @return the copied ability.
     */
    @Override
    public Ability copy() 
    {
        return new CleanseAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getElement(),
                    getModifiers()
                );
    }
    
    public JSONObject toJson() {
		JSONObject start = new JSONObject();
		return null;
	}
}