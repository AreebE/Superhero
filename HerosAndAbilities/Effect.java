// package Game.ablilites.Effects;
// i dont think multiplayer replit and github branches work
// that's probably true
public class Effect 
{

    private final static int PIERCES_DEFENSE_INDEX = 0;
    private final static int PIERCES_SHEILD_INDEX = 1;
    private int strength;
    private EffectList.Type typeOfEffect;
    private int duration;
    private boolean permanent;
    private String name;
    private String desc;
    private Element element;
    private boolean[] pierces;


    public Effect(
        int strength, 
        EffectList.Type type, 
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
            null
        );
    }


    public Effect(
        int strength, 
        EffectList.Type type, 
        int duration, 
        boolean permanent, 
        String name, 
        String desc,
        Element element, 
        boolean... pierces) 
    {
        this.strength = strength;
        this.typeOfEffect = type;
        this.duration = duration;
        this.permanent = permanent;
        this.name = name;
        this.desc = desc;
        this.element = element;
        this.pierces = pierces;
    }


    /**
     * Will apply its effect to the given superhero
     */
    public void applyEffect(
        Superhero target) 
    {
        // System.out.println("called super");
        applyEffect(typeOfEffect, target);
        reduceDuration(target);
    }


    protected void applyEffect(
        EffectList.Type type, 
        Superhero target) 
    {
        switch (typeOfEffect) 
        {
            case ATTACK:
                // System.out.println("updating attack");
                target.addAttack(strength);
                break;
            case DEFENSE:
                target.addDefense(strength);
                break;
            case HEALTH:
                target.healHealth(strength);
                break;
            case SHEILD:
                target.addSheildHealth(strength);
                break;
            case DAMAGE:
                target.dealDamage
                (
                    strength, 
                    pierces[PIERCES_DEFENSE_INDEX], 
                    pierces[PIERCES_SHEILD_INDEX]
                );
        }
    }


    public void reduceDuration(
        Superhero target) 
    {
        duration--;
        if (duration == 0) 
        {
            removeEffect(target);
        }
    }


    protected void removeEffect(
        Superhero target) 
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
                    pierces
                );
    }


    public boolean isRemovable() 
    {
        return true;
    }


    protected EffectList.Type getType() 
    {
        return this.typeOfEffect;
    }


    protected int getStrength() 
    {
        return this.strength;
    }


    protected int getDuration() 
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
