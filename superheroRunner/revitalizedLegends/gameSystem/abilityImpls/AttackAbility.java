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
 * This ability is meant to execute a basic attack.
 *
 */
public class AttackAbility extends Ability 
{
	private static final String PIERCING_KEY = "pierces";
	private static final String DEFENSE_KEY = "ignores defense";
    private boolean isPiercing;
    private boolean ignoresBaseDefense;
    private Element em;

    public AttackAbility(JSONObject json)
    {
    	super(json);
    	super.setCategory(Ability.Category.ATTACK);
    	isPiercing = json.getBoolean(DEFENSE_KEY);
    	ignoresBaseDefense = json.getBoolean(PIERCING_KEY);
    	em = super.getElement();
    }
    /**
     * A basic constructor that calls its super class
     * @param name the name of the ability
     * @param desc its description
     * @param cooldown how much cooldown it has
     * @param strength base strength 
     * @param em the element
     * @param ignoresBaseDefense *NEW* It allows the attack to ignore base defense
     * @param isPiercing *NEW* It allows the attack to ignore shield
     * @param modifiers Modifies the ability itself.
     */
    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            Ability.Category.ATTACK, 
            em,
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }

    /**
     * Another instructor for copying abilities
     * @param parent its parent
     * @param info the information it has?
     */
    public AttackAbility(Ability parent,String info){
      super(parent);
      System.out.println("INFO IS "+info+" IN ATTACKAB");
    }
    
    /**
     * Another constructor for the copy method
     * @param name the name of the ability
     * @param desc the description
     * @param cooldown the cooldown
     * @param strength the base strength
     * @param em the element
     * @param ignoresBaseDefense if it ignores defense
     * @param isPiercing if it ignores shield
     * @param modifiers the modifiers it has
     */
    public AttackAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        ArrayList<AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            Ability.Category.ATTACK, 
            em,
            modifiers
        );
        this.isPiercing = isPiercing;
        this.ignoresBaseDefense = ignoresBaseDefense;
        this.em = em;
    }


    /**
     * Do an attack with this method, updating strength
     * 
     * @param target the target to attack.
     * @param caster the caster of the spell.
     * @param otherTargets what other targets there are to attack.
     * @param allPlayers the other players present in this battle
     * @param log the battlelog to record the attack.
     */
    @Override
	protected void performCast(
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
//         System.out.println("Attack Ability \'" + this.getName() + "\' used on player
//         " + target.getName());
        int currentIndex = log.getCurrentIndex(); 

        int attackStrength = getStrength() + caster.getBaseAttack() + this.getAdditionalStrength();
        if (attackStrength < 0) 
        {
            attackStrength = 0;
        }

//        Terrain t = caster.getTerrain();
//
//        if(t.isTerrainBuffed(this.em)){
//          attackStrength += attackStrength;
//        }
       
        Object[] results = target.dealDamage
        (
            attackStrength, 
            isPiercing, 
            ignoresBaseDefense,
            caster,
            getElement(),
            g,
            log
        );
//        System.out.println(results[4] + "EEEEEE");
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ATTACK, results), currentIndex);
        if ((Boolean) results[5])
        {
            super.stopAttack();
        }
        caster.hasAttacked(log, g);
        return;
    }

    /**
     * The copy ability to produce another attack ability.
     * 
     * @return the copied ability.
     */
    @Override
    public Ability copy() 
    {
        // System.out.println(getName() + ": " + getModifiers().toString());
        return new AttackAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    getStrength(), 
                    getElement(),
                    ignoresBaseDefense, 
                    isPiercing,
                    getModifiers()
                );
    }


    /**
     * The toString method that prints what the ability would look like.
     */
    @Override
    public String toString() 
    {
        StringBuilder sBuilder = new StringBuilder(super.toString());
        if (ignoresBaseDefense) 
        {
            sBuilder.append(" (It ignores the base defense) ");
        }
        if (isPiercing) 
        {
            sBuilder.append(" (It pierces the shield) ");
        }
        return sBuilder.toString();
    }

    
    /**
     * If this ignores the defense
     * @return if it ignores defense
     */
    protected boolean doesIgnoreBaseDefense() 
    {
        return ignoresBaseDefense;
    }


    /**
     * Does it pierces shield?
     * @return if it ignores shield?
     */
    protected boolean isPiercing() 
    {
        return isPiercing;
    }
    
    @Override
    public JSONObject toJson() {
		JSONObject ability = super.toJson();
		ability.put(TYPE_KEY, AbilityLoader.ATTACK);
		ability.put(DEFENSE_KEY, ignoresBaseDefense);
		ability.put(PIERCING_KEY, isPiercing);
		return ability;
	}

      @Override
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
}
//