package battlesystem.abilityImpls;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.Game;

/**
 * This ability is intended to give an effect to an entity
 *
 */
public class SupportAbility extends Ability 
{
    // Effect types
	private static final String EFFECT_KEY = "effect";
    private String template;

    public SupportAbility(JSONObject json)
    {
    	super(json);
    	super.setCategory(Ability.Category.SUPPORT);
    	template = json.getString(EFFECT_KEY);
    }

    /**
     * The basic constructor for a support ability
     * @param name the name of the ability
     * @param desc the description of the ability
     * @param cooldown the cooldown on the ability
     * @param template *NEW* the template so an effect can be copied.
     * @param em the elemental attributes
     * @param modifiers the ability modifiers
     */
    public SupportAbility(
        String name, 
        String desc, 
        int cooldown, 
        String template, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(
            name, 
            desc, 
            cooldown, 
            0, 
            Ability.Category.SUPPORT, 
            em,
            modifiers
        );
        this.template = template;
    }

    /**
     * The constructor for the copy method
     * @param name the name of the ability
     * @param desc the description of the effect
     * @param cooldown the cooldown for the ability
     * @param template the effect used to copy it
     * @param em the elemental attributes
     * @param modifiers the modifiers for the ability
     */
    public SupportAbility(
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
            0, 
            Ability.Category.SUPPORT, 
            em,
            modifiers
        );
        this.template = template;
    }


    /**
     * This applies an effect to the target
     * 
     * @param target the person to apply the effect to
     * @param caster the person who cast the ability
     * @param otherTargets anyone else to target
     * @param allPlayers all other players.
     * @param log meant to record the effect applied.
     */
    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
        target.addEffect(g.getEffect(template));
        Object[] contents = new Object[]{target.getName(), template};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.EFFECT_APPLIED, contents));
        return;
    }

    /**
     * a copy method to create a new ability
     * 
     * @return an exact copy of the support ability.
     */
    @Override
    public Ability copy() 
    {
        return new SupportAbility
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
		ability.put(TYPE_KEY, "support");
		ability.put(EFFECT_KEY, template);
		return ability;
	}
}


// Ideas: arena elemental affects, any sort of effect that causes a player to be
// at an advantage/disadvantage

// if attack Effect --> all attack plus 1
// if defense Effect --> all attacks minus 1