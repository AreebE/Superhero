package battlesystem;

import java.util.HashSet;

public class DualShield extends Shield
{
    private Effect selfApply;
    private Effect casterApply;

    public DualShield(
        String name,
        String desc,
        int duration, 
        Effect self,
        Effect caster, 
        boolean nullifies,
        int uses,
        Shields.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = self;
        this.casterApply = caster;
    }
    

    public DualShield(
        String name,
        String desc,
        int duration, 
        Effect self,
        Effect caster, 
        boolean nullifies,
        int uses,
        HashSet<Shields.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = self;
        this.casterApply = caster;
    }


    @Override
    protected void applyShield(Entity victim, Entity caster)
    {
        victim.addEffect(selfApply.copy());
        caster.addEffect(casterApply.copy());
    }

    @Override
    public Shield copy()
    {
        return new DualShield(getName(), getDesc(), getDuration(), selfApply.copy(), casterApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}