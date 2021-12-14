import java.util.HashSet;

public abstract class Sheild
{
    private String name;
    private String desc;
    private HashSet<SheildList.Trigger> eventTriggers;
    private HashSet<ElementList.Name> elementTriggers;
    private int duration;
    private int uses;
    boolean nullifies;


    public Sheild(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        SheildList.Trigger[] eventTriggers,
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
    

    public Sheild(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        HashSet<SheildList.Trigger> eventTriggers,
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
        SheildList.Trigger eventTrigger, 
        Element element)
    {
        return  (
                    eventTriggers.contains(SheildList.Trigger.ALL)
                    || eventTriggers.contains(eventTrigger)
                ) 
                && 
                (
                    elementTriggers.contains(ElementList.Name.ALL) 
                    || elementTriggers.contains(element.getID())
                );
    }


    public boolean triggerSheild(Superhero target, Superhero caster)
    {
        applySheild(target, caster);
        System.out.println("Trigger " + nullifies);
        if (uses != -1) 
        {
            uses--;
        }
        if (uses == 0)
        {
            target.removeSheild(this);
        }
        System.out.println(nullifies);
        return nullifies;
    }

    protected abstract void applySheild(Superhero target, Superhero caster);

    public void passTurn(Superhero target)
    {
        if (duration != -1)
        {
            duration--;
        }

        if (duration == 0){
            target.removeSheild(this);
        }
    }

    public abstract Sheild copy();

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

    public HashSet<SheildList.Trigger> getEventTriggers()
    {
        return this.eventTriggers;
    }

    public HashSet<ElementList.Name> getElementTriggers()
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