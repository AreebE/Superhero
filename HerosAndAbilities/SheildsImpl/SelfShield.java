import java.util.HashSet;

public class SelfShield extends Shield
{
    private Effect selfApply;


    public SelfShield(
        String name,
        String desc,
        int duration, 
        Effect selfApply, 
        boolean nullifies,
        int uses,
        ShieldList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }
    

    public SelfShield(
        String name,
        String desc,
        int duration, 
        Effect selfApply, 
        boolean nullifies,
        int uses,
        HashSet<ShieldList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }


    @Override
    protected void applyShield(Entity target, Entity caster)
    {
        target.addEffect(selfApply.copy());
    }

    @Override
    public Shield copy()
    {
        return new SelfShield(getName(), getDesc(), getDuration(), selfApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}