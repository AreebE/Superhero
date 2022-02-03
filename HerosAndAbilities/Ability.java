package battlesystem;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

public abstract class Ability 
{

    public static final int MISS = -1;

    public String name;
    private String description;
    private int cooldown;
    private int strength;
    private int turnsSinceUse;
    private Abilities.Type type;
    //private Abilities.Name enumName;
    private Element em;
    private int chance;

    private boolean keepGoing;

    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Type type,
        Element em) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = cooldown;
        this.strength = strength;
        this.type = type;
        this.turnsSinceUse = cooldown;
        this.em = em;
        /*
        this.modifiers = new EnumMap<>(Abilities.Modifier.class);
        
        for (AbilityModifier m : modifiers) 
        {
            System.out.println(m + ", " + m.getModifier());
            this.modifiers.put(m.getModifier(), m);
        }
        */
        // System.out.println(this.modifiers);
    }

/*
    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Type type,
        Element em/*,
        EnumMap<Abilities.Modifier, 
        AbilityModifier> modifiers) 
    {
        this(name, desc, cooldown, strength, type, em);
        //this.modifiers = modifiers;
    }*/



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

    /*
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
        
        RecoilModifier recoil = (RecoilModifier) modifiers.get(Abilities.Modifier.RECOIL);
        RandomModifier random = (RandomModifier) modifiers.get(Abilities.Modifier.RANDOM);
        MultiCastModifier multi = (MultiCastModifier) modifiers.get(Abilities.Modifier.MULTICAST);
        PercentageModifier percent = (PercentageModifier) modifiers.get(Abilities.Modifier.PERCENTAGE);
        GroupModifier group = (GroupModifier) modifiers.get(Abilities.Modifier.GROUP);
        
        // System.out.println(recoil + ", " + random + ", " + multi + ", " + percent);
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
    }*/


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
    /*
    public boolean hasModifier(Abilities.Modifier modifierName)
    {
        return modifiers.containsKey(modifierName);
    }
    
    public AbilityModifier getModifier(Abilities.Modifier modifierName)
    {
        return modifiers.get(modifierName);
    }
    
    public ArrayList<AbilityModifier> getModifiers(){
      return this.modifiers;
    }
    */
    protected void stopAttack()
    {
        keepGoing = false;
    }
}