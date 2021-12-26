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
        ShieldList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
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
        HashSet<ShieldList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.counter = counter;
    }


    @Override
    protected void applyShield(Entity target, Entity caster)
    {
                // System.out.println("Apply " + isNullifies());
        caster.addEffect(counter);
    }

    @Override
    public Shield copy()
    {
        return new TrapShield(getName(), getDesc(), getDuration(), counter.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}