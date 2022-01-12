package battlesystem;

import java.util.Random;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entity implements Comparable<Entity>
{

    public static final int HEALTH_LOST_INDEX = 0;
    public static final int SHIELD_LOST_INDEX = 1;
    public static final int KEEP_GOING_INDEX = 2;

    // free will between 1 and 20;
    private Entity creator;
    private String name;
    private int speed;
    private ArrayList<Ability> abilities;
    private ArrayList<Effect> effects;
    private ArrayList<Shield> shields;
    private State state;
    private int health;
    private int maxHealth;
    private int shieldHealth;
    private int baseAttack;
    private int baseDefense;
    private static Terrain t;


    public static enum Statistic
    {
        SPEED,
        MAX_HEALTH,
        HEALTH,
        BASE_ATTACK,
        BASE_DEFENSE,
        SHIELD
    }

    public Entity(
        String name, 
        int speed, 
        int health, 
        int shieldHealth,
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
        this.state = States.get(States.Name.NORMAL);
        this.baseAttack = 0;
        this.baseDefense = 0;
        this.creator = creator;
        Abilities.giveAbility(this, Abilities.Name.PASS_TURN);
    }


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
                EntityString.append("* ")
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
        EntityString.append("The state is: ")
                    .append(state.toString())
                    .append("\n")
                    .append("\u001B[31m");
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

        EntityString.append("\u001B[35m");
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
        Abilities.Name enumName) 
    {
        // System.out.println(this);
        for (Ability a : abilities) 
        {
            // System.out.println(a);
            // System.out.println(a.getID());
            // // System.out.println(id);
            if (a.getEnumName().equals(enumName)) 
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
    
    public boolean hasGroupAbility(
        Abilities.Name name)
    {
        return getAbility(name).hasModifier(Abilities.Modifier.GROUP);
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
        // System.out.println("called the heal health");
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
            Object[] contents = new Object[]{name};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.KNOCKED_OUT, contents));
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
        Shields.Trigger type = null;
        if (caster != null)
        {
            type = Shields.Trigger.ATTACK;
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
            endAttack = searchForShield(Shields.Trigger.SHIELD_BREAK, e, this, caster, log);
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
            searchForShield(Shields.Trigger.DEATH, Elements.getElement(Elements.Name.ALL), this, creator, log);
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


    public void reduceShieldDurations(
        BattleLog log
    )
    {
        for (int i = shields.size() - 1; i >= 0; i--)
        {
            shields.get(i).passTurn(this, log);
        }
    }


    public boolean searchForShield(
        Shields.Trigger trigger, 
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
        this.state = States.get(States.Name.NORMAL);
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
            // System.out.println(this);
            Effect b = effects.get(i);
            b.useEffect(this, log);
        }
    }

    public void reduceCooldowns(BattleLog log) 
    {
        for (Ability a : abilities) 
        {
            a.reduceCooldown();
            if (a.ableToUseAbility())
            {
               
            }
        }
    }

    public void reduceStateDurations()
    {
        state.reduceDuration(this);
    }

    
}