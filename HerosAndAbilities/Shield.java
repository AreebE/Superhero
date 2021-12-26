import java.util.HashSet;

public abstract class Shield
{
    private String name;
    private String desc;
    private HashSet<ShieldList.Trigger> eventTriggers;
    private HashSet<ElementList.Name> elementTriggers;
    private int duration;
    private int uses;
    boolean nullifies;


    public Shield(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        ShieldList.Trigger[] eventTriggers,
        ElementList.Name[] elementTriggers)
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
        HashSet<ShieldList.Trigger> eventTriggers,
        HashSet<ElementList.Name> elementTriggers)
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
        ShieldList.Trigger eventTrigger, 
        Element element)
    {
        return  (
                    eventTriggers.contains(ShieldList.Trigger.ALL)
                    || eventTriggers.contains(eventTrigger)
                ) 
                && 
                (
                    elementTriggers.contains(ElementList.Name.ALL) 
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
        System.out.println(nullifies);
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

    protected HashSet<ShieldList.Trigger> getEventTriggers()
    {
        return this.eventTriggers;
    }

    protected HashSet<ElementList.Name> getElementTriggers()
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