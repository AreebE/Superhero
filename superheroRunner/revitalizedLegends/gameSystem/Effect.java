package revitalizedLegends.gameSystem;

import java.util.EnumMap;
import java.util.Collection;
import java.util.Arrays;

import org.json.JSONObject;
// package Game.ablilites.Effects;
// i dont think multiplayer replit and github branches work
// that's probably true
/**
 * Acts as a long-lasting thing that will affect an entity
 */
public class Effect 
    implements Saveable
{
	public static final String TYPE_KEY = "type";
	private static final String STRENGTH_KEY = "strength";
	private static final String STAT_KEY = "stat";
	private static final String DURATION_KEY = "duration";
	private static final String IS_PERMANENT_KEY = "permanent";
	private static final String NAME_KEY = "name";
	private static final String DESC_KEY = "desc";
	private static final String ELEMENT_KEY = "element";
	private static final String PIERCES_DEFENSE_KEY = "pierces defense";
	private static final String PIERCES_SHIELD_KEY = "pierces shield";
	private static final String STACK_NAME_KEY = "stack name";
    
    private final static int PIERCES_DEFENSE_INDEX = 0;
    private final static int PIERCES_SHIELD_INDEX = 1;
    private int strength;
    private Type typeOfEffect;
    private int duration;
    private boolean permanent;
    private String name;
    private String desc;
    private Element element;
    private boolean[] pierces;
    private String stack;
    
    /**
     * A basic enum for the types of effects.
     *
     */
    public static enum Type
    {
        ATTACK("attack"), 
        DEFENSE("defense"), 
        HEALTH("health"),
        MAX_HEALTH("max health"),
        SHIELD("shield"), 
        DAMAGE("damage"), 
        SPEED("speed"),
        STACK("stack"),
        GROUP("");

        public final String name;
        
        Type(String name)
        {
            this.name = name;
        }
        
        public static Type getType(String name)
        {
        	switch(name)
        	{
        		default:
        		case "attack":
        			return ATTACK;
        		case "defense":
        			return DEFENSE;
        		case "health":
        			return HEALTH;
        		case "max health":
        			return MAX_HEALTH;
        		case "shield":
        			return SHIELD;
        		case "damage":
        			return DAMAGE;
        		case "speed":
        			return SPEED;
                case "stack":
                    return STACK;
        		case "":
        			return GROUP;
        		
        		
        	}
        }
    } 
    
    public Effect(
    		JSONObject json)
    {
    	this.strength = json.getInt(STRENGTH_KEY);
    	this.typeOfEffect = Type.getType(json.getString(STAT_KEY));
    	this.duration = json.getInt(DURATION_KEY);
    	this.permanent = json.getBoolean(IS_PERMANENT_KEY);
    	this.name = json.getString(NAME_KEY);
    	this.desc = json.getString(DESC_KEY);
    	this.element = Elements.getElement(json.getString(ELEMENT_KEY));
    	
//    	System.out.println(name + ", " + element);
    	if (json.has(PIERCES_DEFENSE_KEY))
    	{
        	this.pierces = new boolean[] {json.getBoolean(PIERCES_DEFENSE_KEY), json.getBoolean(PIERCES_SHIELD_KEY)};
    	}
        if (json.has(STACK_NAME_KEY))
        {
            this.stack = json.getString(STACK_NAME_KEY);
        }
    }
    /**
     * A basic constructor for Effect.
     * @param strength the base strength of this effect
     * @param type the type of effect
     * @param duration the duration of the effect
     * @param permanent whether the change is permanent
     * @param name the name of the effect
     * @param desc the description
     * @param element the element of this effect
     * @param modifiers any modifiers.
     */
    public Effect(
        int strength, 
        Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        Element element) 
    {
        this
        (
            strength, 
            type, 
            duration,
            permanent, 
            name, 
            desc, 
            element, 
            null,
            null
        );
    }

    public Effect(
        int strength, 
        Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        Element element,
        boolean[] pierces) 
    {
        this
        (
            strength, 
            type, 
            duration,
            permanent, 
            name, 
            desc, 
            element, 
            null,
            pierces
        );
    }

    public Effect(
        int strength, 
        Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        String stack,
        Element element) 
    {
        this
        (
            strength, 
            type, 
            duration,
            permanent, 
            name, 
            desc, 
            element, 
            stack,
            null
        );
    }

    /**
     * Another constructor, but for damage
     * 
     * @param strength the base strength of this effect
     * @param type the type of effect
     * @param duration the duration of the effect
     * @param permanent whether the change is permanent
     * @param name the name of the effect
     * @param desc the description
     * @param element the element of this effect
     * @param pierces if it pierces defense and if it pierces shield
     * @param modifiers any modifiers.
     */
    public Effect(
        int strength, 
        Effect.Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        Element element, 
        String stack,
        boolean[] pierces) 
    {
        this.strength = strength;
        this.typeOfEffect = type;
        this.duration = duration;
        this.permanent = permanent;
        this.name = name;
        this.desc = desc;
        this.element = element;
        this.stack = stack;
        this.pierces = pierces;
    }


    /**
     * Will apply its effect to the given Entity
     * 
     * @param target the receiver of this effect
     * @param log the battle log to record what was done
     */
    public void useEffect(
        Entity target,
        BattleLog log) 
    {
        // System.out.println("called super, "+ name + "----" + target);
        applyEffect(target, log);
        reduceDuration(target, log);
    }


    /**
     * Apply the effect onto this character.
     * @param target the reciever of the attack
     * @param log the log to record what was done
     */ 
    public void applyEffect(
        Entity target,
        BattleLog log) 
    {
        int power = this.strength;

        Object[] contents = new Object[]{target.getName(), typeOfEffect, power, name, stack};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.APPLY_EFFECT, contents));
        // System.out.println("Applied " + name + ", " + type power + " " + percent);
        this.applyEffect(target, power);
    }

    /**
     * Do something to the character
     * @param target the entity to affect
     * @param power the amount to change the statistic by
     */
    protected void applyEffect(
        Entity target, 
        int power) 
    {
        switch (typeOfEffect) 
        {
            case ATTACK:
                // System.out.println("updating attack");
                target.addAttack(power);
                break;
            case DEFENSE:
                target.addDefense(power);
                break;
            case SPEED:
                target.addSpeed(power);
                break;
            case MAX_HEALTH:
                target.addMaxHealth(power);
                break;
            case HEALTH:
                target.healHealth(power);
                break;
            case SHIELD:
                target.addShieldHealth(power);
                break;
            case DAMAGE:
                target.dealEffectDamage
                (
                    power, 
                    pierces[PIERCES_DEFENSE_INDEX], 
                    pierces[PIERCES_SHIELD_INDEX]
                );
                break;
            case STACK:
//                target.changeStack(stack, power);
        }
    }


    /**
     * reduce the duration of this effect; remove it when the cooldown is over
     * 
     * @param target the entity to target
     * @param log the log to record what was done
     */
    public void reduceDuration(
        Entity target,
        BattleLog log) 
    {
        duration--;
        if (duration == 0) 
        {
            Object[] contents = new Object[]{target.getName(), new String[]{name}};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.EFFECT_REMOVED, contents));
            removeEffect(target, log);
        }
    }

    /**
     * reduce the duration of this effect
     */
    public void reduceDuration()
    {
        duration--;
    }


    /**
     * remove this effect. If it isn't permanent, then remove the effects.
     * @param target the entity that has this effect
     * @param log the battlelog
     */
    protected void removeEffect(
        Entity target,
        BattleLog log) 
    {
        if (!permanent) 
        {
            switch (typeOfEffect) 
            {
                case ATTACK:
                    target.addAttack(-strength * duration);
                    break;
                case DEFENSE:
                    target.addDefense(-strength * duration);
                    break;
                case SPEED:
                    target.addDefense(-strength * duration);
            }
        }
        target.removeEffect(this);
    }


    /*
     * Creates a copy of the Effect so that two people wouldn't share the same one
     */
    /**
     * Create a copy of this effect
     * @return the copy
     */
    public Effect copy() 
    {
        return new Effect
                (
                    strength, 
                    typeOfEffect, 
                    duration, 
                    permanent, 
                    name, 
                    desc, 
                    element, 
                    stack,
                    pierces
                );
    }

    /**
     * Create a copy of this effect based on some additional strength
     * @param additionalStrength the additional strength to add
     * @return the given copy
     */
    public Effect copy(int additionalStrength) 
    {
        return new Effect
                (
                    strength + additionalStrength, 
                    typeOfEffect, 
                    duration, 
                    permanent, 
                    name, 
                    desc, 
                    element, 
                    stack,
                    pierces
                );
    }

    /**
     * If this effect can be removed through cleansing effects
     * @return true if it can be removed.
     */
    public boolean isRemovable() 
    {
        return true;
    }

    /**
     * The type of effect this is
     * @return the type
     */
    protected Effect.Type getType() 
    {
        return this.typeOfEffect;
    }

  
    /**
     * get the strength of this character
     * @return the strength
     */
    protected int getStrength() 
    {
        return this.strength;
    }  

    /**
     * Change the name of this effect
     * @param newName the new name to give.
     */
    public void setName(String newName)
    {
        this.name = newName;
    }

  
    /**
     * the current duration of the effect
     * @return the amount of turns this will stay for.
     */
    public int getDuration() 
    {
        return this.duration;
    }

    /**
     * If this effect's effects are permanent
     * @return true if the buffs will stay.
     */
    protected boolean isPermanent() 
    {
        return permanent;
    }


    /**
     * Get the information about piercing.
     * @return if it pierces defense + shield
     */
    protected boolean[] getPierces() 
    {
        return pierces;
    }

    /**
     * the name of this effect
     * @return the name
     */
    public String getName() 
    {
        return this.name;
    }

    /**
     * the description of this description
     * @return the description
     */
    public String getDesc() 
    {
        return this.desc;
    }

    /**
     * the elemental attributes
     * @return the element
     */
    public Element getElement() 
    {
        return this.element;
    }

    public String getStack()
    {
        return stack;
    }

    /**
     * The string version of this effect.
     * @return the name, then the description, then how many turns are left.
     */
    @Override
    public String toString()
     {
        return name 
                + " - " 
                + desc 
                + " (" 
                + getDuration() 
                + " turns left)";
    }
    

    public JSONObject toJson()
    {
    	JSONObject effect = new JSONObject();
    	effect.put(TYPE_KEY, "standard");
    	effect.put(STRENGTH_KEY, strength);
    	effect.put(STAT_KEY, typeOfEffect.name);
    	effect.put(DURATION_KEY, duration);
    	effect.put(IS_PERMANENT_KEY, permanent);
    	effect.put(NAME_KEY, name);
    	effect.put(DESC_KEY, desc);
    	effect.put(ELEMENT_KEY, element.getName().toLowerCase());
    	if (pierces != null)
    	{
    		effect.put(PIERCES_DEFENSE_KEY, pierces[PIERCES_DEFENSE_INDEX]);
        	effect.put(PIERCES_SHIELD_KEY, pierces[PIERCES_SHIELD_INDEX]);
    	}
        if (stack != null)
        {
            effect.put(STACK_NAME_KEY, stack);
        }
    	return effect;
    }

    @Override
    public boolean verifyValidity(Storage s)
    {
        return true;
    }
}
