package battlesystem;

import java.util.HashSet;

import battlesystem.databaseImpls.Elements;

/**
 * A class for entities that does something when a trigger is done.
 *  
 */
public abstract class Shield
{
    private String name;
    private String desc;
    private HashSet<Shield.Trigger> eventTriggers;
    private HashSet<Elements.Name> elementTriggers;
    private int duration;
    private int uses;
    boolean nullifies;

    public static enum Trigger {
        ANY_ACTION, // When making a move
        ATTACK, // When the person is attacked
        ATTACKING, // When attacking another player
        CLEANSE, // When cleansing oneself
        DEATH, // Upon this player's death
        DEFENSE, // Upon using a defense skill
        // EFFECT_APPLIED, // Upon being given an effect
        SHIELD_BREAK, // Upon shield breaking
        SPAWN, // Upon spawning another thing
        STATE_CHANGE, // Upon changing state
        GIVE_EFFECT, // Upon giving an effect
        PASS, // Upon passing a turn
        ALL // When any of the above triggers (Ex. trigerring when taking an action and passing.)
    }
    
    /**
     * A basic constructor for shield
     * @param name the name of the shield
     * @param desc a description of how it works
     * @param duration how long it lasts
     * @param nullifies if it nullifies upcoming attacks
     * @param uses how many uses it has
     * @param eventTriggers what will trigger the shield
     * @param elementTriggers what elements will trigger the shield.
     */
    public Shield(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        Shield.Trigger[] eventTriggers,
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
    
    /**
     * An overloaded constructor used for copying.
     * 
     * @param name the name of the shield
     * @param desc the description
     * @param duration how long it lasts
     * @param nullifies if it nullifies upcoming attacks
     * @param uses the amount of times it can be used
     * @param eventTriggers all event triggers
     * @param elementTriggers all elemental triggers
     */
    public Shield(
        String name,
        String desc,
        int duration, 
        boolean nullifies,
        int uses,
        HashSet<Shield.Trigger> eventTriggers,
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

    /**
     * If this would be able to trigger the shield
     * @param eventTrigger the type of event
     * @param element the type of element
     * @return if both conditions are met.
     */
    public boolean wouldTrigger(
        Shield.Trigger eventTrigger, 
        Element element)
    {
        return  (
                    eventTriggers.contains(Shield.Trigger.ALL)
                    || eventTriggers.contains(eventTrigger)
                ) 
                && 
                (
                    elementTriggers.contains(Elements.Name.ALL) 
                    || elementTriggers.contains(element.getID())
                );
    }


    /** 
     * Triggering the shield
     * @param target the person who is targetted by the shield
     * @param caster the person who has the shield
     * @param log the battle log to record what was done
     * @return if upcoming attacks should be nullified.
     */
    public boolean triggerShield(Entity target, Entity caster, BattleLog log)
    {
        applyShield(target, caster, log);
        // System.out.println("Trigger " + nullifies);
        if (uses != -1) 
        {
            uses--;
        }
        if (uses == 0)
        {
            removeShield(target, caster, log);
        }
        // System.out.println(nullifies);
        return nullifies;
    }

    /** 
     * An apply shield method, used to apply effects.
     * @param target the person targetted by shield
     * @param caster the holder of the shield
     * @param log a log for recording actions
     */
    protected abstract void applyShield(Entity target, Entity caster, BattleLog log);

    /**
     * Reduce the duration, but remove once it reaches 0.
     * @param target the entity to target
     * @param log the battlelog to record the removal
     */
    public void passTurn(
        Entity target, 
        BattleLog log)
    {
        if (duration != -1)
        {
            duration--;
        }

        if (duration == 0){
            removeShield(target, null, log);
        }
    }

    /**
     * A copy method
     * @return a shield that is identical to the one it was copied from
     */
    public abstract Shield copy();

    /**
     * Remove the shield from the entity
     * @param target (hopefully) who has the shield
     * @param caster (if target is null) the person who has the shield
     * @param log records the removal of ashield
     */
    public void removeShield(
        Entity target,
        Entity caster,
        BattleLog log
    )
    {
        Object[] contents = new Object[]{target.getName(), new String[]{name}};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.SHIELD_LOST, contents));
        if (target != null)
        {
            target.removeShield(this);
        }
        else 
        {
            caster.removeShield(this);
        }
    }

    /**
     * A string representation of the shield with its name, description, duration, and uses
     */
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" - ")
                .append(desc)
                .append(": ");
        if (duration == -1)
        {
            builder.append("Lasts for an infinite time. ");
        }
        else 
        {
            builder.append("Lasts for ")
                    .append(duration)
                    .append(" more turns. ");
        }

        if (uses == -1)
        {
            builder.append("Can be triggered forever. ");
        }
        else 
        {
            builder.append("Can be triggered up to ")
                    .append(uses)
                    .append(" more times.");
        }
        return builder.toString();
    }

    /**
     * get name
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * get desc
     * @return the description
     */
    public String getDesc()
    {
        return this.desc;
    }

    /**
     * get event triggers
     * @return the event triggers
     */
    protected HashSet<Shield.Trigger> getEventTriggers()
    {
        return this.eventTriggers;
    }

    /**
     * get element triggers
     * @return the elemental triggers
     */
    protected HashSet<Elements.Name> getElementTriggers()
    {
        return this.elementTriggers;
    }

    /**
     * get the duration
     * @return the duration remaining on this shield
     */
    public int getDuration(){
        return this.duration;
    }

    /**
     * the number of uses left
     * @return how many uses are left
     */
    public int getUses(){
        return this.uses;
    }

    /**
     * if this nullifies attacks
     * @return if it nullifies attacks
     */
    public boolean isNullifies(){
        return this.nullifies;
    }
   
}