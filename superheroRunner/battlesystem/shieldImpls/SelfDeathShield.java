package battlesystem.shieldImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.Shield;

/**
 * A shield that triggers when the person is about to die, only it affects the victim.
 *
 */
public class SelfDeathShield extends DeathShield 
{

	/**
	 * The basic constructor for this
	 * @param name the name of the shield
	 * @param desc the description of what this does
	 * @param duration how long it lasts
	 * @param effect the effect it will have
	 * @param nullifies if it nullifies future attacks
	 * @param uses how many uses are left.
	 */
    public SelfDeathShield(
        String name,
        String desc,
        int duration, 
        Effect effect,
        boolean nullifies,
        int uses)
    {
        super(name, desc, duration, effect, nullifies, uses);
    }

    /**
     * Get a copy of this shield.
     * @return the copy of the shield
     */
    @Override
    public Shield copy()
    {
        return new SelfDeathShield
        (
            getName(),
            getDesc(),
            getDuration(),
            getEffect().copy(),
            isNullifies(),
            getUses()
        );
    }

    /**
     * Apply the effect to the victim.
     * 
     * @param victim the person about to die.
     * @param caster effectively null.
     * @param log the log to record the effects used.
     */
    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        BattleLog log)
    {
        Object[] contents = new Object[]{
            victim.getName(), 
            getUses() - 1, 
            victim.getName(), 
            getEffect().getName(), 
            null,
            getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        victim.applyEffect(getEffect().copy(), log);
    }
}