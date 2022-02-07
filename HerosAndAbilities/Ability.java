package battlesystem;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

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
    //private Ability.Name enumName;
    private Element em;
    private EnumMap<Ability.Modifier, AbilityModifier> modifiers;
    private int chance;
    public static final int MAX_CHANCE = 256;

    private boolean keepGoing;

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



    public String getName() 
    {
        return name;
    }


    public String getDescription() 
    {
        return description;
    }


    public int getCooldown() 
    {
        return cooldown;
    }


    public int getStrength() 
    {
        return strength;
    }


    public Element getElement() 
    {
        return em;
    }

    
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


    protected abstract void castAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log
    );


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


    @Override
    public boolean equals(
        Object other) 
    {
        Ability otherAbility = (Ability) other;
        return this.name.equals(otherAbility.getName());
    }


    public void reduceCooldown() 
    {
        if (turnsSinceUse < cooldown) 
        {
            turnsSinceUse++;
        }
    }


    public boolean ableToUseAbility() 
    {
        // System.out.println("turns since use for " + name + ": " + turnsSinceUse);
        return turnsSinceUse == cooldown;
    }


    public int getTurnsNeeded() 
    {
        return cooldown - turnsSinceUse;
    }


    public abstract Ability copy();
    
    public boolean hasModifier(Ability.Modifier modifierName)
    {
        return modifiers.containsKey(modifierName);
    }
    
    public AbilityModifier getModifier(Ability.Modifier modifierName)
    {
        return modifiers.get(modifierName);
    }
    
    public EnumMap<Ability.Modifier, AbilityModifier> getModifiers(){
      return this.modifiers;
    }
    
    protected void stopAttack()
    {
        keepGoing = false;
    }
}