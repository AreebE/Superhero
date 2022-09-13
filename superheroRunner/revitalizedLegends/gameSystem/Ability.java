package revitalizedLegends.gameSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import revitalizedLegends.modifiers.abilityMods.AbilityModifier;
import revitalizedLegends.modifiers.abilityMods.ModifierLoader;

/**
 * A class for the ability so heros can do things.
 *
 */
public abstract class Ability 
    implements Saveable
{
	
	
	
	public static final int NECESSARY_PRIORITY  =   -1;
	public static final int HIT_PRIORITY 		= 	0;
	public static final int ADJUSTMENT_PRIORITY	= 	1;
	public static final int ATTACK_PRIORITY 	=  	2;
	public static final int SUCCESS_PRIORITY 	=   3;
	
    
    /**
     * This is just a general category for each ability
     */
    public static enum Category
    {
        ATTACK, 
        DEFENSE, 
        SUPPORT
    }

    public static final int MISS = -1;

    public static final String TYPE_KEY = "type";
    private static final String NAME_KEY = "name";
    private static final String DESC_KEY = "desc";
    private static final String COOLDOWN_KEY = "cooldown";
    private static final String STRENGTH_KEY = "strength";
    private static final String ELEMENT_KEY = "element";
    private static final String MODIFIERS_KEY = "modifiers";
    
    public String name;
    private String description;
    private int cooldown;
    private int strength;
    private Ability.Category category;
    private Element em;
    private ArrayList<AbilityModifier> modifiers;

    private int turnsSinceUse;
    private int additionalStrength;
    private boolean haveCastedAbility;
    public transient static final int MAX_CHANCE = 256;
    
    private boolean keepGoing;
	private boolean willSetCooldown;
    
    public Ability(JSONObject json)
    {
    	name = json.getString(NAME_KEY);
    	description = json.getString(DESC_KEY);
    	cooldown = json.getInt(COOLDOWN_KEY);
    	strength = json.getInt(STRENGTH_KEY);
    	em = Elements.getElement(json.getString(ELEMENT_KEY));
//    	System.out.println(name + ", " + em);
    	modifiers = new ArrayList<>();
    	JSONArray jsonModifiers = json.getJSONArray(MODIFIERS_KEY);
    	for (int i = 0; i < jsonModifiers.length(); i++)
    	{
    		 modifiers.add(i, ModifierLoader.loadModifier(jsonModifiers.getJSONObject(i)));
    	}
		 Collections.sort(this.modifiers, new Comparator<AbilityModifier>()
	     {
	
			@Override
			public int compare(AbilityModifier o1, AbilityModifier o2) {
				// TODO Auto-generated method stub
				return o1.getPriority() - o2.getPriority();
			}
	     	
	     });
    }
    /**
     * A constructor for when it is given an ability
     * @param tocopy the ability to copy from.
     */
    public Ability(Ability tocopy){
      this.name = tocopy.getName();
      this.description = tocopy.getDescription();
      this.cooldown = tocopy.getCooldown();
      this.strength = tocopy.getStrength();
      this.category = tocopy.getType();
      this.turnsSinceUse = tocopy.getCooldown();
      this.em = tocopy.getElement();
      this.modifiers = tocopy.getModifiers();
      this.additionalStrength = 0;
    }

    /**
     * A more descriptive constructor.
     * @param name the name of the ability
     * @param desc the description of how it works.
     * @param cooldown How much cooldown it has
     * @param strength Its base strength
     * @param type The type it is
     * @param em the element it has
     * @param modifiers What modifiers it has.
     */
    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Ability.Category type,
        Element em,
        AbilityModifier... modifiers) 
    {
        this.name = name;
        this.description = desc;
        this.cooldown = cooldown;
        this.strength = strength;
        this.category = type;
        this.turnsSinceUse = cooldown;
        this.em = em;
        this.modifiers = new ArrayList<>();
        this.additionalStrength = 0;
        
        for (AbilityModifier m : modifiers) 
        {
            // System.out.println(m + ", " + m.getModifier());
            this.modifiers.add(m);
        }
        Collections.sort(this.modifiers, new Comparator<AbilityModifier>()
        {

			@Override
			public int compare(AbilityModifier o1, AbilityModifier o2) {
				// TODO Auto-generated method stub
				return o1.getPriority() - o2.getPriority();
			}
        	
        });
        // System.out.println(this.modifiers);
    }


    /**
     * An overloaded constructor 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown the cooldown after use
     * @param strength the strength of the attck
     * @param type the type of ability 
     * @param em its element
     * @param modifiers what modifiers it has (this is an array list)
     */
    public Ability(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Ability.Category type,
        Element em,
        ArrayList<AbilityModifier> modifiers) 
    {
        this(name, desc, cooldown, strength, type, em);
        this.modifiers = modifiers;
    }


    /**
     * get name
     * @return Get the name of this ability
     */
    public String getName() 
    {
        return name;
    }


    /**
     * get description
     * @return Get the description of this ability.
     */
    public String getDescription() 
    {
        return description;
    }

    /**
     * get cooldown
     * @return Get how many turns this will be off for after use.
     */
    public int getCooldown() 
    {
        return cooldown;
    }

    /**
     * get the strength
     * @return the base strength of this ability
     */
    public int getStrength() 
    {
        return strength;
    }

    public int getAdditionalStrength()
    {
    	return additionalStrength;
    }
    /**
     * get element
     * @return the element this ability belongs to.
     */
    public Element getElement() 
    {
        return em;
    }

    /**
     * Use the ability. This will also trigger the modifiers that this ability has.
     * First random to see if the move hits,
     * Then recoil for some base damage,
     * Then multitime to see how many times this should execite,
     * then percentage modifier to update the strength,
     * and group modifier to attack all others in the group. It can be interrupted though.
     * 
     * @param targets the entity/entities to target
     * @param caster the caster of this spell
     * @param g 	the game class, which can make calls to get certain things.
     * @param log the log to store actions.
     */
    public void useAbility(
        List<Entity> targets, 
        Entity caster,
        Game g,
        BattleLog log) 
    {
    	haveCastedAbility = false;
    	willSetCooldown = true;
        keepGoing = true;
        Object[] contents = new Object[]{caster.getName(), targets.get(0).getName(), name, targets.size() - 1};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ABILITY, contents));
        caster.performAction(log, g);

        
       
        // System.out.println(willSetCooldown);
