package gameSystem;

import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A class for entities that does something when a trigger is done.
 *  
 */
public abstract class Shield
    implements Saveable
{
	public static final String TYPE_KEY = "type";
	private static final String NAME_KEY = "name";
	private static final String DESC_KEY = "desc";
	private static final String EVENT_TRIGGER_KEYS = "event triggers";
	private static final String ELEMENT_TRIGGER_KEYS = "element triggers";
	private static final String DURATION_KEY = "duration";
	private static final String USES_KEY = "uses";
	private static final String NULLIFIES_KEY = "nullifies";
	
    private String name;
    private String desc;
    private HashSet<Shield.Trigger> eventTriggers;
    private HashSet<Elements.Name> elementTriggers;
    private int duration;
    private int uses;
    boolean nullifies;

    public static enum Trigger {
        ANY_ACTION("any"), // When making a move
        BEFORE_ATTACK("before attacked"), // before the person is attacked.
        ATTACK("attacked"), // When the person is attacked
        ATTACKING("attacking"), // When attacking another player
        CLEANSE("cleanse"), // When cleansing oneself
        DEATH("death"), // Upon this player's death
        EFFECT_APPLIED("effect applied"), // Upon being given an effect
        SHIELD_BREAK("shieldBreak"), // Upon shield breaking
        SHIELD_ADDED("shieldAdded"), // Upon adding a shield
        SPAWN("spawn"), // Upon spawning another thing
        STATE_CHANGE("changeState"), // Upon changing state
        GIVE_EFFECT("giveEffect"), // Upon giving an effect
        PASS("pass"), // Upon passing a turn
        ALL("all") ;// When any of the above triggers (Ex. trigerring when taking an action and passing.)
    
        private final String name;
        Trigger(String name)
        {
        	this.name = name;
        }
        public static Trigger getTrigger(String name)
        {
        	switch(name)
        	{
        		case "any":
        		default:
        			return ANY_ACTION;
        		case "attacked":
        			return ATTACK;
        		case "attacking":
        			return ATTACKING;
        		case "cleanse":
        			return CLEANSE;
        		case "death":
        			return DEATH;
                case "effect applied":
                    return EFFECT_APPLIED;
        		case "shieldAdded":
        			return SHIELD_ADDED;
        		case "shieldBreak":
        			return SHIELD_BREAK;
        		case "spawn":
        			return SPAWN;
        		case "changeState":
        			return STATE_CHANGE;
        		case "giveEffect":
        			return GIVE_EFFECT;
        		case "pass":
        			return PASS;
                case "before attack":
                    return BEFORE_ATTACK;
        		case "all":
        			return ALL;
        	}
        }
    }
    
    public Shield(JSONObject json)
    {
    	name = json.getString(NAME_KEY);
        desc = json.getString(DESC_KEY);
        
        eventTriggers = new HashSet<Shield.Trigger>();
        JSONArray events = json.getJSONArray(EVENT_TRIGGER_KEYS);
        for (int i = 0; i < events.length(); i++)
        {
        	eventTriggers.add(Trigger.getTrigger(events.getString(i)));
        }
//        System.out.println(name);
//        System.out.println(eventTriggers.toString());
        elementTriggers = new HashSet<Elements.Name>();
        JSONArray elements = json.getJSONArray(ELEMENT_TRIGGER_KEYS);
        for (int i = 0; i < elements.length(); i++)
        {
        	elementTriggers.add(Elements.getElement(elements.getString(i)).getID());
        }
//        System.out.println(elementTriggers.toString());
        
        
        duration = json.getInt(DURATION_KEY);
        uses = json.getInt(USES_KEY);
        nullifies = json.getBoolean(NULLIFIES_KEY);
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
    public boolean triggerShield(Entity target, Entity caster, Game g, BattleLog log)
    {
        applyShield(target, caster, g, log);
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
    protected abstract void applyShield(Entity target, Entity caster, Game g, BattleLog log);

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
    

    public JSONObject toJson()
    {
    	JSONObject shield = new JSONObject();
    	shield.put(NAME_KEY, name);
    	shield.put(DESC_KEY, desc);
    	shield.put(DURATION_KEY, duration);
    	shield.put(USES_KEY, uses);
    	shield.put(NULLIFIES_KEY, nullifies);
    	
    	JSONArray jsonShieldTriggers = new JSONArray();
    	Iterator<Trigger> shieldTriggers = eventTriggers.iterator();
    	while (shieldTriggers.hasNext())
    	{
    		jsonShieldTriggers.put(shieldTriggers.next().name);
    	}
    	shield.put(EVENT_TRIGGER_KEYS, jsonShieldTriggers);
    	
    	JSONArray jsonElementTriggers = new JSONArray();
    	Iterator<Elements.Name> elementTriggers = this.elementTriggers.iterator();
    	while (elementTriggers.hasNext())
    	{
    		jsonElementTriggers.put(Elements.getElement(elementTriggers.next()).getName().toLowerCase());
    	}
    	shield.put(ELEMENT_TRIGGER_KEYS, jsonElementTriggers);
    	
    	return shield;
    }

    
}