package revitalizedLegends.gameSystem.effectImpls;

import org.json.JSONObject;

import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Element;
import revitalizedLegends.gameSystem.Entity;

public class DelayedPassive extends PassiveEffect
{
    private static final String DELAY_KEY = "delay";
    private int currentCount;
    private int delay;
    public DelayedPassive(JSONObject json)
	{
		super(json);
        delay = json.getInt(DELAY_KEY);
        currentCount = 0;
	}
	/**
	 * The basic constructor for a passive effect that applies damage
	 * @param strength the base strength
	 * @param type the type of effect
	 * @param name the name of the effect
	 * @param desc the description of what it does
	 * @param element the element it belongs to
	 * @param pierces if it pierces defense + shield
	 * @param modifiers the modifiers this effect has
	 */
  
    
    public DelayedPassive(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        int delay,
        Element element,
        String stack,
        boolean[] pierces) 
    {
        super
        (
            strength, 
            type, 
            name,
            desc, 
            element, 
            stack,
            pierces
        );
        this.delay = delay;
        this.currentCount = 0;
    }
    
    /**
     * The constructor that won't work with damage effects
     * 
     * @param strength the base strength of this effect
     * @param type the type of effect
     * @param name the name of the effect
     * @param desc the description of how it works
     * @param element the element it belongs to
     * @param modifiers what modifiers it has
     */
    public DelayedPassive(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        int delay,
        Element element) 
    {
        this
        (
            strength, 
            type, 
            name,
            desc, 
            delay,
            element, 
            null,
            null
        );
    }

    public DelayedPassive(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        int delay,
        Element element,
        boolean[] pierces) 
    {
        this
        (
            strength, 
            type, 
            name,
            desc, 
            delay,
            element, 
            null,
            pierces
        );
    }

    public DelayedPassive(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        int delay,
        Element element,
        String stack) 
    {
        this
        (
            strength, 
            type, 
            name,
            desc, 
            delay,
            element, 
            stack,
            null
        );
        this.delay = delay;
    }


    /**
     * Just to prevent the effect from ever ending.
     * @param target is meaningless here
     * @param log is meaningless here
     */
    @Override
    public void useEffect(Entity target, BattleLog log)
    {
        currentCount++;
        if (currentCount == delay)
        {
            currentCount = 0;
            applyEffect(target, log);
        }
    }

    /**
     * The basic copy version; nothing much to say
     * @return a new passive delayed effect
     */
    @Override
    public Effect copy() 
    {
        return new DelayedPassive
                (
                    getStrength(),
                    getType(), 
                    getName(), 
                    getDesc(), 
                    delay,
                    getElement(),
                    getStack(),
                    getPierces()
                );
    }

    /**
     * The upgraded version for the copy effect
     * @param the additional strength to add on top of the base
     * @return the upgraded effect
     */
    @Override
    public Effect copy(int additionalStrength) {
        return new DelayedPassive
                    (
                        getStrength() + additionalStrength, 
                        getType(),
                        getName(), 
                        getDesc(), 
                        delay,
                        getElement(),
                        getStack(),
                        getPierces()
                    );
        }

    /**
     * Overrided this method just so nothing can remove it.
     * @return false. Nothing can remove a passive effect.
     */
    @Override
    public boolean isRemovable() 
    {
        return false;
    }
    
    @Override
    public JSONObject toJson()
    {
    	JSONObject effect = super.toJson();
    	effect.put(TYPE_KEY, EffectLoader.DELAYED_PASSIVE);
    	effect.put(DELAY_KEY, delay);
        return effect;
    }
}