//        System.out.println("using ability " + name);
//        System.out.println(recoil + ", " + random + ", " + multi + ", " + percent + ", " + group);
        boolean shouldContinue = true;
        for (int i = 0; i < modifiers.size() && shouldContinue; i++)
        {
//        	System.out.println("checking modifier " + modifiers.get(i).getClass().toString());
        	shouldContinue = modifiers.get(i).triggerModifier(targets, caster, this, g, log);

        }
        if (!haveCastedAbility)
        {
//        	System.out.println("casting ability " + name);
        	castAbility(targets.get(0), caster, g, log);
        }
        if (willSetCooldown)
        {
            turnsSinceUse -= cooldown;        	
        }
        additionalStrength = 0;
    }

   

    /**
     * Cast the ability. Do not call this one: call useAbility instead. This is meant to be overriden by other classes.
     * @param target the target
     * @param caster the caster
     * @param otherTargets the other targets to hit
     * @param allPlayers all other players
     * @param log the battlelog to record actions
     */
    public void castAbility
    (
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log
    )
    {
    	haveCastedAbility = true;
    	performCast(target, caster, g, log);
    }
    
    protected abstract void performCast(
    		Entity target,
    		Entity caster,
    		Game g,
    		BattleLog log
    		);
    /**
     * The string representation of the ability. Starts with name, then description, then cooldown
     */
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


    /**
     * Return if they are equal based on the name
     * @return if they really are equal
     */
    @Override
    public boolean equals(
        Object other) 
    {
        Ability otherAbility = (Ability) other;
        return this.name.equals(otherAbility.getName());
    }


    /**
     * update the turns used since the ability was cast.
     */
    public void reduceCooldown() 
    {
        if (turnsSinceUse < cooldown) 
        {
            turnsSinceUse++;
        }
    }


    /**
     * Only returns true if the turns since it was used is the same as its cooldown.
     * @return if this ability can be used
     */
    public boolean ableToUseAbility() 
    {
        // System.out.println("turns since use for " + name + ": " + turnsSinceUse);
        return turnsSinceUse == cooldown;
    }


    /**
     * Get how many turns this will be on cooldown for.
     * @return how many turns are needed.
     */
    public int getTurnsNeeded() 
    {
        return cooldown - turnsSinceUse;
    }
    
    /**
     * 
     * @return the type of this ability
     */
    public Category getType(){
      return this.category;
    }



    /**
     * A method for copying this ability
     * @return the copy of this.
     */
    public abstract Ability copy();
    
    /**
     * get all modifiers
     * @return all modifiers
     */
    public ArrayList<AbilityModifier> getModifiers(){
      return this.modifiers;
    }
    
    /**
     * A method used to notify the program when to stop attacking
     */
    protected void stopAttack()
    {
        keepGoing = false;
    }
    
    /**
     * 
     */
    public boolean continueAttacking()
    {
    	return keepGoing;
    }
    
    public void doNotSetCooldown()
    {
    	this.willSetCooldown = false;
    }

    public void doNotCast()
    {
        this.haveCastedAbility = true;
    }
    
    /**
     * Get how many people this can target, though return 1 at most, but -1 if hitting eve ryone.
     * @return the number of targets.
     */
    public int getTargetAmount()
    {
    	
    	try 
    	{
    		for (int i = modifiers.size() - 1; i >= 0; i++)
    		{
    			if (modifiers.get(i).getClass().equals(GroupModifier.class))
    			{
    	    		return ((GroupModifier) modifiers.get(i)).getLimit();
    			}
    			else if (modifiers.get(i).getPriority() < Ability.ATTACK_PRIORITY)
    			{
    				return 1;
    			}
    		}
    	}
    	catch (NullPointerException|ClassCastException|IndexOutOfBoundsException npe)
    	{
    		return 1;
    	}
		return 1;
    }
    
    protected void setCategory(Category c)
    {
    	this.category = c;
    }

    public void adjustAdditionalStrength(int number, boolean isPercentage)
    {
//    	System.out.println(number + "," + isPercentage);
    	additionalStrength += (isPercentage)? strength * (number / 100 - 1): number; 
//    	System.out.println(additionalStrength);
    }
    
    /**
     * Convert the ability to a json file
     * @return the json object.
     */
    public JSONObject toJson()
    {
    	JSONObject ability = new JSONObject();
    	ability.put(COOLDOWN_KEY, cooldown);
    	ability.put(DESC_KEY, description);
    	ability.put(ELEMENT_KEY, em.getName().toLowerCase());
    	ability.put(NAME_KEY, name);
    	ability.put(STRENGTH_KEY, strength);
    	
    	JSONArray jsonModifiers = new JSONArray();
    	for (int i = 0; i < modifiers.size(); i++)
    	{
    		jsonModifiers.put(modifiers.get(i).toJson());
    	}
    	ability.put(MODIFIERS_KEY, jsonModifiers);
    	return ability;
    }
}