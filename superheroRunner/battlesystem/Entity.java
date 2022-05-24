package battlesystem;

import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battlesystem.objectMapImpls.AbilityManager;
import battlesystem.objectMapImpls.Effects;
import battlesystem.objectMapImpls.Shields;
import battlesystem.objectMapImpls.States;
import battlesystem.objectMapImpls.Effects.Name;

import java.util.Arrays;
/**
 * 
 * @author Areeb Emran
 * 
 * This class is meant to represent any object, such as a superhero.
 */
public class Entity implements Comparable<Entity>
{
    // free will between 1 and 20;
    private transient Entity creator;
    private Effect mostRecentEffect;
    private String name;
    private int speed;
    private transient ArrayList<Ability> abilities;
    private transient ArrayList<Effect> effects;
    private transient ArrayList<Shield> shields;
    private transient State state;
    private transient State defaultState;
    private int health;
    private int maxHealth;
    private int shieldHealth;
    private int baseAttack;
    private int baseDefense;
    private int teamID;
    private static transient Terrain t;


    /**
     * 
     * @author Areeb Emran
     * 
     * This is just an enum for getting a specific type of statistic
     */
    public static enum Statistic
    {
        SPEED("speed"),
        MAX_HEALTH("max health"),
        HEALTH("health"),
        BASE_ATTACK("attack"),
        BASE_DEFENSE("defense"),
        SHIELD("shield");
    	
    	
    	public final String name;
    	
    	Statistic(String name)
    	{
    		this.name = name;
    	}
    	
    	public static Statistic getStatistic(String name)
    	{
    		switch (name)
    		{
    			case "speed":
    				return SPEED;
    			case "max health":
    				return MAX_HEALTH;
    			case "health":
    				return HEALTH;
    			case "attack":
    				return BASE_ATTACK;
    			case "defense":
    				return BASE_DEFENSE;
    			case "shield":
    			default:
    				return SHIELD;
    		}
    	}
    }
    
    //alt constructor using EII
    /**
     * A different constructor that uses the Entity info item.
     * 
     * @param in the entity info item used.
     */
    public Entity(EntityInfoItem in){
      this.name = in.name;
      this.speed = in.speed;
      this.maxHealth = in.maxHealth;
      this.health = maxHealth;
      this.shieldHealth = in.shieldHealth;
      this.state = States.get(States.Name.NORMAL);
      this.baseAttack = 0;
      this.baseDefense = 0;
      this.creator = creator;
      setStuffFromEII(in);

      /*
      private String name;
      private int speed;
      private ArrayList<Abilities.Name> abilities;
      private ArrayList<Effects.Name> effects;
      private ArrayList<Shields.Name> shields;
      private int maxHealth;
      private int shieldHealth;
      */
    }

    /**
     * The most basic version of creating an entity.
     * 
     * @param name The name of the super
     * @param speed The starting speed of the superhero.
     * @param health The health & max health of this superhero.
     * @param shieldHealth The starting amount of shield this character has.
     * @param defaultState The default state this should be in. Check the state class for more information.
     * @param creator The entity that created this one.
     */
    public Entity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
        State defaultState,
        Entity creator) 
    {
        this.name = name;
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.shieldHealth = shieldHealth;
        this.abilities = new ArrayList<>();
        this.effects = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.defaultState = defaultState;
        this.state = defaultState;
        this.baseAttack = 0;
        this.baseDefense = 0;
        this.creator = creator;
        // ArrayList<String> abstogive = new ArrayList<String>();
        // abstogive.add("pass turn");
//        AbilityManager.giveAbilities(this, abstogive);
    }


    /**
     * Give this character some abilities, effects, and shields they start with.
     * 
     * @param in The info item that contains all of the names.
     */
    private void setStuffFromEII(EntityInfoItem in){
      this.abilities = new ArrayList<>();
      this.effects = new ArrayList<>();
      this.shields = new ArrayList<>();
      for(String t: in.abilities){
        this.abilities.add(AbilityManager.getAbility(t));
      }
        
//            for(String t: in.effects){
//                this.effects.add(Effects.getEffect(t));
//            }
        
      
        
//            for( t: in.shields){
//                this.shields.add(Shields.getShield(t));
//            }
        

    }
    


