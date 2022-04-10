package battlesystem.shieldImpls;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Elements;
import battlesystem.Entity;
import battlesystem.Game;
import battlesystem.Shield;

/**
 * This shield is triggered whenever this character is about to die.
 *
 */
public class DeathShield extends Shield
{

    private String effect;

    /**
     * A basic constructor for this shield
     * 
     * @param name the name of this shield
     * @param desc the description of what it does
     * @param duration the duration this lasts for
     * @param effect the effect it will apply
     * @param nullifies if it nullifies future attacks.
     * @param uses how many uses it has
     */
    public DeathShield(
        String name,
        String desc,
        int duration, 
        String effect,
        boolean nullifies,
        int uses)
    {
        super(name, desc, duration, nullifies, uses,  new Shield.Trigger[]{Shield.Trigger.DEATH}, new Elements.Name[]{Elements.Name.ALL});
        this.effect = effect;
        // System.out.println(effect);
    }
  
    /**
     * A method for creating a copy of this ability
     */
    @Override
    public Shield copy()
    {
        return new DeathShield
        (
            getName(),
            getDesc(),
            getDuration(),
            effect,
            isNullifies(),
            getUses()
        );
    }

    /**
     * Apply the shield upon death to the 'victim.'
     *  
     *  @param victim the person about to die
     *  @param caster the creator of the victim.
     *  @param log record the shield used.
     */
    @Override
    protected void applyShield(
        Entity victim, 
        Entity caster,
        Game g,
        BattleLog log)
    {
        // System.out.println("appltied death shield to " + victim.getName());
        Object[] contents = new Object[]{victim.getName(), getUses() - 1, caster.getName(), effect, null, getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_TRIGGER, contents));
        caster.applyEffect(g.getEffect(effect), log);
    }

    /**
     * A method to get the effect of this shield.
     * @return the effect
     */
    protected String getEffect()
    {
        return effect;
    }
}