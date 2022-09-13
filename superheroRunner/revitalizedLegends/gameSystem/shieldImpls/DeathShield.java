package revitalizedLegends.gameSystem.shieldImpls;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Elements;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.Shield;
import revitalizedLegends.gameSystem.Storage;

/**
 * This shield is triggered whenever this character is about to die.
 *
 */
public class DeathShield extends Shield
{
	private static final String EFFECT_KEY = "effect";
    private String effect;

    public DeathShield(JSONObject json)
    {
    	super(json);
    	effect = json.getString(EFFECT_KEY).toLowerCase();
    }
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
        caster.addStartingEffect(g.getEffect(effect));
    }

    /**
     * A method to get the effect of this shield.
     * @return the effect
     */
    protected String getEffect()
    {
        return effect;
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject shield = super.toJson();
    	shield.put(TYPE_KEY, ShieldLoader.DEATH);
    	shield.put(EFFECT_KEY, effect);
    	return shield;
    }

    @Override
    public boolean verifyValidity(Storage s)
    {
        return s.getEffect(effect) != null;
    }
}