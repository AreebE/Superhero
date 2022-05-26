package battlesystem.abilityImpls;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.Shield;
import battlesystem.Storage;

/**
 * The defense ability used to produce a shield and give it to a player.
 *
 */
public class DefenseAbility extends Ability 
{
	private static final String SHIELD_KEY = "shield";
    private String shield;

    public DefenseAbility(JSONObject json)
    {
    	super(json);
    	shield = json.getString(SHIELD_KEY).toLowerCase();
    	super.setCategory(Ability.Category.DEFENSE);
    }
    /**
     * The basic constructor for the defense ability.
     * 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown how many turns it will be on cooldown for
     * @param shield *NEW* the shield that this will apply to the target
     * @param em the elemental attributes of the spell
     * @param modifiers the extra modifiers of this ability
     */
    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        String shield, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Ability.Category.DEFENSE,  
            em,
            modifiers
        );
        this.shield = shield;
    }


    /**
     * The constructor for the copy method
     * 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown the cooldown of the ability
     * @param shield the shield that will be given to the target
     * @param em the elemental attributes of the skill
     * @param modifiers the modifiers to add to this ability
     */
    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        String shield, 
        Element em, 
        ArrayList<AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            0, 
            Ability.Category.DEFENSE, 
            em,
            modifiers
        );
        this.shield = shield;
    }


    /**
     * The method to cast the ability. In this case, it will give the shield to the target.
     * 
     * @param target the target to give the shield to
     * @param caster the person who is using the ability.
     * @param otherTargets the other targets to attack
     * @param allPlayers the other players in the list
     * @param log the battlelog to record the shield given
     */
    @Override
    protected void performCast(
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
        Object[] contents = new Object[]{target.getName(), shield};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.DEFENSE, contents));
        target.addShield(g.getShield(shield), log, caster, g);
        return;
    }

    /**
     * Create a copy of the defense ability.
     * @return a copy of the defense ability.
     */
    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    shield,
                    getElement(),
                    getModifiers()
                );
    }

    public JSONObject toJson() {
		JSONObject ability = super.toJson();
		ability.put(TYPE_KEY, AbilityLoader.DEFENSE);
		ability.put(SHIELD_KEY, (shield == null)? "": shield);
		return ability;
	}

      @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getShield(shield) != null;
    }
}

// regeneration, heal, and shields/ resistance effects