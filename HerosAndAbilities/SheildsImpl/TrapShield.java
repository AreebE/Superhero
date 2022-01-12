package battlesystem;

import java.util.HashSet;

public class TrapShield extends Shield
{
    private Effect counter;


    public TrapShield(
        String name,
        String desc,
        int duration, 
        Effect counter, 
        boolean nullifies,
        int uses,
        Shields.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }
    

    public TrapShield(
        String name,
        String desc,
        int duration, 
        Effect counter, 
        boolean nullifies,
        int uses,
        HashSet<Shields.Trigger> eventTriggers,
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