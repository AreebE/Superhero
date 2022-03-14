package battlesystem;

import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import battlesystem.databaseImpls.AbilityManager;
import battlesystem.databaseImpls.Effects;
import battlesystem.databaseImpls.Effects.Name;
import battlesystem.databaseImpls.Elements;
import battlesystem.databaseImpls.Shields;
import battlesystem.databaseImpls.States;

import java.util.Arrays;

public class Entity implements Comparable<Entity>
{
    // free will between 1 and 20;
    private transient Entity creator;
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
    private static transient Terrain t;


    public static enum Statistic
    {
        SPEED,
        MAX_HEALTH,
        HEALTH,
        BASE_ATTACK,
        BASE_DEFENSE,
        SHIELD
    }
    //alt constructor using EII
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
        ArrayList<String> abstogive = new ArrayList<String>();
        abstogive.add("pass turn");
        AbilityManager.giveAbilities(this, abstogive);
    }


    private void setStuffFromEII(EntityInfoItem in){
      this.abilities = new ArrayList<>();
      this.effects = new ArrayList<>();
      this.shields = new ArrayList<>();
      for(String t: in.abilities){
        this.abilities.add(AbilityManager.getAbility(t));
      }
        
            for(Effects.Name t: in.effects){
                this.effects.add(Effects.getEffect(t));
            }
        
      
        
            for(Shields.Name t: in.shields){
                this.shields.add(Shields.getShield(t));
            }
        

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
    


  
    protected Entity getCreator()
    {
        return this.creator;
    }

    /**
     * Provides information on what the Entity does
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


    public String getName() 
    {
        return this.name;
    }
    //

    /*
     * Methods involving a player's ability/abilities
     */
    public void addAbility(
        Ability newAbility) 
    {
        abilities.add(newAbility);
    }


    public ArrayList<Ability> getAbilities() 
    {
        return this.abilities;
    }


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


    public boolean hasAbility(
        Ability ability) 
    {
        return abilities.contains(ability);
    }
    
    public boolean hasGroupAbility(String name)
    {
        return getAbility(name).hasModifier(Ability.Modifier.GROUP);
    }
    
    /*
     * Methods involving a player's health
     */
    public void addShieldHealth(int shield) 
    {
        this.shieldHealth += shield;
    }


    public int getShieldHealth() 
    {
        return this.shieldHealth;
    }


    public boolean hasShield() 
    {
        return this.shieldHealth == 0;
    }


    public void healHealth(
        int healed) 
    {
        health += healed;
        if (health > maxHealth) 
        {
            health = maxHealth;
        }
    }


    public int getHealth() 
    {
        return this.health;
    }

    public void addMaxHealth(int addHealth)
    {   
        maxHealth += addHealth;
        health += addHealth;
    }

    public boolean isHealthZero(BattleLog log) 
    {
        if (this.health <= 0)
        {
            searchForShield(Shield.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), creator, this, log);
        }
        return this.health <= 0;
    }

    /**
     * deals damage to the target based on the attacks stregth, the casters base
     * attack and the targets base defense.
     *
     * @param damageDealt    the total damage of the attack
     *
     * @param isPiercing     whether the attack ignores shield health
     *
     * @param ignoresDefense whether the attack ignores the base defense
     *
     * @return     if the player can keep attacking
     */
 public Object[] dealDamage(
        int damageDealt, 
        boolean isPiercing, 
        boolean ignoresDefense,
        Entity caster,
        Element e,
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
                System.out.println();
                endAttack = searchForShield(type, e, this, caster, log);
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
            endAttack = searchForShield(Shield.Trigger.SHIELD_BREAK, e, this, caster, log);
            if (type != null)
            {
                endAttack = endAttack || searchForShield(type, e, this, caster, log);
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
            searchForShield(Shield.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), this, creator, log);
        }
        return logValues;
    }

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
    public void addEffect(
        Effect newEffect) 
    {
        effects.add(newEffect);
    }

    public void applyEffect(
        Effect e,
        BattleLog log
    )
    {
        e.useEffect(this, log);
    }

    public void removeEffect(
        Effect removed) 
    {
        effects.remove(removed);
    }


    public void removeEffects(
        Elements.Name elementID,
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
    }
    //

    /*
    * Methods for adding/removing shields
    */

    public void addShield(
        Shield s)
    {
        shields.add(s);
    }


    public void removeShield(
        Shield s)
    {
        shields.remove(s);
    }


    public void reduceShieldDurations(BattleLog log)
    {
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            shields.get(i).passTurn(this, log);
        }
    }


    public boolean searchForShield(
        Shield.Trigger trigger, 
        Element element,
        Entity target, 
        Entity caster,
        BattleLog log)
    {
        boolean nullifyEffect = false;
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            Shield s = shields.get(i);
            if (s.wouldTrigger(trigger, element))
            {
                boolean nullify = s.triggerShield(target, caster, log);
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
    public void addSpeed(
        int speed)
    {
        this.speed += speed;
    }

    public int getSpeed()
    {
        return this.speed;
    }

     @Override
    public int compareTo(
        Entity other) 
    {
        return this.speed - other.getSpeed();
    }


    /*
     * Methods for adding/getting attack or defense
     */
    public void addDefense(
        int defense) 
    {
        baseDefense += defense;
    }


    public void addAttack(
        int attack) 
    {
        baseAttack += attack;
    }


    public int getBaseAttack() 
    {
        return baseAttack;
    }


    public int getBaseDefense() 
    {
        return baseDefense;
    }
    //

    /*
     * terrain 
    */

    public void setTerrain(Terrain t){
      this.t = t;
    }

    public Terrain getTerrain(){
      return t;
    }

    /*
    * Getting an action 
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
                Entity target = inputReader.getSingleTarget();
                String name = inputReader.getAbilityName();
                a = new Action(target, this, name, allHeros, inputReader);
            }
            actions.add(a);
        }
        return actions;
    }

    /*
    *
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
    public void replaceState(State newState)
    {
        this.state = newState;
    }

    public State getState()
    {
        return this.state;
    }

    public void resetState()
    {
        this.state = defaultState;
    }

    /*
     * Actions done at the end of the turn
     */
     public void endOfTurn(BattleLog log) 
    {
        Object[] contents = new Object[]{name};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.END_OF_TURN, contents));

        useEffects(log);
        reduceCooldowns(log);
        reduceShieldDurations(log);
        reduceStateDurations();
        isHealthZero(log);

        contents = new Object[]{name, state.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.CURRENT_STATE, contents));

        contents = new Object[]{health, maxHealth, shieldHealth, baseAttack, baseDefense, speed};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.STAT_DISPLAY, contents));
        
        return;
    }


    public void useEffects(BattleLog log) 
    {
        for (int i = effects.size() - 1; i >= 0; i--) 
        {
            Effect b = effects.get(i);
            b.useEffect(this, log);
        }
    }

    public void reduceCooldowns(BattleLog log) 
    {
        for (Ability a : abilities) 
        {
            a.reduceCooldown();
        }
    }

    public void reduceStateDurations()
    {
        state.reduceDuration(this);
    }
    
    public boolean isTargettable()
    {
    	return true;
    }

}