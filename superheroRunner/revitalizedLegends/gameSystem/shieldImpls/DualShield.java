package revitalizedLegends.gameSystem.shieldImpls;

import java.util.HashSet;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Elements;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Shield;
import revitalizedLegends.gameSystem.Storage;

/**
 * A dual shield to apply two effects.
 * IT CANNOT WORK WITH 'PASS,' so make sure to list all seperate instances this can apply in.
 *
 */
public class DualShield extends Shield
{
	
	
	
	private static final String SELF_APPLY_EFFECT_KEY = "self apply";
	private static final String CASTER_APPLY_EFFECT_KEY = "caster apply";
	
    private String selfApply;
    private String casterApply;

    public DualShield(JSONObject json)
	{
    	super(json);
		selfApply = json.getString(SELF_APPLY_EFFECT_KEY).toLowerCase();
		casterApply = json.getString(CASTER_APPLY_EFFECT_KEY).toLowerCase();
	}
    
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
        String self,
        String caster, 
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
        String self,
        String caster, 
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
        Game g,
        BattleLog log)
    {
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), casterApply, selfApply, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));

        victim.addStartingEffect(g.getEffect(selfApply));
        caster.addStartingEffect(g.getEffect(casterApply));
    }

    /**
     * Create a copy of the shield
     * @return the copy
     */
    @Override
    public Shield copy()
    {
        return new DualShield(getName(), getDesc(), getDuration(), selfApply, casterApply, isNullifies(), getUses(), getEventTriggers(), getElementTriggers());
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject shield = super.toJson();
    	shield.put(TYPE_KEY, ShieldLoader.DUAL);
    	shield.put(CASTER_APPLY_EFFECT_KEY, casterApply);
    	shield.put(SELF_APPLY_EFFECT_KEY, selfApply);
    	return shield;
    }

    @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getEffect(casterApply) != null && s.getEffect(selfApply) != null;
    }
}