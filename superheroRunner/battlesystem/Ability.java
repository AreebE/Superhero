package battlesystem;

import java.util.EnumMap;
import java.util.List;

import modifiers.RandomModifier;
import modifiers.GroupModifier;
import modifiers.RecoilModifier;
import modifiers.MultiCastModifier;
import modifiers.PercentageModifier;

import java.util.ArrayList;

/**
 * A class for the ability so heros can do things.
 *
 */
public abstract class Ability 
{
    public static enum Modifier
    {
        RANDOM,
        RECOIL,
        MULTICAST,
        PERCENTAGE,
        GROUP
    }
    
    public static enum Type
    {
        ATTACK, 
        DEFENSE, 
        SUPPORT
    }

    public static final int MISS = -1;

    public String name;
    private String description;
    private int cooldown;
    private int strength;
    private int turnsSinceUse;
    private Ability.Type type;
    private Element em;
    private EnumMap<Ability.Modifier, AbilityModifier> modifiers;
    private int chance;
    public transient static final int MAX_CHANCE = 256;
    
    private boolean keepGoing;
    
    /**
     * A constructor for when it is given an ability
     * @param tocopy the ability to copy from.
     */
    public Ability(Ability tocopy){
      this.name = tocopy.getName();
      this.description = tocopy.getDescription();
      this.cooldown = tocopy.getCooldown();
      this.strength = tocopy.getStrength();
      this.type = tocopy.getType();
      this.turnsSinceUse = tocopy.getCooldown();
      this.em = tocopy.getElement();
      this.modifiers = new EnumMap<>(Ability.Modifier.class);
    }

