package battlesystem;

import java.util.HashSet;

public abstract class Shield
{
    private String name;
    private String desc;
    private HashSet<Shields.Trigger> eventTriggers;
    private HashSet<Elements.Name> elementTriggers;
    private int duration;
    private int uses;
    boolean nullifies;


    public Shield(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        Shields.Trigger[] eventTriggers,
        Elements.Name[] elementTriggers)
    {
        this.name = name;
        this.desc = desc;
        this.duration = duration;
        this.nullifies = nullifies;
        this.uses = uses;
        this.eventTriggers = new HashSet<>();
        for (int i = 0; i < eventTriggers.length; i++)
        {
            this.eventTriggers.add(eventTriggers[i]);
        }
        this.elementTriggers = new HashSet<>();
        for (int i = 0; i < elementTriggers.length; i++)
        {
            this.elementTriggers.add(elementTriggers[i]);
        }
    }
    

    public Shield(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        HashSet<Shields.Trigger> eventTriggers,
        HashSet<Elements.Name> elementTriggers)
    {
        this.name = name;
        this.desc = desc;
        this.duration = duration;
        this.nullifies = nullifies;
        this.uses = uses;
        this.eventTriggers = eventTriggers;
        this.elementTriggers = elementTriggers;
    }

    public boolean wouldTrigger(
        Shields.Trigger eventTrigger, 
        Element element)
    {
        return  (
                    eventTriggers.contains(Shields.Trigger.ALL)
                    || eventTriggers.contains(eventTrigger)
                ) 
                && 
                (
                    elementTriggers.contains(Elements.Name.ALL) 
                    || elementTriggers.contains(element.getID())
                );
    }


    public boolean triggerShield(Entity target, Entity caster)
    {
        applyShield(target, caster);
        // System.out.println("Trigger " + nullifies);
        if (uses != -1) 
        {
            uses--;
        }
        if (uses == 0)
        {
            target.removeShield(this);
        }
        // System.out.println(nullifies);
        return nullifies;
    }

    protected abstract void applyShield(Entity target, Entity caster);

    public void passTurn(Entity target)
    {
        if (duration != -1)
        {
            duration--;
        }

        if (duration == 0){
            target.removeShield(this);
        }
    }

    public abstract Shield copy();

    public String toString()
    {
        return name + " " + desc + ", " + duration;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDesc()
    {
        return this.desc;
    }

    protected HashSet<Shields.Trigger> getEventTriggers()
    {
        return this.eventTriggers;
    }

    protected HashSet<Elements.Name> getElementTriggers()
    {
        return this.elementTriggers;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getUses(){
        return this.uses;
    }

    public boolean isNullifies(){
        return this.nullifies;
    }
}