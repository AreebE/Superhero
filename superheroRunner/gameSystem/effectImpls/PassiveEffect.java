package gameSystem.effectImpls;

import org.json.JSONObject;

import gameSystem.BattleLog;
import gameSystem.Effect;
import gameSystem.Element;
import gameSystem.Entity;

/**
 * A long term passive effect for some characters
 *
 */
public class PassiveEffect extends Effect 
{
	
	public PassiveEffect(JSONObject json)
	{
		super(json);
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
    public PassiveEffect(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        Element element,
        boolean[] pierces) 
    {
        super
        (
            strength, 
            type, 
            0, 
            true, 
            name,
            desc, 
            element, 
            pierces
        );
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
    public PassiveEffect(
        int strength, 
        Effect.Type type, 
        String name, 
        String desc, 
        Element element) 
    {
        this
        (
            strength, 
            type, 
            name,
            desc, 
            element, 
            null
        );
    }


    /**
     * Just to prevent the effect from ever ending.
     * @param target is meaningless here
     * @param log is meaningless here
     */
    @Override
    public void reduceDuration(
        Entity target,
        BattleLog log) 
    {
    }

    /**
     * The basic copy version; nothing much to say
     * @return a new passive effect
     */
    @Override
    public Effect copy() 
    {
        return new PassiveEffect
                (
                    getStrength(),
                    getType(), 
                    getName(), 
                    getDesc(), 
                    getElement(),
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
        return new PassiveEffect
                    (
                        getStrength() + additionalStrength, 
                        getType(),
                        getName(), 
                        getDesc(), 
                        getElement(),
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
    	effect.put(TYPE_KEY, EffectLoader.PASSIVE);
    	return effect;
    }
}