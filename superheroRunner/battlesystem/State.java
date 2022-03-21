package battlesystem;

import org.json.JSONObject;

/**
 * States will affect how many actions a player is allowed to have
 *
 */
public abstract class State 
{

    private int duration;
    private String name;
    private String desc;
    
    /**
     * Basic constructor
     * @param name the name of the state
     * @param desc the description of the state
     * @param duration how long it lasts normally, only when <1.
     */
    public State(
        String name, 
        String desc,
        int duration)
    {
        this.duration = duration;
        this.name = name;
        this.desc = desc;
    }
    
    /** 
     * apply the state to the person
     * @param victim the entity that has this state
     * @return how many actions this person is allowed
     */
    public Integer applyStatus(Entity victim)
    {
        return triggerStatus(victim);
    }

    /**
     * apply the state
     * 
     * @param victim the entity that has the state
     * 
     * @return how many actions the entity is allowed.
     */
    protected abstract Integer triggerStatus(Entity victim);

    /**
     * reduce the duration of the state
     * 
     * @param victim the entity that has the state. Remove if the duration equals 0.
     */
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

    /**
     * the current duration of the state
     * @return the remanining number of turns
     */
    protected int getDuration()
    {
        return duration;
    }

    /**
     * the name
     * @return the name of the state
     */
    public String getName()
    {
        return name;
    }

    /**
     * the desc
     * @return the description of this state
     */
    public String getDesc()
    {
        return desc;
    }
    
    /**
     * the string representation of this state: name, then desc, then number of turns left
     */
    @Override 
    public String toString()
    {
        return name + ", " + desc + ": " + duration + " turns left";
    }

    /**
     * A copy of this state
     * @return the copy of this state.
     */
    public abstract State copy();
    public abstract JSONObject toJson();
}   