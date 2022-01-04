package battlesystem;

import java.util.EnumMap;
import java.util.List;

public abstract class Ability 
{
    private String name;
    private String description;
    private int cooldown;
    private int strength;
    private int turnsSinceUse;
    private Abilities.Type type;
    private Abilities.Name enumName;
    private Element em;
    private boolean isRandomized;
    private int chance;
    private EnumMap<Abilities.Modifier, AbilityModifier> modifiers;


    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Type type,
        Abilities.Name enumName, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = cooldown;
        this.strength = strength;
        this.type = type;
        this.turnsSinceUse = cooldown;
        this.enumName = enumName;
        this.em = em;
        this.modifiers = new EnumMap<>(Abilities.Modifier.class);
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
        Abilities.Type type,
        Abilities.Name enumName, 
        Element em,
        EnumMap<Abilities.Modifier, 
        AbilityModifier> modifiers) 
    {
        this(name, desc, cooldown, strength, type, enumName, em);
        this.modifiers = modifiers;
    }


    // alt const. for customMaker
    // Note: Type is only in the ability list
    public Ability(
        String name, 
        String desc) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = 2;
        this.strength = 0;
        this.type = Abilities.Type.ATTACK;
        this.turnsSinceUse = cooldown;
        this.enumName = enumName;
        this.em = em;
        this.modifiers = new EnumMap<>(Abilities.Modifier.class);
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


    public Abilities.Name getEnumName() 
    {
        return enumName;
    }


    protected EnumMap<Abilities.Modifier, AbilityModifier> getModifiers() 
    {
        return modifiers;
    }


    public boolean useAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        turnsSinceUse = 0;
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
                boolean keepGoing = castAbility(target, caster, otherTargets, allPlayers);
                if (group != null)
                {
                    strength *= group.triggerModifier(target, caster);
                    // System.out.println(strength);
                    for (int j = 0; j < group.getLimit(); j++)
                    {
                        castAbility(otherTargets.get(j), caster, otherTargets, allPlayers);
                    }
                }
                strength = baseStrength;
                if (!keepGoing){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    protected abstract boolean castAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers
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

    public boolean hasModifier(Abilities.Modifier modifierName)
    {
        return modifiers.containsKey(modifierName);
    }

    public AbilityModifier getModifier(Abilities.Modifier modifierName)
    {
        return modifiers.get(modifierName);
    }
}