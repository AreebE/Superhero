package revitalizedLegends.gameSystem.effectImpls;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Element;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.objectMapImpls.Effects;

/**
 * The delayed effect is intended to take place after some turns pass.
 *
 */
public class DelayedEffect extends Effect 
{
	
	public DelayedEffect(JSONObject json)
	{
		super(json);
	}

	/**
	 * The delayed effect basic constructor
	 * 
	 * @param strength how much base strength this has
	 * @param type the type of effect
	 * @param timer *NEW* how much time will pass before the effect triggers
	 * @param name the name of the ability
	 * @param desc the description of how this works
	 * @param element the element of this effection
	 * @param modifiers the modifiers this has
	 */
    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element) 
    {
        this
        (
            strength, 
            type, 
            timer, 
            name, 
            desc, 
            element, 
            null,
            null
        );
    }

    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
        String stack) 
    {
        this
        (
            strength, 
            type, 
            timer, 
            name, 
            desc, 
            element, 
            stack,
            null
        );
    }

    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
        boolean[] pierces) 
    {
        this
        (
            strength, 
            type, 
            timer, 
            name, 
            desc, 
            element, 
            null,
            pierces
        );
    }
    
    /**
     * The version for the attack version
     * @param strength the base strength of this effect
     * @param type the time of effect
     * @param timer how many turns need to pass
     * @param name the name of this effect
     * @param desc the description of this effect
     * @param element the element of this effect
     * @param pierces if it pierces shield + defense
     * @param modifiers the modifiers of this effect
     */
    public DelayedEffect(
        int strength, 
        Effect.Type type, 
        int timer, 
        String name, 
        String desc, 
        Element element,
        String stack,
        boolean[] pierces) 
    {
        super
        (
            strength, 
            type, 
            timer, 
            true, 
            name, 
            desc, 
            element,
            stack,
            pierces
        );
    }
    
    /**
     * This is so the effect doesn't activate prematurely.
     * @param target the person with the effect
     * @param log the log to store events.
     */
    @Override
    public void useEffect(
        Entity target,
        BattleLog log) 
    {
        reduceDuration(target, log);
    }


    /**
     * When removing the effect, it applies it.
     * @param target the person who had the effect
     * @param log the battle log for recording the events
     */
    @Override
    protected void removeEffect(
        Entity target,
        BattleLog log) 
    {
        applyEffect(target, log);
        target.removeEffect(this);
    }


    /**
     * a copy method to get a copy of the effect
     * @return the new delayed effect
     */
    @Override
    public Effect copy() 
    {
        return new DelayedEffect
                    (
                        getStrength(), 
                        getType(), 
                        getDuration(), 
                        getName(), 
                        getDesc(), 
                        getElement(), 
                        getStack(),
                        getPierces()
                    );
    }

    /**
     * A copy method to get a new one.
     * @param additionalStrength add some strength to this effect.
     * @return a new effect with the strength modified
     */
    @Override
    public Effect copy(int additionalStrength) 
    {
        return new DelayedEffect
                (
                    getStrength() + additionalStrength, 
                    this.getType(), 
                    this.getDuration(), 
                    getName(), 
                    getDesc(), 
                    getElement(),
                    getStack(),
                    getPierces()
                );
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject effect = super.toJson();
    	effect.put(TYPE_KEY, EffectLoader.DELAY);
    	return effect;
    }
}