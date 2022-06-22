package gameSystem.shieldImpls;

import java.util.HashSet;

import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.Elements;
import gameSystem.Entity;
import gameSystem.Game;
import gameSystem.Shield;
import gameSystem.Storage;

/**
 * This shield will affect the the person who has this shield
 *
 */
public class SelfShield extends Shield
{
	private static final String SELF_APPLY_KEY = "self apply";
    private String selfApply;

    public SelfShield(JSONObject json)
    {
    	super(json);
    	selfApply = json.getString(SELF_APPLY_KEY).toLowerCase();
    }
    /**
     * The basic constructor of this shield
     * 
     * @param name the name of the shield
     * @param desc the description of how it works
     * @param duration the duration of how long this lasts
     * @param selfApply *NEW* the effect to apply to the victim.
     * @param nullifies if it nullifies future attacks
     * @param uses the uses left
     * @param eventTriggers what events it triggers for
     * @param elementTriggers what elements it triggers for.
     */
    public SelfShield(
        String name,
        String desc,
        int duration, 
        String selfApply, 
        boolean nullifies,
        int uses,
        Shield.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }
    

    /** 
     * The constructor for the copy method
     * @param name the name of this ability
     * @param desc the description of how it works
     * @param duration the duration of how long it lasts
     * @param selfApply The effect to apply to the victim
     * @param nullifies if it nullifies future effects
     * @param uses how many uses it has left
     * @param eventTriggers what events it triggers for
     * @param elementTriggers what elements it triggers for.
     */
    public SelfShield(
        String name,
        String desc,
        int duration, 
        String selfApply, 
        boolean nullifies,
        int uses,
        HashSet<Shield.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }


    /**
     * This will give an effect to the victim.
     * @param victim the person who receives the effect
     * @param caster the person who caused the event
     * @param log records the shield used and the effect given.
     */
    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster, 
        Game g,
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, victim.getName(), selfApply, null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.addStartingEffect(g.getEffect(selfApply));
    }

    @Override
    public Shield copy()
    {
        return new SelfShield(getName(), getDesc(), getDuration(), selfApply, isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

    @Override
    public JSONObject toJson()
    {
    	JSONObject json = super.toJson();
    	json.put(TYPE_KEY, ShieldLoader.SELF);
    	json.put(SELF_APPLY_KEY, selfApply);
    	return json;
    }

    @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getEffect(selfApply) != null;
    }
}