import java.util.EnumMap;

public abstract class Ability 
{
    private String name;
    private String description;
    private int cooldown;
    private int strength;
    private int turnsSinceUse;
    private AbilityList.Type type;
    private AbilityList.Name enumName;
    private Element em;
    private boolean isRandomized;
    private int chance;
    private EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers;


    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        AbilityList.Type type,
        AbilityList.Name enumName, 
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
        this.modifiers = new EnumMap<>(AbilityList.ModifierName.class);
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
        AbilityList.Type type,
        AbilityList.Name enumName, 
        Element em,
        EnumMap<AbilityList.ModifierName, 
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
        this.type = AbilityList.Type.ATTACK;
        this.turnsSinceUse = cooldown;
        this.enumName = enumName;
        this.em = em;
        this.modifiers = new EnumMap<>(AbilityList.ModifierName.class);
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


    public AbilityList.Name getEnumName() 
    {
        return enumName;
    }


    protected EnumMap<AbilityList.ModifierName, AbilityModifier> getModifiers() 
    {
        return modifiers;
    }


    public boolean useAbility(
        Superhero target, 
        Superhero caster) 
    {
        turnsSinceUse = 0;
        RecoilModifier recoil = (RecoilModifier) modifiers.get(AbilityList.ModifierName.RECOIL);
        RandomModifier random = (RandomModifier) modifiers.get(AbilityList.ModifierName.RANDOM);
        MultiCastModifier multi = (MultiCastModifier) modifiers.get(AbilityList.ModifierName.MULTICAST);
        // System.out.println(random + ", " + recoil);
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
                castAbility(target, caster);
            }
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
}