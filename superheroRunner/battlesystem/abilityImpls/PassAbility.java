package battlesystem.abilityImpls;

import java.util.List;

import org.json.JSONObject;

import battlesystem.Ability;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.Game;

/**
 * A pass ability intended to pass turns. For the sake of convenience, it is recorded as an attack skill.
 *
 */
public class PassAbility extends Ability 
{

	/**
	 * A basic constructor
	 * @param name the name of the ability
	 * @param desc the description
	 */
    public PassAbility
    (
        String name, 
        String desc
    ) 
    {
        super(name, desc, 0, 0, Ability.Category.ATTACK,null);
    }

    /**
     * For all effects and purposes, this method won't be called. None of the parameters matter too much.
     */
    @Override
	public void castAbility
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
        return;
    }  
    
    /**
     * A simple copy method to copy the pass ability.
     */
    @Override
    public Ability copy()
    {
        return new PassAbility(getName(), getDescription());
    } 
    
    public JSONObject toJson() {
		JSONObject start = new JSONObject();
		return null;
	}
}