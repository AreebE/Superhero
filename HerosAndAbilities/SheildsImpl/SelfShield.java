package battlesystem;

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
        Shields.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
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
        HashSet<Shields.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }


    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster, 
        StringBuilder actions)
    {
        actions.append(victim.getName())
            .append(" recieved the effect of ")
            .append(selfApply.getName())
            .append("\n");
        victim.addEffect(selfApply.copy());
    }

    @Override
    public Shield copy()
    {
        return new SelfShield(getName(), getDesc(), getDuration(), selfApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}