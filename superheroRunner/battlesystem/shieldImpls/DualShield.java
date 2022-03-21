package battlesystem.shieldImpls;

import java.util.HashSet;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Elements;
import battlesystem.Entity;
import battlesystem.Shield;

/**
 * A dual shield to apply two effects.
 * IT CANNOT WORK WITH 'PASS,' so make sure to list all seperate instances this can apply in.
 *
 */
public class DualShield extends Shield
{
    private Effect selfApply;
    private Effect casterApply;

    /**
     * the constructor for a dual shield.
     * 
     * @param name the name of this shield
     * @param desc the description of what it does
     * @param duration how long this shield lasts
     * @param self *NEW* The effect to apply to the victim
     * @param caster *NEW* the effect to apply to the caster/attacker/other.
     * @param nullifies If this nullifies future attacks
     * @param uses How many uses this has
     * @param eventTriggers What events this triggers under
     * @param elementTriggers what elements this triggers under.
     */
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
    

    /**
     * A constructor for the copy method
     * 
     * @param name what the shield is called
     * @param desc how it works
     * @param duration how long it lasts
     * @param self effect to apply to victim
     * @param caster effect to apply to caster
     * @param nullifies if this nullifies future attacks
     * @param uses the uses left
     * @param eventTriggers the events it triggers for
     * @param elementTriggers the elements it triggers for.
     */
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


    /** 
     * This applies two effects: one for the victim, the other to the caster
     * 
     * @param victim the victim of the event
     * @param caster the person who caused the event
     */
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

    /**
     * Create a copy of the shield
     * @return the copy
     */
    @Override
    public Shield copy()
    {
        return new DualShield(getName(), getDesc(), getDuration(), selfApply.copy(), casterApply.copy(), isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }

}