    /**
     * A more descriptive constructor.
     * @param name the name of the ability
     * @param desc the description of how it works.
     * @param cooldown How much cooldown it has
     * @param strength Its base strength
     * @param type The type it is
     * @param em the element it has
     * @param modifiers What modifiers it has.
     */
    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Ability.Type type,
        Element em,
        AbilityModifier... modifiers) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = cooldown;
        this.strength = strength;
        this.type = type;
        this.turnsSinceUse = cooldown;
        this.em = em;
        this.modifiers = new EnumMap<>(Ability.Modifier.class);
        
        for (AbilityModifier m : modifiers) 
        {
            // System.out.println(m + ", " + m.getModifier());
            this.modifiers.put(m.getModifier(), m);
        }
        // System.out.println(this.modifiers);
    }


    /**
     * An overloaded constructor 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown the cooldown after use
     * @param strength the strength of the attck
     * @param type the type of ability 
     * @param em its element
     * @param modifiers what modifiers it has (this is an EnumMap)
     */
    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Ability.Type type,
        Element em,
        EnumMap<Ability.Modifier, 
        AbilityModifier> modifiers) 
    {
        this(name, desc, cooldown, strength, type, em);
        this.modifiers = modifiers;
    }


    /**
     * get name
     * @return Get the name of this ability
     */
    public String getName() 
    {
        return name;
    }


    /**
     * get description
     * @return Get the description of this ability.
     */
    public String getDescription() 
    {
        return description;
    }

    /**
     * get cooldown
     * @return Get how many turns this will be off for after use.
     */
    public int getCooldown() 
    {
        return cooldown;
    }

    /**
     * get the strength
     * @return the base strength of this ability
     */
    public int getStrength() 
    {
        return strength;
    }

    /**
     * get element
     * @return the element this ability belongs to.
     */
    public Element getElement() 
    {
        return em;
    }

    /**
     * Use the ability. This will also trigger the modifiers that this ability has.
     * 
     * @param target the entity to target
     * @param caster the caster of this spell
     * @param otherTargets any other targets to target.
     * @param allPlayers  the other players in this battle
     * @param log the log to store actions.
     */
    public void useAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        keepGoing = true;
        Object[] contents = new Object[]{caster.getName(), target.getName(), name, (otherTargets == null)? 0: otherTargets.size()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ABILITY, contents));
        
        turnsSinceUse -= cooldown;
        
        RecoilModifier recoil = (RecoilModifier) modifiers.get(Ability.Modifier.RECOIL);
        RandomModifier random = (RandomModifier) modifiers.get(Ability.Modifier.RANDOM);
        MultiCastModifier multi = (MultiCastModifier) modifiers.get(Ability.Modifier.MULTICAST);
        PercentageModifier percent = (PercentageModifier) modifiers.get(Ability.Modifier.PERCENTAGE);
        GroupModifier group = (GroupModifier) modifiers.get(Ability.Modifier.GROUP);
        
        System.out.println(recoil + ", " + random + ", " + multi + ", " + percent + ", " + group);
        if (random == null 
            ||  random.triggerModifier(target, caster)) 
        {
            if (recoil != null) 
            {
                recoil.triggerModifier(target, caster);
            }
            int times = 1;
            if (multi != null)
            {
                times = multi.triggerModifier(target, caster);
            }
            for (int i = 0; i < times; i++)
            {
                int baseStrength = strength;
                int additionalStrength = 0;
                if (percent != null)
                {
                    // System.out.println("called percent");
                    additionalStrength = percent.triggerModifier(target, caster);
                }
                strength += additionalStrength;
                // System.out.println(strength);
                castAbility(target, caster, otherTargets, allPlayers, log);
                if (group != null)
                {
                    //strength *= group.triggerModifier(target, caster);
                    // System.out.println(strength);
                    for (int j = 0; j < otherTargets.size(); j++)
                    {
                        castAbility(otherTargets.get(j), caster, otherTargets, allPlayers, log);
                    }
                }
                  
                strength = baseStrength;
                if (!keepGoing){
                    contents = new Object[]{BattleLog.Entry.Interruption.SHIELD};
                    log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
                    return;                
                }
            }
            // contents = new Object[]{caster.getName(), target.getName(), name, (group == null)? 0: group.getLimit()};
            // log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ABILITY, contents));
            return;
        }
        contents = new Object[]{BattleLog.Entry.Interruption.RANDOM};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.INTERRUPTED, contents));
        return;
    }


    /**
     * Cast the ability. Do not call this one: call useAbility instead. This is meant to be overriden by other classes.
     * @param target the target
     * @param caster the caster
     * @param otherTargets the other targets to hit
     * @param allPlayers all other players
     * @param log the battlelog to record actions
     */
    public abstract void castAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log
    );

    /**
     * The string representation of the ability. Starts with name, then description, then cooldown
     */
    @Override
    public String toString() 
    {
        StringBuilder output = new StringBuilder();
        output.append(name)
            .append(": ")
            .append(description).append(". ");
        if (cooldown != 0) 
        {
            output.append("Has a cooldown of ")
                .append(cooldown)
                .append(" turns.");
        } 
        else 
        {
            output.append("It can be used every round.");
        }
        return output.toString();
    }


    /**
     * Return if they are equal based on the name
     * @return if they really are equal
     */
    @Override
    public boolean equals(
        Object other) 
    {
        Ability otherAbility = (Ability) other;
        return this.name.equals(otherAbility.getName());
    }


    /**
     * update the turns used since the ability was cast.
     */
    public void reduceCooldown() 
    {
        if (turnsSinceUse < cooldown) 
        {
            turnsSinceUse++;
        }
    }


    /**
     * Only returns true if the turns since it was used is the same as its cooldown.
     * @return if this ability can be used
     */
    public boolean ableToUseAbility() 
    {
        // System.out.println("turns since use for " + name + ": " + turnsSinceUse);
        return turnsSinceUse == cooldown;
    }


    /**
     * Get how many turns this will be on cooldown for.
     * @return how many turns are needed.
     */
    public int getTurnsNeeded() 
    {
        return cooldown - turnsSinceUse;
    }
    
    /**
     * 
     * @return the type of this ability
     */
    public Type getType(){
      return this.type;
    }

    /**
     * 
     * @return the chance this ability has to hit.
     */
    public int getChance(){
      return this.chance;
    }

    /**
     * A method for copying this ability
     * @return the copy of this.
     */
    public abstract Ability copy();
    
    /**
     * check if this has a modifier
     * @param modifierName the name of this modifier
     * @return if it has a certain one.
     */
    public boolean hasModifier(Ability.Modifier modifierName)
    {
        return modifiers.containsKey(modifierName);
    }
    
    /**
     * return the modifier 
     * @param modifierName the name of this modifier
     * @return the modifier asked for
     */
    public AbilityModifier getModifier(Ability.Modifier modifierName)
    {
        return modifiers.get(modifierName);
    }
    
    /**
     * get all modifiers
     * @return all modifiers
     */
    public EnumMap<Ability.Modifier, AbilityModifier> getModifiers(){
      return this.modifiers;
    }
    
    /**
     * A method used to notify the program when to stop attacking
     */
    protected void stopAttack()
    {
        keepGoing = false;
    }
    
    /**
     * Get how many people this can target, though return 1 at most.
     * @return the number of targets.
     */
    public int getTargetAmount()
    {
    	try 
    	{
    		return ((modifiers.GroupModifier) modifiers.get(Modifier.GROUP)).getLimit();
    	}
    	catch (NullPointerException npe)
    	{
    		return 1;
    	}
    }


}