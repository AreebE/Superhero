package battlesystem;

public abstract class State 
{

    private int duration;
    private String name;
    private String desc;
    
    public State(
        String name, 
        String desc,
        int duration)
    {
        this.duration = duration;
        this.name = name;
        this.desc = desc;
    }
    
    public Integer applyStatus(Entity victim)
    {
        return triggerStatus(victim);
    }

    protected abstract Integer triggerStatus(Entity victim);

    protected void reduceDuration(
        Entity victim)
    {
        if (duration > 0)
        {
            duration--;
        }
        if (duration == 0)
        {
            victim.resetState();
        }
    }

    protected int getDuration()
    {
        return duration;
    }

    public String getName()
    {
        return name;
    }

    public String getDesc()
    {
        return desc;
    }
    
    @Override 
    public String toString()
    {
        return name + ", " + desc + ": " + duration + " turns left";
    }

    public abstract State copy();
}   