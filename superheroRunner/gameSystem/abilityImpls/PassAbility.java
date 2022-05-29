package gameSystem.abilityImpls;

import java.util.List;

import org.json.JSONObject;

import gameSystem.Ability;
import gameSystem.BattleLog;
import gameSystem.Element;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Storage;

/**
 * A pass ability intended to pass turns. For the sake of convenience, it is recorded as an attack skill.
 *
 */
public class PassAbility extends Ability 
{
	
	public PassAbility(JSONObject json)
	{
		super(json);
		super.setCategory(Ability.Category.ATTACK);
	}

	/**
	 * A basic constructor
	 * @param name the name of the ability
	 * @param desc the description
	 */
    public PassAbility
    (
        String name, 
        String desc,
        Element em
    ) 
    {
        super(name, desc, 0, 0, Ability.Category.ATTACK, em);
    }

    /**
     * For all effects and purposes, this method won't be called. None of the parameters matter too much.
     */
    @Override
    protected void performCast
    (
        Entity target, 
        Entity caster,
        Game g,
		BattleLog battleLog
    )
    {
        
    }

    /**
     * This method is called just to record the fact the target used an ability
     * 
     * @param target Should be the caster
     * @param caster the caster who is passing
     * @param otherTargets the other targets here
     * @param allPlayers can be considered null
     * @param log the log to record this pass action.
     */
    @Override
    public void useAbility
    (
        List<Entity> targets, 
        Entity caster,
        Game g,
        BattleLog log
    )
    {
        Object[] contents = new Object[]{caster.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.PASS, contents));
        caster.pass(log, g);
        return;
    }  
    
    /**
     * A simple copy method to copy the pass ability.
     */
    @Override
    public Ability copy()
    {
        return new PassAbility(getName(), getDescription(), getElement());
    } 
    
    public JSONObject toJson() {
		JSONObject ability = super.toJson();
		ability.put(TYPE_KEY, AbilityLoader.PASS);
		return ability;
	}

      @Override
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
}