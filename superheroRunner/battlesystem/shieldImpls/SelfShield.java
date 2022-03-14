package battlesystem.shieldImpls;

import java.util.HashSet;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.Shield;
import battlesystem.databaseImpls.Elements;

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
        Shield.Trigger[] eventTriggers,
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
        HashSet<Shield.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = selfApply;
    }


    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster, 
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, victim.getName(), selfApply.getName(), null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.addEffect(selfApply.copy());
    }

    @Override
    public Shield copy()
    {
        return new SelfShield(getName(), getDesc(), getDuration(), selfApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}