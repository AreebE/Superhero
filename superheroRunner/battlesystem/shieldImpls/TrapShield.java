package battlesystem.shieldImpls;

import java.util.HashSet;

import org.json.JSONObject;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Elements;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.Shield;

/**
 * When this shield triggers, then it gives an effect to the caster.
 *
 */
public class TrapShield extends Shield
{
	private static final String COUNTER_KEY = "counter";
    private String counter;

    public TrapShield(JSONObject json)
    {
    	super(json);
    	counter = json.getString(COUNTER_KEY).toLowerCase();
    }
    /**
     * The basic constructor for the default shield
     * @param name the name of the shield 
     * @param desc the description of how it works
     * @param duration how long the shield lasts for
     * @param counter *NEW* the counter effect to use
     * @param nullifies if it nullifies future attacks
     * @param uses how many uses it has left
     * @param eventTriggers what events it triggers for
     * @param elementTriggers what elements it triggers for.
     */
    public TrapShield(
        String name,
        String desc,
        int duration, 
        String counter, 
        boolean nullifies,
        int uses,
        Shield.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }
    

    /**
     * The constructor for the copy method
     * 
     * @param name the name of the shield 
     * @param desc the description of how the shield works
     * @param duration the length of how long it lasts
     * @param counter the effect to apply to the caster
     * @param nullifies if it nullifies future attacks
     * @param uses the uses left
     * @param eventTriggers the events it triggers for
     * @param elementTriggers the elements it triggers for.
     */
    public TrapShield(
        String name,
        String desc,
        int duration, 
        String counter, 
        boolean nullifies,
        int uses,
        HashSet<Shield.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }


    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        Game g,
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), counter, null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
                // System.out.println("Apply " + isNullifies());
        caster.addEffect(g.getEffect(counter));
    }

    @Override
    public Shield copy()
    {
        return new TrapShield(getName(), getDesc(), getDuration(), counter, isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

    @Override 
    public JSONObject toJson()
    {
    	JSONObject json = super.toJson();
    	json.put(TYPE_KEY, ShieldLoader.TRAP);
    	json.put(COUNTER_KEY, "counter");
    	return json;
    }
}