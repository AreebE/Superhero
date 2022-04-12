package battlesystem.effectImpls;

import org.json.JSONObject;

import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Element;
import battlesystem.Entity;

// package Game.ablilites.Effects;

/**
 * This effect is used to apply things instantly
 * @author Areeb Emran
 *
 */
public class InstantEffect extends Effect 
{
	public InstantEffect(JSONObject json)
	{
		super(json);
	}
	
	/**
	 * a basic constructor
	 * @param strength The base strength
	 * @param type the type of effect
	 * @param name the name of the effect
	 * @param desc the description of what it does
	 * @param element what element it is
	 * @param modifiers the modifiers it has
	 */
    public InstantEffect(
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
     * An instant effect for damage effects
     * 
     * @param strength the basic strength
     * @param type the type of effect
     * @param name the name of the effect
     * @param desc the description of what it does
     * @param element the element it belongs to
     * @param pierces if it pierces the defense and/or shields.
     * @param modifiers the modifiers it has
     */
    public InstantEffect(
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
     * Just remove the efffect
     * @param the entity with the effect
     * @param log does not matter in this method.
     */
    @Override
    public void reduceDuration(
        Entity target,
        BattleLog log) 
    {
        target.removeEffect(this);
    }

    /**
     * A basic copy method
     * @return a copy of the effect
     */
    @Override
    public Effect copy() {
        return new InstantEffect
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
     * A buffed copy method
     * @param the additionalStrength to add to this effect
     * @return an upgraded version of the previous effect.
     */
    @Override
    public Effect copy(int additionalStrength) {
        return new InstantEffect
                    (
                        getStrength() + additionalStrength, 
                        getType(),
                        getName(), 
                        getDesc(), 
                        getElement(),
                        getPierces()
                    );
    }
    
    @Override 
    public JSONObject toJson()
    {
    	JSONObject effect = super.toJson();
    	effect.put(TYPE_KEY, EffectLoader.INSTANT);
    	return effect;
    }
}