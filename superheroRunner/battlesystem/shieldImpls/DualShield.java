package battlesystem.shieldImpls;

import java.util.HashSet;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.Shield;
import battlesystem.databaseImpls.Elements;

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
        Shield.Trigger[] eventTriggers,
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
        HashSet<Shield.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        super(name, desc, duration, nullifies, uses, eventTriggers, elementTriggers);
        this.selfApply = self;
        this.casterApply = caster;
    }


    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), casterApply.getName(), selfApply.getName(), getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.addEffect(selfApply.copy());
        caster.addEffect(casterApply.copy());
    }

    @Override
    public Shield copy()
    {
        return new DualShield(getName(), getDesc(), getDuration(), selfApply.copy(), casterApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}