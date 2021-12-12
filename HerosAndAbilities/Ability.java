import java.util.EnumMap;

public abstract class Ability 
{
    private String name;
    private String description;
    private int cooldown;
    private int strength;
    private int turnsSinceUse;
    private AbilityList.AbilityType type;
    private AbilityList.AbilityNames enumName;
    private Element em;
    private boolean isRandomized;
    private int chance;
    private EnumMap<AbilityList.AbilityModifierNames, AbilityModifier> modifiers;


    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.AbilityType type,
        AbilityList.AbilityNames enumName, 
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
        this.modifiers = new EnumMap<>(AbilityList.AbilityModifierNames.class);
        for (AbilityModifier m : modifiers) 
        {
            // System.out.println(m + ", " + m.getModifier());
            this.modifiers.put(m.getModifier(), m);
        }
        System.out.println(this.modifiers);
    }


    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.AbilityType type,
        AbilityList.AbilityNames enumName, 
        Element em,
        EnumMap<AbilityList.AbilityModifierNames, 
        AbilityModifier> modifiers) 
    {
        this(name, desc, cooldown, strength, type, enumName, em);
        this.modifiers = modifiers;
    }


    // alt const. for customMaker
    // Note: AbilityType is only in the ability list
    public Ability(
        String name, 
        String desc) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = 2;
        this.strength = 0;
        this.type = AbilityList.AbilityType.ATTACK;
        this.turnsSinceUse = cooldown;
        this.enumName = enumName;
        this.em = em;
        this.modifiers = new EnumMap<>(AbilityList.AbilityModifierNames.class);
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


    public AbilityList.AbilityNames getEnumName() 
    {
        return enumName;
    }


    protected EnumMap<AbilityList.AbilityModifierNames, AbilityModifier> getModifiers() 
    {
        return modifiers;
    }


    public boolean useAbility(
        Superhero target, 
        Superhero caster) 
    {
        turnsSinceUse = 0;
        RecoilModifier recoil = (RecoilModifier) modifiers.get(
            AbilityList.AbilityModifierNames.RECOIL
        );

        RandomModifier random = (RandomModifier) modifiers.get(
            AbilityList.AbilityModifierNames.RANDOM
        );
        
        // System.out.println(random + ", " + recoil);
        if (random == null 
            ||  random.triggerModifier(target, caster)) 
        {
            if (recoil != null) 
            {
                recoil.triggerModifier(target, caster);
            }
            castAbility(target, caster);
            return true;
        }
        return false;
    }


    protected abstract void castAbility
    (
        Superhero target, 
        Superhero caster
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
            output.append("It is a passive ability.");
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


    public abstract Ability copyAbility();
}