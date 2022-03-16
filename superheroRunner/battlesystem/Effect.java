package battlesystem;

import java.util.EnumMap;
import java.util.Collection;
import java.util.Arrays;
// package Game.ablilites.Effects;
// i dont think multiplayer replit and github branches work
// that's probably true
/**
 * Acts as a long-lasting thing that will affect an entity
 */
public class Effect 
{

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
    private EnumMap<Modifier, EffectModifier> modifiers;
    
    /**
     * A basic enum for the types of effects.
     *
     */
    public static enum Type
    {
        ATTACK("attack"), 
        DEFENSE("defense"), 
        HEALTH("health"),
        MAX_HEALTH("Max health"),
        SHIELD("shield"), 
        DAMAGE("health"), 
        SPEED("speed"),
        GROUP("");

        public final String name;
        
        Type(String name)
        {
            this.name = name;
        }
    } 
    
    public static enum Modifier 
    {
        PERCENT
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
        Element element,
        EffectModifier[] modifiers) 
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
            modifiers
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
        boolean[] pierces,
        EffectModifier[] modifiers) 
    {
        this.strength = strength;
        this.typeOfEffect = type;
        this.duration = duration;
        this.permanent = permanent;
        this.name = name;
        this.desc = desc;
        this.element = element;
        this.pierces = pierces;
        this.modifiers = new EnumMap<>(Modifier.class);
        for (int i = 0; i < modifiers.length; i++)
        {
            EffectModifier modifier = modifiers[0];
            this.modifiers.put(modifier.getName(), modifier);
        }
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
        // System.out.println("called super");
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
        PercentageEffectModifier percent = (PercentageEffectModifier) modifiers.get(Modifier.PERCENT);
        int power = this.strength + ((percent == null) ? 0: percent.applyEffect(target));

        Object[] contents = new Object[]{target.getName(), typeOfEffect, power, name};
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
                    pierces,
                    getModifiers()
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
                    pierces,
                    getModifiers()
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
     * all effect modifiers this has
     * @return the modifiers
     */
    protected EffectModifier[] getModifiers()
    {
        // System.out.println(modifiers);
        Collection<EffectModifier> modifierCollection = modifiers.values();
        EffectModifier[] mods = new EffectModifier[]{};
        if (modifierCollection != null)
        {
            // System.out.println(Arrays.toString(modifierCollection.toArray(mods)));
            mods = modifierCollection.toArray(mods);
                        // System.out.println(Arrays.toString(mods));

        }
        return mods;
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
     * Get the effective strength
     * @param target the entity to draw strength from
     * @return how much effective strength this has
     */
    protected int getStrength(Entity target) 
    {
        PercentageEffectModifier percent = (PercentageEffectModifier) modifiers.get(Effect.Modifier.PERCENT);
        return this.strength + ((percent == null)? 0: percent.applyEffect(target));
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


    protected boolean[] getPierces() 
    {
        return pierces;
    }


    public String getName() 
    {
        return this.name;
    }


    public String getDesc() 
    {
        return this.desc;
    }


    public Element getElement() 
    {
        return this.element;
    }


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
}
