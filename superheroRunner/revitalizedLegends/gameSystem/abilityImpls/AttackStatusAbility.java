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
 * This extension of attack ability is going to add an effect to the target.
 */
public class AttackStatusAbility extends AttackAbility 
{
	private static final String SIDE_EFFECT_KEY = "side effect";
    private static final String ON_USER_KEY = "on user";
    private String sideEffect;
    private boolean onUser;
    public AttackStatusAbility(JSONObject json)
    {
    	super(json);
    	sideEffect = json.getString(SIDE_EFFECT_KEY).toLowerCase();
        onUser = json.getBoolean(ON_USER_KEY);
    }
    /**
     * One key difference, compared to the other one:
     *  
     * @param name name of the ability
     * @param desc the description of how the ability works
     * @param cooldown the cooldown
     * @param strength the strength
     * @param em the element
     * @param ignoresBaseDefense if it ignores defense
     * @param isPiercing if it ignores shield
     * @param sideEffect *NEW* The effect this will be the one applied to the target
     * @param modifiers any modifiers to give
     */
    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing, 
        String sideEffect,
        boolean onUser,
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
        this.onUser = onUser;
    }


    /**
     * The constructor for the copy method.
     * 
     * @param name the name of the ability
     * @param desc the description of the ability
     * @param cooldown the cooldown
     * @param strength the base strength
     * @param em the element
     * @param ignoresBaseDefense if it ignores defense
     * @param isPiercing if it ignores shield
     * @param sideEffect the side effect
     * @param modifiers the modifiers for the ability.
     */
    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        String sideEffect,
        boolean onUser,
        ArrayList<AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
        this.onUser = onUser;
    }


    /**
     * It calls the super.castAbility, then applies the status effect if the ability either pierces or the target 
     * doesn't have a shield.
     * 
     * @param target the target for the ability
     * @param caster the caster
     * @param otherTargets other targets that may be important
     * @param allPlayers all other players.
     */
    @Override
    protected void performCast(
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
        super.performCast(target, caster, g, log);
        Object[] contents = new Object[]{target.getName(), sideEffect};        
        if (onUser)
        {
            contents[0] = caster.getName();
            caster.addEffect(caster, g, log, g.getEffect(sideEffect));        
        }
        else 
        {
            target.addEffect(caster, g, log, g.getEffect(sideEffect));        
        }
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ATTACK_STATUS, contents));
        return;
    }

    /**
     * The copy method to create a copy of an attackStatusAbility
     * 
     * @return the copied ability.
     */
    @Override
    public Ability copy() 
    {
        return new AttackStatusAbility
                    (   getName(), 
                        getDescription(), 
                        getCooldown(), 
                        getStrength(), 
                        getElement(), 
                        doesIgnoreBaseDefense(), 
                        isPiercing(), 
                        sideEffect,
                        onUser,
                        getModifiers()
                    );
    }
    
    public JSONObject toJson() {
		JSONObject ability = super.toJson();
		ability.put(TYPE_KEY, AbilityLoader.ATTACK_STATUS);
		ability.put(SIDE_EFFECT_KEY, sideEffect);
		ability.put(ON_USER_KEY, onUser);
        return ability;
	}

      @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getEffect(sideEffect) != null;
    }
}