//
//    public EntityInfoItem toEII(){
//      try{
//        return new EntityInfoItem(name,speed,getAbNames(),getEffectsEnums(),getStartingShieldEnums(),maxHealth,shieldHealth);
//      }catch(Exception e){
//        System.out.println("ERROR CREATING EII");
//        e.printStackTrace();
//      }
//      return null;
//    }
//
//    private ArrayList<String> getAbNames() throws Exception{
//      ArrayList<String> out = new ArrayList<>();
//      for(Ability t:abilities){
//        out.add(t.getName());
//      }
//      return out;
//    }
//    private ArrayList<Effects.Name> getEffectsEnums() throws Exception{
//      ArrayList<Effects.Name> out = new ArrayList<>();
//      for(Effect t:effects){
//          System.out.println(t.getEnumName());
//        out.add(t.getEnumName());
//      }
//      return out;
//      
//    }
//    private ArrayList<Shields.Name> getStartingShieldEnums() throws Exception{
//      ArrayList<Shields.Name> out = new ArrayList<>();
//      for(Shield t:shields){
//        out.add(t.getEnumName());
//      }
//      return out;
//    }
    


  
    /**
     * The method for getting the creator.
     * @return the creator.
     */
    protected Entity getCreator()
    {
        return this.creator;
    }

    /**
     * Provides information on what the Entity does
     * 
     * It first lists the will / speed, then the abilities, then state, then effects, then shields, then all basic stats.
     */
    @Override
    public String toString() 
    {
        StringBuilder EntityString = new StringBuilder(name).append(" - will of ")
                                                                .append(speed)
                                                                .append(". \n")
                                                                .append("\u001B[35m");
        if (abilities.size() == 0) 
        {
            EntityString.append("There are no abilities.\n");
        } 
        else 
        {
            EntityString.append("The abilities are: \n");
            for (int i = 0; i < abilities.size(); i++) 
            {
                Ability a = abilities.get(i);
                EntityString.append("*    ")
                                .append(a.toString());
                if (!a.ableToUseAbility()) 
                {
                    EntityString.append(" (on cooldown. needs ")
                                    .append(a.getTurnsNeeded())
                                    .append(" more turns)");
                }
                EntityString.append("\n");
            }
        }
        // moved it down
        if (state != null)
        {
            EntityString.append("The state is: ")
                    .append(state.toString())
                    .append("\n")
                    .append("\u001B[31m");
        }
        
        if (effects != null)
        {
            if (effects.size() == 0) 
            {
                EntityString.append("No Effects/ deEffects applied.\n");
            } else 
            {
                EntityString.append("The Effects/deEffects are:\n");
                for (int i = 0; i < effects.size(); i++) 
                {
                    EntityString.append("* ")
                                    .append(effects.get(i))
                                    .append("\n");
                }
            }
        }

        EntityString.append("\u001B[35m");
        if (shields != null)
        {
            if (shields.size() == 0) 
            {
                EntityString.append("No shields applied.\n");
            } else 
            {
                EntityString.append("The Shields are:\n");
                for (int i = 0; i < shields.size(); i++) 
                {
                    EntityString.append("* ")
                                    .append(shields.get(i))
                                    .append("\n");
                }
            }
        }
        EntityString.append("\u001B[34m")
                        .append("* health - ")
                        .append(health)
                        .append("/")
                        .append(maxHealth)
                        .append("\n")
                        .append("* shield - ")
                        .append(shieldHealth)
                        .append("\n")
                        .append("* base attack - ")
                        .append(baseAttack)
                        .append("\n")
                        .append("* base defense - ")
                        .append(baseDefense)
                        .append("\n")
                        .append("\u001B[0m")
                        .append("\n");
        return EntityString.toString();
    }


    /**
     * I'm unsure why this method exists.
     * 
     * @return a basic string with name and the speed?
     */
    
    /*
     * Writes this Entity to a Single Line so that its usable by Fileiothing also
     * should health/shieldHealth be persistent? // not entirely sure, maybe for
     * only the base health/ base shield? as in all heros get 100 health and x
     * shield at the beggingn of each round // for now, that could work. but for
     * more customizability, we may remove that set health later on
     */
    public String ToSaveable() 
    {
        String out = "";
        out = (out + name + speed);
        return out;
    }


    /**
     * Method for getting the name
     * 
     * @return the name of this entity
     */
    public String getName() 
    {
        return this.name;
    }
    //

    /*
     * Methods involving a player's ability/abilities
     */
    /**
     * Add an ability to this player.
     * 
     * @param newAbility the new ability to add.
     */
    public void addAbility(
        Ability newAbility) 
    {
        abilities.add(newAbility);
    }
    
   

    /**
     * Get all of the abilities of this player.
     * 
     * @return the abilities of this player.
     */
    public ArrayList<Ability> getAbilities() 
    {
        return this.abilities;
    }

    /**
     * Get a specific ability when getting an ability. 
     * 
     * @param name the name of the ability.
     * @return an ability if this has it: otherwise, it returns null.
     */
    public Ability getAbility(
        String name) 
    {
        
        for (Ability a : abilities) 
        {
            name = name.replace('_', ' ');
            if (name.toLowerCase().equals(a.getName().toLowerCase())) 
            {
                if (a.ableToUseAbility()) 
                {
                    return a;
                } 
                else 
                {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Does this have the ability? 
     * 
     * @param ability the ability to look out for.
     * @return if it has this ability.
     */
    public boolean hasAbility(
        Ability ability) 
    {
        return abilities.contains(ability);
    }
    
    /**
     * Is this specific ability a group ability?
     * 
     * @param name the name of the ability
     * @return if it has a group modifier (only done if it does have a group ability)
     */
    public boolean hasGroupAbility(String name)
    {
        return getAbility(name).getTargetAmount() != 1;
    }
    
    /*
     * Methods involving a player's health
     */
    /**
     * Add some shield health
     * @param shield extra shield to add
     */
    public void addShieldHealth(int shield) 
    {
        this.shieldHealth += shield;
    }


    /**
     * Get the amount of shield this character has.
     * @return How much shield they have.
     */
    public int getShieldHealth() 
    {
        return this.shieldHealth;
    }

    /**
     * See if the character has their shield.
     * @return if their shield is 0.
     */
    public boolean hasShield() 
    {
        return this.shieldHealth == 0;
    }

    /**
     * Heal the health of this entity.
     * @param healed Amount to add.
     */
    public void healHealth(
        int healed) 
    {
        health += healed;
        if (health > maxHealth) 
        {
            health = maxHealth;
        }
    }

    /**
     * Get the amount of health of this entity
     * @return the amount of health remaining.
     */
    public int getHealth() 
    {
        return this.health;
    }

    /**
     * Increase the MAXIMUM amount of health, along with adding more health.
     * @param addHealth How much to increase both amounts by.
     */
    public void addMaxHealth(int addHealth)
    {   
        maxHealth += addHealth;
        health += addHealth;
    }

    /**
     * Is the health of this character 0? Also do a check for death shields.
     * @param log a battlelog to record if any players died.
     * @return if the health is 0 AFTER any death shields have been triggered.
     */
    public boolean isHealthZero(BattleLog log, Game g) 
    {
        if (this.health <= 0)
        {
            searchForShield(Shield.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), creator, this, g, log);
        }
        return this.health <= 0;
    }

    /**
     * deals damage to the target based on the attacks strength, the casters base
     * attack and the targets base defense.
     * This also triggers a few type of shields: 
     * * Attack (whenever hp is taken)
     * * ShieldBreak (when shield reaches 0)
     * * Death (when hp is less than 0) 
     *
     * @param damageDealt    the total damage of the attack
     *
     * @param isPiercing     whether the attack ignores shield health
     *
     * @param ignoresDefense whether the attack ignores the base defense
     * 
     * @param caster 		 Whoever cast the ability.
     * 
     * @param e				The element of this attack.
     * 
     * @param g				the game class
     * @param log			The battle log to record an event.
     * 
     * @return     			{name, shieldDamageDone, ShieldLeft, healthDamageDone, HealthLeft, canKeepAttacking}
     */
 public Object[] dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e,
        Game g,
        BattleLog log) 
    {   
        Object[] logValues = new Object[]{this.name, 0, 0, 0, 0, false};
        boolean endAttack = false;
        Shield.Trigger type = null;
        if (caster != null)
        {
            type = Shield.Trigger.ATTACK;
        } 

        if (!ignoresDefense) 
        {
            damageDealt -= baseDefense;
        }
        
        if (0 >= damageDealt) 
        {
            return logValues;
        } 
        else if (isPiercing) 
        {
            if (type != null)
            {
//                System.out.println();
                endAttack = searchForShield(type, e, this, caster, g, log);
            }
            if (endAttack){
                logValues[5] = true;
                return logValues;
            }
            health -= damageDealt;
            logValues[1] = damageDealt;
            logValues[2] = (health < 0)? 0: health;
        } 
        else if (shieldHealth >= damageDealt) 
        {
            shieldHealth -= damageDealt;
            logValues[3] = damageDealt;
            logValues[4] = shieldHealth;
            return logValues;
        } 
        else
        {
            logValues[3] = shieldHealth;
            logValues[4] = 0;
            damageDealt -= shieldHealth;
            shieldHealth = 0;
            endAttack = searchForShield(Shield.Trigger.SHIELD_BREAK, e, this, caster, g, log);
            if (type != null)
            {
                endAttack = endAttack || searchForShield(type, e, this, caster, g, log);
            }
            if (endAttack){
                logValues[5] = true;
                return logValues;
            }
            logValues[1] = damageDealt;
            health -= damageDealt;
            logValues[2] = (health > 0)? health: 0;
        }
        
        if (health < 0) 
        {
            health = 0;
            searchForShield(Shield.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), this, creator, g, log);
        }
        return logValues;
    }

 	/** 
 	 * A separate method for effects to deal damage. It just won't break shields.
 	 * @param damageDealt How much damage to deal
 	 * @param isPiercing Whether this effect should ignore shield.
 	 * @param ignoresDefense Whether this effect should ignore defense.
 	 * @return Unsure what this is supposed to do.
 	 */
    public boolean dealEffectDamage(
        int damageDealt,
        boolean isPiercing,
        boolean ignoresDefense)
    {
         if (!ignoresDefense) 
        {
            damageDealt -= baseDefense;
        }

        if (0 >= damageDealt) 
        {
            return true;
        } 
        else if (isPiercing) 
        {
            health -= damageDealt;
        } 
        else if (shieldHealth >= damageDealt) 
        {
            shieldHealth -= damageDealt;
            return true;
        } 
        else
        {
            damageDealt -= shieldHealth;
            shieldHealth = 0;            
            health -= damageDealt;
            return false;
        }
        
        if (health < 0) 
        {
            health = 0;
        }
        return true;
    }
    //


    /*
     * Methods for adding/removing Effects
     */
    
    /** 
     * Add an effect to this entity
     * @param newEffect the effect to add.
     */
    public void addEffect(
        Entity caster,
        Game g,
        BattleLog log,
        Effect newEffect) 
    {
        effects.add(newEffect);
        mostRecentEffect = newEffect;
        searchForShield(Shield.Trigger.EFFECT_APPLIED, newEffect.getElement(), this, caster, g, log);
    }

    public void addStartingEffect(
        Effect e
    )
    {
        effects.add(e);
    }
    /**
     * Apply an effect on this entity. 
     * @param e the effect to use.
     * @param log The log to store what happened.
     */
    public void applyEffect(
        Effect e,
        BattleLog log
    )
    {
        e.useEffect(this, log);
    }

    public void gaveEffect(Game g, BattleLog log)
    {
        this.searchForShield(Shield.Trigger.GIVE_EFFECT, Elements.getElement(Elements.Name.ALL), this, this, g, log);
    }
    /**
     * Remove an effect from this entity
     * @param removed The effect to remove.
     */
    public void removeEffect(
        Effect removed) 
    {
        effects.remove(removed);
    }


    /** 
     * A method for removing effects based on their element
     * @param elementID The element to remove.
     * @param log The battlelog to record all removed effects.
     */
    public void removeEffects(
        Elements.Name elementID,
        Game g,
        Entity caster,
        BattleLog log)
    {
        List<String> effectsRemoved = new ArrayList<>();
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect e = effects.get(i);
            if (e.isRemovable()
                    &&  ( 
                            elementID.equals(Elements.Name.ALL) 
                            || elementID.equals(e.getElement().getID())
                        )
                ) 
            {
                effectsRemoved.add(e.getName());
                removeEffect(e);
            }
        }
        Object[] contents = new Object[]{name, effectsRemoved.toArray(new String[0])};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.CLEANSE, contents));
        searchForShield(Shield.Trigger.CLEANSE, Elements.getElement(elementID), this, caster, g, log);
    }
    //

    /**
     * Get the most recent effect applied to this target.
     * @return the most recent effect.
    */
    public Effect getMostRecentEffect()
    {
        return mostRecentEffect;
    }
    /*
    * Methods for adding/removing shields
    */
    
    /**
     * Give this hero a shield
     * @param s the shield to give.
     */
    public void addShield(
        Shield s,
        BattleLog log,
        Entity caster,
        Game g)
    {
        shields.add(s);
        searchForShield(Shield.Trigger.SHIELD_ADDED, Elements.getElement(Elements.Name.ALL), this, caster, g, log);
    }

    public void addStartingShield(
        Shield s
    )
    {
        shields.add(s);
    }

    /**
     * Remove a shield from this entity.
     * @param s the shield to remove.
     */
    public void removeShield(
        Shield s)
    {
        shields.remove(s);
    }

    /**
     * Reduce the shield durations and remove any that have expired.
     * @param log the battle log for storing any shields that happened to be removed.
     */
    public void reduceShieldDurations(BattleLog log)
    {
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            shields.get(i).passTurn(this, log);
        }
    }

    /**
     * A search for shields that will trigger any that happen to be relevant.
     * @param trigger The type of trigger to look for.
     * @param element The elemental trigger of this at.
     * @param target The one who would be targeted. For death ones though, this will be the creator.
     * @param caster The person who did the the thing, if applicable. For death ones, it will be this entity (since they are the ones dying)
     * @param log the battle log to record any triggers happening.
     * @return if this should nullify any future attacks
     */
    public boolean searchForShield(
        Shield.Trigger trigger, 
        Element element,
        Entity target, 
        Entity caster,
        Game g,
        BattleLog log)
    {
        boolean nullifyEffect = false;
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            Shield s = shields.get(i);
//            System.out.println(s.getName());
            if (s.wouldTrigger(trigger, element))
            {
                boolean nullify = s.triggerShield(target, caster, g, log);
                if (nullify)
                {
                    nullifyEffect = true;
                }
            }
        }
        return nullifyEffect;
    }

    /*
    * Methods for speed
    */
    
    /**
     * Add some speed 
     * @param speed the amount of speed to add.
     */
    public void addSpeed(
        int speed)
    {
        this.speed += speed;
    }

    /**
     * get the speed of this entity
     * @return the speed of this entity.
     */
    public int getSpeed()
    {
        return this.speed;
    }

    /**
     * A way to order the entities, bsed on speed.
     * @param other the other entity
     * @return the differences in speed.
     */
     @Override
    public int compareTo(
        Entity other) 
    {
        return this.speed - other.getSpeed();
    }


    /*
     * Methods for adding/getting attack or defense
     */
     
     /**
      * Add defense
      * @param defense the amount of defense to add.
      */
    public void addDefense(
        int defense) 
    {
        baseDefense += defense;
    }


    /**
     * Add attack
     * @param attack the amount of attack to add.
     */
    public void addAttack(
        int attack) 
    {
        baseAttack += attack;
    }


    /**
     * Get base attack
     * @return the base attack
     */
    public int getBaseAttack() 
    {
        return baseAttack;
    }

    /**
     * Get base defense
     * @return the base defense.
     */
    public int getBaseDefense() 
    {
        return baseDefense;
    }
    //

    /*
     * terrain 
    */

    /**
     * Set the terrain of this entity.
     * @param t the terrain.
     */
    public void setTerrain(Terrain t){
      this.t = t;
    }

    /** 
     * Get the terrain of this entity
     * @return this entity's terrain
     */
    public Terrain getTerrain(){
      return t;
    }

    /*
    * Getting an action 
    */
    /**
     * Get the actions this entity will do.
     * @param allHeros All of the heros that are possible targets.
     * @param inputReader the input system to get some things.
     * @return all actions this entity will do on their turn.
     */
    public List<Action> getActions(
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        List<Action> actions = new ArrayList<>();
        Integer numActions = state.applyStatus(this);
        if (numActions == 0)
        {
            actions.add(new PassAction(this));
            return actions;
        }
        for (int i = 0; i < numActions; i++)
        {
            Action a = null;
            while (a == null || !a.isLegalAction())
            {
//                System.out.println("Getting action");
                Entity target = inputReader.getSingleTarget();
                String name = inputReader.getAbilityName();
                a = new Action(target, this, name, allHeros, inputReader);
            }
            actions.add(a);
        }
        return actions;
    }

    /**
     * Actions to get on this entity's turn
     * @param fighters the fighters that are going to be possible targets.
     * @param scanInput the input system
     * @return all actions.
     */
    public List<Action> onTurn(ArrayList<Entity> fighters, InputSystem scanInput){
      System.out.println(this);
        List<Action> playerActions = this.getActions(fighters, scanInput);
        return playerActions;
    } 

    public void pass(
        BattleLog log,
        Game g
    )
    {
        searchForShield(Shield.Trigger.PASS, Elements.getElement(Elements.Name.ALL), this, this, g, log);
    }

    public void spawnedObject(
        BattleLog log,
        Entity caster,
        Game g
    )
    {
        searchForShield(Shield.Trigger.SPAWN, Elements.getElement(Elements.Name.ALL), this, caster, g, log);
    }
    
    public void performAction(
        BattleLog log,
        Game g
    )
    {
        searchForShield(Shield.Trigger.ANY_ACTION, Elements.getElement(Elements.Name.ALL), this, this, g, log);
    }

    public void hasAttacked(BattleLog log, Game g)
    {
        searchForShield(Shield.Trigger.ATTACKING, Elements.getElement(Elements.Name.ALL), this, this, g, log);    
    }
    /*
    *
    */
    /**
     * Get a given statistic
     * @param stat the statistic to use
     * @return the int representing that statistic.
     */
    public int getStatistic(Statistic stat)
    {
        switch (stat)
        {
            case SPEED:
                return speed;
            case HEALTH:
                return health;
            case MAX_HEALTH:
                return maxHealth;
            case BASE_ATTACK:
                return baseAttack;
            case BASE_DEFENSE:
                return baseDefense; 
            case SHIELD:
                return shieldHealth;
        }
        return 0;
    }

    /*
    * Changing state
    */
    /**
     * Change the state of this entity
     * @param newState the new state of this entity
     */
    public void replaceState(State newState, Entity caster, BattleLog log, Game g)
    {
        this.state = newState;
        searchForShield(Shield.Trigger.STATE_CHANGE, Elements.getElement(Elements.Name.ALL), this, caster, g, log);       
    }

    /**
     * Get current state
     * @return the current state.
     */
    public State getState()
    {
        return this.state;
    }

    /**
     * Reset the state of this entity
     */
    public void resetState()
    {
        this.state = defaultState;
    }

    /*
     * Actions done at the end of the turn
     */
    /**
     * Do some things at the end of this player's turn:
     *  * Use effects
     *  * reduce cooldowns and effect durations.
     *  * reduce the state's duration
     *  * See if the health is 0
     * @param log for recording all of these events.
     */
     public void endOfTurn(BattleLog log, Game g) 
    {
        Object[] contents = new Object[]{name};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.END_OF_TURN, contents));

        useEffects(log);
        reduceCooldowns(log);
        reduceShieldDurations(log);
        reduceStateDurations();
        isHealthZero(log, g);

        contents = new Object[]{name, state.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.CURRENT_STATE, contents));

        contents = new Object[]{health, maxHealth, shieldHealth, baseAttack, baseDefense, speed};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.STAT_DISPLAY, contents));
        
        return;
    }

    /**
      *	Use all of the effects this entity has 
      * @param log the log to record actions
     */
    public void useEffects(BattleLog log) 
    {
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect b = effects.get(i);
            b.useEffect(this, log);
        }
    }

    /**
     * Reduce the ability cooldowns
     * @param log unused for now.
     */
    public void reduceCooldowns(BattleLog log) 
    {
        for (Ability a : abilities) 
        {
            a.reduceCooldown();
        }
    }

    /**
     *Reduce the duration of the state.
     */
    public void reduceStateDurations()
    {
        state.reduceDuration(this);
    }
    
    /**
     * A method for if one can target this entity.
     * @return Always true.
     */
    public boolean isTargettable()
    {
    	return true;
    }
    
    private void setName(String newName)
    {
    	this.name = newName;
    }

    public int getTeamID()
    {
    	return teamID;
    }
    
    public void setTeamID(int newID)
    {
    	this.teamID = newID;
    }

	public void appendNumber(int countOfSpawn) {
		this.name += "." + countOfSpawn;
		
	}
}