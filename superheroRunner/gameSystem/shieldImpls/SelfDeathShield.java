package gameSystem.shieldImpls;

import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Shield;

/**
 * A shield that triggers when the person is about to die, only it affects the victim.
 *
 */
public class SelfDeathShield extends DeathShield 
{

	public SelfDeathShield(JSONObject json)
	{
		super(json);
	}
	/**
	 * The basic constructor for this
	 * @param name the name of the shield
	 * @param desc the description of what this does
	 * @param duration how long it lasts
	 * @param effect the effect it will have
	 * @param nullifies if it nullifies future attacks
	 * @param uses how many uses are left.
	 */
    public SelfDeathShield(
        String name,
        String desc,
        int duration, 
        String effect,
        boolean nullifies,
        int uses)
    {
        super(name, desc, duration, effect, nullifies, uses);
    }

    /**
     * Get a copy of this shield.
     * @return the copy of the shield
     */
    @Override
    public Shield copy()
    {
        return new SelfDeathShield
        (
            getName(),
            getDesc(),
            getDuration(),
            getEffect(),
            isNullifies(),
            getUses()
        );
    }

    /**
     * Apply the effect to the victim.
     * 
     * @param victim the person about to die.
     * @param caster effectively null.
     * @param log the log to record the effects used.
     */
    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        Game g,
        BattleLog log)
    {
        Object[] contents = new Object[]{
            victim.getName(), 
            getUses() - 1, 
            victim.getName(), 
            getEffect(), 
            null,
            getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.applyEffect(g.getEffect(getEffect()), log);
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject shield = super.toJson();
    	shield.put(TYPE_KEY, ShieldLoader.SELF_DEATH);
    	return shield;
    }

  
}