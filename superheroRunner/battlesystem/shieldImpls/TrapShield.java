package battlesystem.shieldImpls;

import java.util.HashSet;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.Shield;
import battlesystem.databaseImpls.Elements;

/**
 * When this shield triggers, then it gives an effect to the caster.
 *
 */
public class TrapShield extends Shield
{
    private Effect counter;

    
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
        Effect counter, 
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
        Effect counter, 
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
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), counter.getName(), null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
                // System.out.println("Apply " + isNullifies());
        caster.addEffect(counter.copy());
    }

    @Override
    public Shield copy()
    {
        return new TrapShield(getName(), getDesc(), getDuration(), counter.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}