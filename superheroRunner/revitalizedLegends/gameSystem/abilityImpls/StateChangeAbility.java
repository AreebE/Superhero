package revitalizedLegends.gameSystem.abilityImpls;

import java.util.ArrayList;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.Ability;
import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Element;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Storage;
import revitalizedLegends.modifiers.abilityMods.AbilityModifier;
/**
 * An ability meant to change the state of an entity
 *
 */
public class StateChangeAbility extends SupportAbility
{
	private static final String STATE_KEY = "state";
    private String template;

    public StateChangeAbility(JSONObject json)
    {
    	super(json);
    	template = json.getString(STATE_KEY).toLowerCase();
    }
    /**
     * A basic constructor to change state 
     * 
     * @param name the name of the ability
     * @param desc the description of the ability
     * @param cooldown the cooldown of the ability
     * @param template *NEW* the state to copy.
     * @param em the elemental attributes
     * @param modifiers the modifiers
     */
    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        String template, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(name, desc, cooldown, null, em, modifiers);
        this.template = template;
    }

    /**
     * The constructor for the copy method
     * 
     * @param name the name of the ability
     * @param desc the description of the ability
     * @param cooldown the cooldown on the ability
     * @param template the template to copy the state from
     * @param em the elemental attributes
     * @param modifiers the modifiers of the ability
     */
    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        String template, 
        Element em,
        ArrayList<AbilityModifier> modifiers
        )
    {
        super(name, desc, cooldown, null, em, modifiers);
        this.template = template;
    }
    
    /**
     * In this case, this will apply the state to the new player
     * 
     * @param target the target who has their state changed
     * @param caster the caster of the ability
     * @param otherTargets the other targets
     * @param allPlayers the other players there
     * @param log to record the transform from the old state to the new state.
     */
    @Override
    protected void performCast(
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
        String oldStateName = target.getState().getName();
        Object[] contents = new Object[]{target.getName(), oldStateName, template};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.STATE_CHANGE, contents));
        target.replaceState(g.getState(template.toLowerCase()), caster, log, g);
        return; 
    }


    /**
     * A method for creating a copy of a state changing ability
     * @return a state change ability copy
     */
    @Override
    public Ability copy() 
    {
        return new StateChangeAbility
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
		JSONObject ability = super.toJson();
		System.out.println(ability);
		ability.put(TYPE_KEY, AbilityLoader.STATE);
		ability.put(STATE_KEY, template);
		return ability;
	}

    @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getState(template) != null;
    }
}