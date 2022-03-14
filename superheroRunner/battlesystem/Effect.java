package battlesystem;

import java.util.EnumMap;
import java.util.Collection;
import java.util.Arrays;
// package Game.ablilites.Effects;
// i dont think multiplayer replit and github branches work
// that's probably true
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
     */
    public void useEffect(
        Entity target,
        BattleLog log) 
    {
        // System.out.println("called super");
        applyEffect(target, log);
        reduceDuration(target, log);
    }


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

    public void reduceDuration()
    {
        duration--;
    }


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


    public boolean isRemovable() 
    {
        return true;
    }


    protected Effect.Type getType() 
    {
        return this.typeOfEffect;
    }

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

    protected int getStrength() 
    {
        return this.strength;
    }  

    public void setName(String newName)
    {
        this.name = newName;
    }

    protected int getStrength(Entity target) 
    {
        PercentageEffectModifier percent = (PercentageEffectModifier) modifiers.get(Effect.Modifier.PERCENT);
        return this.strength + ((percent == null)? 0: percent.applyEffect(target));
    }

    public int getDuration() 
    {
        return this.duration;
    }


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
